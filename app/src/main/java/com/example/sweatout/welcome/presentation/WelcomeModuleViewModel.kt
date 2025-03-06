package com.example.sweatout.welcome.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweatout.core.data.firebase.FirebaseAuthManager
import com.example.sweatout.core.session.UserSession
import com.example.sweatout.welcome.presentation.authentication.mvi.AuthResult
import com.example.sweatout.welcome.presentation.models.UserUI
import com.example.sweatout.welcome.presentation.models.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeModuleViewModel @Inject constructor(
    private val userSession: UserSession
): ViewModel() {
    private val _userUiState = MutableStateFlow(UserUI())
    val userUiState: StateFlow<UserUI> = _userUiState

    private val _authResult = MutableStateFlow(AuthResult())
    val authResult: StateFlow<AuthResult> = _authResult

    fun updateGender(gender: String) {
        _userUiState.value = _userUiState.value.copy(gender = gender)
    }

    fun updateAge(age: Int) {
        _userUiState.value = _userUiState.value.copy(age = age)
    }

    fun updateHeight(height: Int) {
        _userUiState.value = _userUiState.value.copy(height = height)
    }

    fun updateWeight(weight: Int) {
        _userUiState.value = _userUiState.value.copy(weight = weight)
    }

    fun updateActivityLevel(level: String) {
        _userUiState.value = _userUiState.value.copy(activityLevel = level)
    }

    fun updateGoals(goals: List<String>) {
        _userUiState.value = _userUiState.value.copy(goals = goals)
    }

    fun updateName(new: String) {
        _userUiState.value = _userUiState.value.copy(name = new)
    }

    fun updateUsername(new: String) {
        _userUiState.value = _userUiState.value.copy(nickName = new)
    }

    fun updateMobileNo(new: String) {
        _userUiState.value = _userUiState.value.copy(phoneNo = new)
    }

    fun updateImage(imageUri: Uri) { // to temporary store the data
        _userUiState.value = _userUiState.value.copy(imageUrl = imageUri.toString())
    }

    fun updateEmail(new: String) { // update email while using the sign up and sign in function
        _userUiState.value = _userUiState.value.copy(email = new)
    }

    fun signInUserByEmail(email: String, password: String) {
        _authResult.value = AuthResult(isLoading = true)
        val firebaseAuthManager = FirebaseAuthManager()
        firebaseAuthManager.signIn(email, password){result->
            result.fold(
                onSuccess = {user->
                    viewModelScope.launch {
                        viewModelScope.launch {
                            userSession.saveCurrentUser(user)
                        }
                    }
                    _authResult.value = AuthResult(isSuccess = true, user = user)
                    Log.d("signup", "Signup Succeed")
                },
                onFailure = {error->
                    _authResult.value = AuthResult(isError = true, errorMessage = error.message)
                    Log.e("Error","Signup failed", error)
                }
            )
        }
    }

    fun signUpUserByEmail(email: String, password: String) {
        updateEmail(email)
        _authResult.value = AuthResult(isLoading = true)
        val firebaseAuthManager = FirebaseAuthManager()
        val user = _userUiState.value.toUser()
        firebaseAuthManager.signUp(email, password, user){result->
            result.fold(
                onSuccess = { newuser ->
                    viewModelScope.launch {
                        userSession.saveCurrentUser(newuser)
                    }
                    _authResult.value = AuthResult(isSuccess = true, user = newuser)
                    Log.d("signup", "Signup Succeed")
                },
                onFailure = {error->
                    _authResult.value = AuthResult(isError = true, errorMessage = error.message)
                    Log.e("Error","Signup failed", error)
                }
            )
        }
        Log.e("signup", "sign up attempted by $user")
    }

    fun updateDetails(){
        val firebaseAuthManager = FirebaseAuthManager()
        val updatedUser = _userUiState.value.toUser()
        _authResult.value = AuthResult(isLoading = true)
        firebaseAuthManager.updateDetails(updatedUser){result->
            result.fold(
                onSuccess = {user->
                    viewModelScope.launch {
                        userSession.saveCurrentUser(user)
                    }
                    _authResult.value = AuthResult(isSuccess = true, user = user)
                    Log.d("user", user.toString())
                },
                onFailure = {error->
                    _authResult.value = AuthResult(isError = true, errorMessage = error.message)
                    Log.e("UpdateUser", "Failed to update user details", error)
                }
            )
        }
    }
}