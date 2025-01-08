package com.demo.sprintit.data.sources

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthSource {
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun isSignedIn(): Boolean {
        return  firebaseAuth.currentUser != null
    }

    fun signUp(email: String, password: String): Task<AuthResult> {
       return firebaseAuth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener { task ->
               if (task.isSuccessful) {
                   Log.d("FirebaseAuthSource", "User created successfully")
                   Result.success("success")
               } else {
                   Log.e("FirebaseAuthSource", task.exception?.message.toString())
                   Result.failure(task.exception!!)
               }
           }
    }

    fun signIn(email: String, password: String): Task<AuthResult> {
       return firebaseAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener { task ->
               if (task.isSuccessful) {
                   Log.d("FirebaseAuthSource", "User signed in successfully")
                     Result.success("success")
                } else {
                     Log.e("FirebaseAuthSource", task.exception?.message.toString())
                     Result.failure(task.exception!!)
               }
           }
    }

    fun signOut() {
    firebaseAuth.signOut()
    Log.d("FirebaseAuthSource", "User signed out successfully")
}


}