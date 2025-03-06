package com.example.sweatout.welcome.domain.repository

import com.example.sweatout.welcome.domain.models.User

interface AuthRepository {
    fun signUp(email: String, password:String, user: User, callback:(Result<User>)->Unit)
    fun signIn(email: String, password: String, callback: (Result<User>) -> Unit)
}