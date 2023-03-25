package com.geovannycode.routing

import com.geovannycode.services.StorageService
import com.geovannycode.services.student.DefaultStudentService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val END_POINT = "api/students"

fun Application.studentsRoutes() {
    val studentService: DefaultStudentService by inject()

    val storageService: StorageService by inject()
    routing {
        route("/$END_POINT") {
        }
    }
}
