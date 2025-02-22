package com.example.sweatout.exercise.presentation

import androidx.lifecycle.ViewModel
import com.example.sweatout.exercise.domain.SessionResult
import com.example.sweatout.exercise.presentation.models.SessionResultUi
import com.example.sweatout.exercise.presentation.models.toCal
import com.example.sweatout.exercise.presentation.models.toDays
import com.example.sweatout.exercise.presentation.models.toMinutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutViewModal:ViewModel(){

    private val _session_Result = MutableStateFlow(SessionResult())
    val session_Result = _session_Result.asStateFlow()
    private val _sessionUi_Result = MutableStateFlow(SessionResultUi())
    val sessionUi_Result = _sessionUi_Result.asStateFlow()

    fun increaseStreak(){
        _session_Result.value = _session_Result.value.copy(streak = _session_Result.value.streak + 1)
        updateSessionUiResultUiFlow()
    }
    fun increaseCalBurnedInSession(){
        _session_Result.value = _session_Result.value.copy(calBurnedInSession = _session_Result.value.calBurnedInSession + 1)
        updateSessionUiResultUiFlow()
    }
    fun increaseTotalTimeInSession(){
        _session_Result.value = _session_Result.value.copy(totalTimeInSession = _session_Result.value.totalTimeInSession + 1)
        updateSessionUiResultUiFlow()
    }
    fun increaseExerciseDone(){
        _session_Result.value = _session_Result.value.copy(exercisesDoneInSession = _session_Result.value.exercisesDoneInSession + 1)
        updateSessionUiResultUiFlow()
    }
    fun increaseTotalCal(){
        _session_Result.value = _session_Result.value.copy(totalCalBurned = _session_Result.value.calBurnedInSession + _session_Result.value.totalCalBurned)
        updateSessionUiResultUiFlow()
    }
    fun increaseExercisesDone(){
        _session_Result.value = _session_Result.value.copy(totalExercisesDone = _session_Result.value.totalExercisesDone + _session_Result.value.exercisesDoneInSession)
        updateSessionUiResultUiFlow()
    }


    fun updateSessionUiResultUiFlow(){
        _sessionUi_Result.value = SessionResultUi(
            totalCalBurned = _session_Result.value.totalCalBurned.toCal(),
            calBurnedInSession = _session_Result.value.calBurnedInSession.toCal(),
            totalExercisesDone = _session_Result.value.totalExercisesDone.toString(),
            exercisesInSession = _session_Result.value.exercisesDoneInSession.toString(),
            totalTimeInSession = _session_Result.value.totalTimeInSession.toMinutes(),
            streak = _session_Result.value.streak.toDays()
        )
    }
}