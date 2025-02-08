package com.example.sweatout.welcome.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sweatout.welcome.presentation.models.UserUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeModuleViewModel : ViewModel() {
    private val _userUiState = MutableStateFlow(UserUI())
    val userUiState: StateFlow<UserUI> = _userUiState

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

    fun updateName(new:String){
        _userUiState.value = _userUiState.value.copy(name = new)
    }

    fun updateUsername(new:String){
        _userUiState.value = _userUiState.value.copy(nickName = new)
    }

    fun updateMobileNo(new: String){
        _userUiState.value = _userUiState.value.copy(phoneNo = new)
    }

    fun updateImage(imageUri: Uri) { // to temporary store the data
        _userUiState.value = _userUiState.value.copy(imageUrl = imageUri.toString())
    }

    fun updateEmail(new: String){ // update email while using the sign up and sign in function
        _userUiState.value = _userUiState.value.copy(email = new)
    }

    fun signInUserByEmail() {
        Log.e("login", "Login attempted")
    }

    fun signUpUserByEmail() {
        Log.e("signup", "sign up attempted")
    }
}