package com.demo.sprintit.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskId: String = "",
    val projectId: String = "", // Associated Project ID
    val title: String = "",
    val description: String = "",
    val assignedTo: List<String> = emptyList(), // List of User IDs
    val priority: String = "Medium", // Priority: Low, Medium, High
    val status: String = "To Do", // Status: To Do, In Progress, Done
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null
)
