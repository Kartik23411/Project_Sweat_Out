package com.example.sweatout.core.data.firebase

import com.example.sweatout.welcome.domain.models.User
import com.example.sweatout.welcome.domain.repository.AuthRepository
import com.example.sweatout.welcome.presentation.models.UserUI
import com.example.sweatout.welcome.presentation.models.toUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseAuthManager : AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance();
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun signUp(
        email: String,
        password: String,
        user: User,
        callback: (Result<User>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.currentUser?.let { firebaseUser ->
                    firestore.collection("users")
                            .document(firebaseUser.uid)
                            .set(user)
                            .addOnSuccessListener {
                                callback(Result.success(user))
                            }
                            .addOnFailureListener { exception ->
                                callback(Result.failure(exception))
                            }
                } ?: callback(Result.failure(Exception("Error in creating the account")))
            }
            else {
                callback(Result.failure(task.exception ?: Exception("Can't Sign up now")))
            }
        }
    }

    override fun signIn(email: String, password: String, callback: (Result<User>) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.currentUser?.let { firebaseUser ->
                    firestore.collection("users")
                            .document(firebaseUser.uid)
                            .get()
                            .addOnSuccessListener { document ->
                                if (document.exists()) {
                                    val user = document.toObject(UserUI::class.java)?.toUser()
                                    if (user != null) callback(Result.success(user))
                                    else callback(Result.failure(Exception("Parsing error")))
                                }
                                else {
                                    callback(Result.failure(Exception("User not found")))
                                }
                            }
                            .addOnFailureListener { exception ->
                                callback(Result.failure(exception))
                            }
                } ?: callback(Result.failure(Exception("User Sign in error")))
            }
            else {
                callback(Result.failure(task.exception ?: Exception("User Sign in error")))
            }
        }
    }

    fun updateDetails(user: User, callback: (Result<User>) -> Unit) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("users").document(currentUser.uid)
                    .set(user)
                    .addOnSuccessListener {
                        callback(Result.success(user))
                    }
                    .addOnFailureListener { exception ->
                        callback(Result.failure(Exception(exception)))
                    }
        }
        else {
            callback(Result.failure(Exception("User is not logged in")))
        }
    }
}