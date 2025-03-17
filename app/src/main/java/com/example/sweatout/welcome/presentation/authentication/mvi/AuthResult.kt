package com.example.sweatout.welcome.presentation.authentication.mvi

import com.example.sweatout.welcome.domain.models.User

data class AuthResult(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)