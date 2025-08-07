package org.example.app.model

import java.util.UUID

// PUBLIC_INTERFACE
data class Note(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var content: String,
    var tags: List<String> = listOf(),
    val createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
)
