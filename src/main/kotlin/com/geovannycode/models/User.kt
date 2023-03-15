package com.geovannycode.models

import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val username: String,
    val password: String,
    val role: String = Role.USER.name,
) {
    enum class Role {
        ADMIN, USER
    }
}
