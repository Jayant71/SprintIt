package com.demo.sprintit.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val profileImageUrl: String = "",
    val role: String = "Member", // Roles like Admin, Member, etc.
    val createdAt: Long = System.currentTimeMillis()
)
