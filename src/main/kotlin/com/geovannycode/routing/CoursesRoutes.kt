package com.geovannycode.routing

import com.geovannycode.dto.CourseDTO
import com.geovannycode.mapper.toDTO
import com.geovannycode.services.course.DefaultCourseService
import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val END_POINT = "api/courses"

fun Application.coursesRoutes() {
    val courseService: DefaultCourseService by inject()

    // val usuarioService: DefaultUserService by inject()

    routing {
        route("/$END_POINT") {
            get({
                description = "Get all courses"
                response {
                    HttpStatusCode.OK to {
                        description = "List of courses"
                        body<List<CourseDTO>> { description = "List of courses" }
                    }
                }
            }) {
                val result = mutableListOf<CourseDTO>()
                courseService.findAll().collect {
                    result.add(it.toDTO())
                }
                courseService.findAll().collect {
                    println(it)
                }
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }
}
