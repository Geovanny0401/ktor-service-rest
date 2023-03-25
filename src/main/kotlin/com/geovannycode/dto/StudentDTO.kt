package com.geovannycode.dto

import kotlinx.serialization.Serializable

@Serializable
data class StudentDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val courseId: String? = null,
    val avatar: String?,
)
