package com.geovannycode.repostory.student

import com.geovannycode.models.Student
import com.geovannycode.repository.student.DefaultStudentCachedRepository
import com.geovannycode.repository.student.DefaultStudentRepository
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class DefaultStudentCacheRepositoryTest {

    @MockK
    private lateinit var repositoryStudent: DefaultStudentRepository

    @InjectMockKs
    private lateinit var repositoryCache: DefaultStudentCachedRepository

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
        coEvery { repositoryStudent.findAll() } returns flowOf(student)
        val result = repositoryStudent.findAll().toList()
        assertNotNull(result)
        coVerify { repositoryStudent.findAll() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findById() = runTest {
        coEvery { repositoryStudent.findById(any()) } returns student
        val result = repositoryStudent.findById(student.id)
        assertEquals("Geovanny", result?.firstName)
        assertEquals("Mendoza", result?.lastName)
        coVerify { repositoryStudent.findById(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save() = runTest {
        coEvery { repositoryStudent.save(any()) } returns student
        val result = repositoryStudent.save(student)
        assertEquals(result.firstName, student.firstName)
        coVerify { repositoryStudent.save(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun update() = runTest {
        coEvery { repositoryStudent.update(any(), any()) } returns student
        val result = repositoryStudent.update(student.id, student)
        assertEquals(student.firstName, result!!.firstName)
        coEvery { repositoryStudent.update(any(), any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete() = runTest {
        coEvery { repositoryStudent.findById(any()) } returns student
        coEvery { repositoryStudent.delete(any()) } returns student

        val result = repositoryCache.delete(student)
        assertEquals(student.firstName, result?.firstName)
        coEvery { repositoryStudent.delete(any()) }
    }
}
