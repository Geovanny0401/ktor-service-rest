package com.geovannycode.routing

import com.geovannycode.dto.CourseDTO
import com.geovannycode.mapper.toCourse
import com.geovannycode.mapper.toDTO
import com.geovannycode.services.course.DefaultCourseService
import io.github.smiley4.ktorswaggerui.dsl.delete
import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.github.smiley4.ktorswaggerui.dsl.put
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

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

            get(
                "{id}",
                {
                    description = "Get by id course"
                    request {
                        pathParameter<String>("id") {
                            description = "Id the course"
                            required = true
                        }
                    }
                    response {
                        HttpStatusCode.OK to {
                            description = "Id success"
                            body<CourseDTO> { description = "Course" }
                        }
                        HttpStatusCode.BadRequest to {
                            description = "Not found id"
                            body<String> { description = "Exception" }
                        }
                    }
                },
            ) {
                try {
                    val id = call.parameters["id"]!!
                    val course = courseService.findById(UUID.fromString(id))

                    call.respond(HttpStatusCode.OK, course.toDTO())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            post(
                {
                    description = "Post course"
                    request {
                        body<CourseDTO> {
                            description = "Request course"
                        }
                    }
                    response {
                        HttpStatusCode.Created to {
                            description = "Course Success"
                            body<CourseDTO> { description = "Course created" }
                        }
                        HttpStatusCode.BadRequest to {
                            description = "Fields not validation"
                            body<String> { description = "Exception" }
                        }
                    }
                },
            ) {
                try {
                    val courseReceive = call.receive<CourseDTO>()
                    val courseSave = courseService.save(courseReceive.toCourse())
                    call.respond(
                        HttpStatusCode.Created,
                        courseService.findById(courseSave.id).toDTO(),
                    )
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            put(
                "{id}",
                {
                    description = "Put course"
                    request {
                        pathParameter<String>("id") {
                            description = "Id the course"
                            required = true
                        }
                        body<CourseDTO> {
                            description = "Request of Course"
                        }
                    }
                    response {
                        HttpStatusCode.OK to {
                            description = "Update Course"
                            body<CourseDTO> { description = "Update Course" }
                        }
                        HttpStatusCode.BadRequest to {
                            description = "Invalidate some fields"
                            body<String> { description = "Exception" }
                        }
                    }
                },
            ) {
                try {
                    val id = call.parameters["id"]!!
                    val request = call.receive<CourseDTO>()
                    val course = courseService.update(UUID.fromString(id), request.toCourse())
                    call.respond(HttpStatusCode.OK, course.toDTO())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            delete(
                "{id}",
                {
                    description = "Delete by id Course"
                    request {
                        pathParameter<String>("id") {
                            description = "Id the course"
                            required = true
                        }
                    }
                    response {
                        HttpStatusCode.NoContent to {
                            description = "Delete Course with success"
                        }
                        HttpStatusCode.BadRequest to {
                            description = "Not found Course"
                            body<String> { description = "Exception" }
                        }
                    }
                },
            ) {
                try {
                    val id = call.parameters["id"]!!
                    val courseDelete = courseService.findById(UUID.fromString(id))
                    courseService.delete(courseDelete)
                    call.respond(HttpStatusCode.NoContent)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }
        }
    }
}
