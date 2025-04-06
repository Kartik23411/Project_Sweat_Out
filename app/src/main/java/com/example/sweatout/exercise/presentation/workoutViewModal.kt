package com.example.sweatout.exercise.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweatout.core.domain.util.Result
import com.example.sweatout.core.session.UserSession
import com.example.sweatout.exercise.data.ExerciseRepository
import com.example.sweatout.exercise.domain.DifficultyLevel
import com.example.sweatout.exercise.domain.Exercise
import com.example.sweatout.exercise.domain.SessionResult
import com.example.sweatout.exercise.presentation.models.SessionResultUi
import com.example.sweatout.exercise.presentation.models.toCal
import com.example.sweatout.exercise.presentation.models.toDays
import com.example.sweatout.exercise.presentation.models.toMinutes
import com.example.sweatout.welcome.domain.models.User
import com.example.sweatout.welcome.domain.models.toUserUI
import com.example.sweatout.welcome.presentation.models.UserUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModal @Inject constructor(
    private val userSession: UserSession,
    private val repository: ExerciseRepository
) : ViewModel() {

    private val _user: StateFlow<User?> =
            userSession.currentUserFlow.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val userUi: StateFlow<UserUI?> = _user.map { user -> user?.toUserUI() }
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _session_Result = MutableStateFlow(SessionResult())
    val session_Result: StateFlow<SessionResult> = _session_Result

    private val _sessionUi_Result = MutableStateFlow(SessionResultUi())
    val sessionUi_Result: StateFlow<SessionResultUi> = _sessionUi_Result

    private val _state: StateFlow<WorkoutState> = MutableStateFlow(WorkoutState())
    val state: StateFlow<WorkoutState> = _state

    fun loadWorkoutPlan(difficultyLevel: DifficultyLevel) {
        viewModelScope.launch {
            _state.value.copy(isLoading = true)
            when (val result = repository.getExercisePlan(difficultyLevel)) {
                is Result.Success -> {
                    Log.d("Data", result.data.toString())
                    _state.value.copy(isLoading = false, exercises = result.data)
                }

                is Result.Error   -> _state.value.copy(isLoading = false)
            }
        }
    }

    var getAllExercises: Flow<List<Exercise>> = repository.getAllExercises()


    fun increaseStreak() {
        _session_Result.value =
                _session_Result.value.copy(streak = _session_Result.value.streak + 1)
        updateSessionUiResultUiFlow()
    }

    fun increaseCalBurnedInSession(calBurned: Int) {
        _session_Result.value =
                _session_Result.value.copy(
                    calBurnedInSession = _session_Result.value
                            .calBurnedInSession + calBurned
                )
        Log.d("Exercise Updated", "${_session_Result.value.calBurnedInSession} and $calBurned")
        updateSessionUiResultUiFlow()
    }

    fun increaseTotalTimeInSession(time: Int) {
        _session_Result.value =
                _session_Result.value.copy(
                    totalTimeInSession = _session_Result.value
                            .totalTimeInSession + time
                )
        updateSessionUiResultUiFlow()
    }

    fun increaseExerciseDone() {
        _session_Result.value =
                _session_Result.value.copy(exercisesDoneInSession = _session_Result.value.exercisesDoneInSession + 1)
        updateSessionUiResultUiFlow()
    }

    fun increaseTotalCal() {
        viewModelScope.launch {
            updateTotalCaloriesBurned(_session_Result.value.calBurnedInSession)
        }
        updateSessionUiResultUiFlow()
    }

    suspend fun updateTotalCaloriesBurned(additionalCalories: Int) {
        val currentUser = userSession.currentUserFlow.first()
        currentUser?.let { user ->
            val updatedUser = user.copy(
                totalCaloriesBurned = user.totalCaloriesBurned + additionalCalories
            )
            Log.d("Updated user", "$updatedUser")
            userSession.saveCurrentUser(updatedUser)
        }
    }

    fun increaseExercisesDone() {
        _session_Result.value =
                _session_Result.value.copy(totalExercisesDone = _session_Result.value.totalExercisesDone + _session_Result.value.exercisesDoneInSession)
        updateSessionUiResultUiFlow()
    }

    fun clearPreviousSession() {
        increaseTotalCal()
        _session_Result.value = _session_Result.value.copy(
            exercisesDoneInSession = 0,
            totalTimeInSession = 0,
            calBurnedInSession = 0
        )
    }

    fun updateSessionUiResultUiFlow() {
        _sessionUi_Result.value = SessionResultUi(
            calBurnedInSession = _session_Result.value.calBurnedInSession.toCal(),
            totalExercisesDone = _session_Result.value.totalExercisesDone.toString(),
            exercisesInSession = _session_Result.value.exercisesDoneInSession.toString(),
            totalTimeInSession = (_session_Result.value.totalTimeInSession).toMinutes(),
            streak = _session_Result.value.streak.toDays()
        )
    }
}

data class WorkoutState(
    val isLoading: Boolean = false,
    val exercises: List<Exercise> = emptyList()
)