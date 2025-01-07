package com.demo.sprintit.presentation.repository

import com.demo.sprintit.data.sources.FirebaseAuthSource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult


class FirebaseAuthRepository() {
     fun signUp(email: String, password: String): Task<AuthResult> {
        return FirebaseAuthSource().signUp(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Result.success("success")
            } else {
                Result.failure(task.exception!!)
            }
        }
    }

     fun signIn(email: String, password: String): Task<AuthResult> {
        return FirebaseAuthSource().signIn(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                Result.success("success")
            } else {
                Result.failure(task.exception!!)
            }
        }
    }

     fun signOut() {
        return FirebaseAuthSource().signOut()
    }

    fun isSignedIn(): Boolean {
        return FirebaseAuthSource().isSignedIn()
    }
}