package com.example.sweatout.welcome.presentation

import androidx.lifecycle.ViewModel
import com.example.sweatout.welcome.presentation.models.UserUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeModuleViewModel:ViewModel() {
    private val _userUiState = MutableStateFlow(UserUI())
    val userUiState: StateFlow<UserUI> = _userUiState

    fun updateGender(gender:String){
        _userUiState.value = _userUiState.value.copy(gender = gender)
    }

    fun updateAge(age:Int){
        _userUiState.value = _userUiState.value.copy(age = age)
    }

    fun updateHeight(height:Int){
        _userUiState.value = _userUiState.value.copy(height = height)
    }

    fun updateWeight(weight:Int){
        _userUiState.value = _userUiState.value.copy(weight = weight)
    }
}