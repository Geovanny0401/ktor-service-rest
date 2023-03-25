package com.geovannycode.plugins

import com.geovannycode.routing.coursesRoutes
import com.geovannycode.routing.storageRoutes
import com.geovannycode.routing.studentsRoutes
import io.ktor.server.application.*

fun Application.configureRouting() {
    storageRoutes()
    coursesRoutes()
    studentsRoutes()
}
