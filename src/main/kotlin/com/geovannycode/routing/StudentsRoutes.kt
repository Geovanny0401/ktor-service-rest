package com.geovannycode.routing

import com.geovannycode.dto.StudentDTO
import com.geovannycode.services.StorageService
import com.geovannycode.services.student.DefaultStudentService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.text.get

private const val END_POINT = "api/students"

fun Application.studentsRoutes() {
    val studentService: DefaultStudentService by inject()

    val storageService: StorageService by inject()
    routing {
        route("/$END_POINT") {
            get(
                {
                    description = "Get all students"
                    response {
                        HttpStatusCode.OK to {
                            description = "List de studentsDTO"
                            body<List<StudentDTO>> { description = "List de studentsDTO" }
                        }
                    }
                },
            ) {
                val result = mutableListOf<StudentDTO>()
                studentService.findAll().collect {
                    result.add(it.toDTO())
                }
                studentService.findAll().collect {
                    println(it)
                }
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }
}
