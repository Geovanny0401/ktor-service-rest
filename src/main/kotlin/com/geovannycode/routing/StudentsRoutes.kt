package com.geovannycode.routing

import com.geovannycode.dto.StudentDTO
import com.geovannycode.mapper.toDTO
import com.geovannycode.mapper.toStudent
import com.geovannycode.services.StorageService
import com.geovannycode.services.student.DefaultStudentService
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
                            description = "List of student"
                            body<List<StudentDTO>> { description = "List of student" }
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

            get(
                "{id}",
                {
                    description = "Get by id student"
                    request {
                        pathParameter<String>("id") {
                            description = "id for student"
                            required = true
                        }
                    }
                    response {
                        HttpStatusCode.OK to {
                            description = "Id success"
                            body<StudentDTO> { description = "Student Request" }
                        }
                        HttpStatusCode.BadRequest to {
                            description = "Id Not Found"
                            body<String> { description = "Exception" }
                        }
                    }
                },
            ) {
                try {
                    val id = call.parameters["id"]!!
                    val student = studentService.findById(UUID.fromString(id))
                    call.respond(HttpStatusCode.OK, student.toDTO())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            post(
                {
                    description = "Post student"
                    request {
                        body<StudentDTO> {
                            description = "Request Student"
                        }
                    }
                    response {
                        HttpStatusCode.Created to {
                            description = "Student Created success"
                            body<StudentDTO> { description = "Student created" }
                        }
                        HttpStatusCode.BadRequest to {
                            description = "Invalidate fields"
                            body<String> { description = "Exception" }
                        }
                    }
                },
            ) {
                try {
                    val studentReceive = call.receive<StudentDTO>()
                    val studentSave = studentReceive.toStudent()
                    studentService.save(studentSave)
                    call.respond(HttpStatusCode.Created, studentService.findById(studentSave.id).toDTO())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            put("{id}", {
                description = "Put student"
                request {
                    pathParameter<String>("id") {
                        description = "Id of student"
                        required = true
                    }
                    body<StudentDTO> {
                        description = "Request Student"
                    }
                }
                response {
                    HttpStatusCode.OK to {
                        description = "Student update"
                        body<StudentDTO> { description = "Update Student" }
                    }
                    HttpStatusCode.BadRequest to {
                        description = "Invalidate some fields"
                        body<String> { description = "Exception" }
                    }
                }
            }) {
                try {
                    val id = call.parameters["id"]!!
                    val request = call.receive<StudentDTO>()
                    val student = studentService.update(UUID.fromString(id), request.toStudent())
                    call.respond(HttpStatusCode.OK, student.toDTO())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            delete("{id}", {
                description = "Delete by id student"
                request {
                    pathParameter<String>("id") {
                        description = "Delete by id student"
                        required = true
                    }
                }

                response {
                    HttpStatusCode.NoContent to {
                        description = "Delete student success"
                    }

                    HttpStatusCode.BadRequest to {
                        description = "Student not found"
                        body<String> { description = "Exception" }
                    }
                }
            }) {
                try {
                    val id = call.parameters["id"]!!
                    val studentDelete = studentService.findById(UUID.fromString(id))
                    studentService.delete(studentDelete)
                    call.respond(HttpStatusCode.NoContent)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.NotFound, e.message.toString())
                }
            }
        }
    }
}
