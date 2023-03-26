package com.geovannycode.repostory.student

import com.geovannycode.database.Database
import com.geovannycode.models.Student
import com.geovannycode.repository.student.DefaultStudentRepository
import com.geovannycode.services.DataBaseService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultStudentRepositoryTest {

    private val database = Database()
    private val dataBaseService = DataBaseService(database)
    private var repositoryStudent = DefaultStudentRepository(dataBaseService)

    private val student = Student(
        firstName = "Test",
        lastName = "Test",
        email = "test@test.com",
        age = 25,
        avatar = "avatarTest"
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
        val result = repositoryStudent.findAll().toList()
        assertNotNull(result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findById() = runTest {
        val result = repositoryStudent.findById(UUID.fromString("54b0a2a6-5135-4ad5-b2b3-2f94c31d30bc"))
        assertEquals("Geovanny", result?.firstName)
        assertEquals("Mendoza", result?.lastName)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save() = runTest {
        val result = repositoryStudent.save(student)

        assertEquals(result.firstName, student.firstName)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun update() = runTest {
        val savedCourse = repositoryStudent.save(student)
        val update = savedCourse.copy(firstName = "TEST")
        val result = repositoryStudent.update(student.id, update)

        assertEquals(result?.firstName, update.firstName)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete() = runTest {
        val savedCourse = repositoryStudent.save(student)
        val result = repositoryStudent.delete(savedCourse)

        assertEquals(result?.firstName, savedCourse.firstName)
    }
}