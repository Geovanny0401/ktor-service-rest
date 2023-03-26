package com.geovannycode.services.student

import com.geovannycode.models.Student
import com.geovannycode.repository.student.DefaultStudentCachedRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class DefaultStudentServiceTest {

    @MockK
    private lateinit var repositoryStudentCache: DefaultStudentCachedRepository

    @InjectMockKs
    lateinit var service: DefaultStudentService

    init {
        MockKAnnotations.init(this)
    }

    private val student = Student(
        firstName = "Geovanny",
        lastName = "Mendoza",
        email = "test@test.com",
        age = 25,
        avatar = "avatarTest",
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findAll() = runTest {
        coEvery { repositoryStudentCache.findAll() } returns flowOf(student)
        val result = service.findAll().toList()
        assertTrue(result.isNotEmpty())
        coVerify { repositoryStudentCache.findAll() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findById() = runTest {
        coEvery { repositoryStudentCache.findById(any()) } returns student
        val result = service.findById(student.id)
        assertEquals(student.firstName, result.firstName)
        assertEquals(student.lastName, result.lastName)
        coVerify { repositoryStudentCache.findById(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save() = runTest {
        coEvery { repositoryStudentCache.save(any()) } returns student
        val result = service.save(student)
        assertEquals(student.firstName, result.firstName)
        assertEquals(student.lastName, result.lastName)

        coVerify { repositoryStudentCache.save(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun update() = runTest {
        coEvery { repositoryStudentCache.update(any(), any()) } returns student
        val result = service.update(student.id, student)
        assertEquals(student.firstName, result.firstName)
        assertEquals(student.lastName, result.lastName)
        coVerify { repositoryStudentCache.update(any(), any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete() = runTest {
        coEvery { repositoryStudentCache.findById(any()) } returns student
        coEvery { repositoryStudentCache.delete(any()) } returns student
        val result = service.delete(student)
        assertEquals(student.firstName, result.firstName)
        assertEquals(student.lastName, result.lastName)
        coVerify { repositoryStudentCache.delete(any()) }
    }
}
