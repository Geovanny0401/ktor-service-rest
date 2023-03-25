package com.geovannycode.mapper

import com.geovannycode.dto.UserDTORegister
import com.geovannycode.dto.UserDTOResponse
import com.geovannycode.models.User
import com.geovannycode.utils.Encryptor

fun User.toDTO(): UserDTOResponse {
    return UserDTOResponse(
        username = username,
        role = role,
    )
}

fun UserDTORegister.toUser(): User {
    return User(
        username = username,
        password = Encryptor.encryptor(password),
        role = role,
    )
}
