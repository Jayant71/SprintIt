package com.demo.sprintit.presentation.repository

import com.demo.sprintit.data.model.User as LocalUser
import com.demo.sprintit.data.sources.FirestoreDataSource
import com.google.android.gms.tasks.Task

class FireStoreRepository {
    private val firestoreDataSource = FirestoreDataSource()

    fun addUser(user: LocalUser): Task<Void> {
        return firestoreDataSource.addUser(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Result.success("success")
            } else {
                Result.failure(task.exception!!)
            }
        }
    }
}