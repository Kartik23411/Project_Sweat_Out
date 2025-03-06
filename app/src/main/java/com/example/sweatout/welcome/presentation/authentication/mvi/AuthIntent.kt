package com.example.sweatout.welcome.presentation.authentication.mvi

import com.example.sweatout.welcome.domain.models.User

sealed class AuthIntent {
    data class SignUp(val email: String, val password: String, val user:User): AuthIntent()
    data class  SignIn(val email: String, val password: String): AuthIntent()
}