package com.geovannycode.utils

import com.toxicbakery.bcrypt.Bcrypt
object Encryptor {
    private const val BRCRYPT_SALT = 12

    fun encryptor(password: String): String {
        return Bcrypt.hash(password, BRCRYPT_SALT).decodeToString()
    }
}