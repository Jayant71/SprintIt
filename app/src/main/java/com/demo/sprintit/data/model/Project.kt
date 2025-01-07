package com.demo.sprintit.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val projectId: String = "",
    val title: String = "",
    val description: String = "",
    val createdBy: String = "", // User ID of the creator
    val teamMembers: List<String> = emptyList(), // List of User IDs
    val createdAt: Long = System.currentTimeMillis(),
    val deadline: Long? = null,
    val status: String = "Active" // Status: Active, Completed, Archived
)
