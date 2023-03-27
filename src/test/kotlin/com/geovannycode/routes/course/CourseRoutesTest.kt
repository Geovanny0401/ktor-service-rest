package com.geovannycode.routes.course

import com.geovannycode.mapper.toDTO
import com.geovannycode.models.Course
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CourseRoutesTest {

    private val course = Course(
        name = "Kotlin",
    )

    private val create = course.toDTO()

    @Test
    @Order(1)
    fun getAll() = testApplication {
        environment { config }
        val response = client.get("/api/courses")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    @Order(2)
    fun post() = testApplication {
        environment { config }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val response = client.post("/api/courses") {
            contentType(ContentType.Application.Json)
            setBody(create)
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    @Order(3)
    fun put() = testApplication {
        environment { config }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val response = client.put("/api/courses/${UUID.randomUUID()}") {
            contentType(ContentType.Application.Json)
            setBody(create)
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    @Order(4)
    fun delete() = testApplication {
        environment { config }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val response = client.delete("api/courses/${UUID.randomUUID()}") {}
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
}