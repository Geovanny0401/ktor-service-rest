package com.geovannycode.models

import java.util.UUID

data class Course(
    val id: UUID = UUID.randomUUID(),
    val name: String
)
