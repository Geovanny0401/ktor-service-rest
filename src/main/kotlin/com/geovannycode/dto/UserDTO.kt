package com.geovannycode.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTOLogin(
    val username: String,
    val password: String
)

@Serializable
data class UserDTORegister(
    val username: String,
    val password: String,
    val role: String,
)

@Serializable
data class UserDTOResponse(
    val username: String,
    val role: String,
)