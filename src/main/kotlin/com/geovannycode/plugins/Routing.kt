package com.geovannycode.plugins

import com.geovannycode.routing.studentsRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    storageRoutes()
    coursesRoutes()
    studentsRoutes()
}
