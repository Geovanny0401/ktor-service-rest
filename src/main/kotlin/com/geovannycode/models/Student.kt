package com.geovannycode.models

import java.util.UUID

data class Student(
    val id: UUID = UUID.randomUUID(),
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    var avatar: String?,
    var courseId: UUID? = null,
)
