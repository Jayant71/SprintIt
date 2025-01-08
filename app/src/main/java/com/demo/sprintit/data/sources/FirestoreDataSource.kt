package com.demo.sprintit.data.sources

import android.util.Log
import com.google.android.gms.tasks.Task
import com.demo.sprintit.data.model.User as LocalUser
import com.google.firebase.firestore.FirebaseFirestore


class FirestoreDataSource {
    private  val firestore = FirebaseFirestore.getInstance()

    fun addUser(user: LocalUser): Task<Void> {
        return firestore.collection("users").document(user.userId).set(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("FirestoreDataSource", "User added successfully")
            } else {
                Log.e("FirestoreDataSource", task.exception?.message.toString())
            }
        }

    }
}