package com.geovannycode.repostory.course

import com.geovannycode.database.Database
import com.geovannycode.models.Course
import com.geovannycode.repository.course.DefaultCourseRepository
import com.geovannycode.services.DataBaseService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultCourseRepositoryTest {
    private val database = Database()
    private val dataBaseService = DataBaseService(database)

    private var repositoryCourse = DefaultCourseRepository(dataBaseService)

    private val course = Course(
        id = UUID.randomUUID(),
        name = "ktor",
    )

    @BeforeEach
    fun setUp() {
        dataBaseService.clearDataBaseService()
        dataBaseService.initDataBaseService()
    }

    @AfterAll
    fun tearDown() {
        dataBaseService.clearDataBaseService()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findAll() = runTest {
        val result = repositoryCourse.findAll().toList()
        assertNotNull(result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findById() = runTest {
        val result = repositoryCourse.findById(UUID.fromString("cd2cdec5-e14f-4b98-9ab0-7a1eb4a35e7a"))
        assertEquals("Kotlin", result?.name)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save() = runTest {
        val result = repositoryCourse.save(course)
        assertEquals(result.name, course.name)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun update() = runTest {
        val findCourse = repositoryCourse.save(course)
        val update = findCourse.copy(name = "Kotlin_essential")
        val result = repositoryCourse.update(course.id, update)
        assertEquals(result?.name, update.name)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete() = runTest {
        val savedCourse = repositoryCourse.save(course)
        val result = repositoryCourse.delete(savedCourse)
        assertEquals(result?.name, savedCourse.name)
    }
}
