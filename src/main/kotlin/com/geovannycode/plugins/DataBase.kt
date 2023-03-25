package com.geovannycode.plugins

import com.geovannycode.services.DataBaseService
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureDataBase() {
    val dataBaseService: DataBaseService by inject()

    dataBaseService.initDataBaseService()
}
