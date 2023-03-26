package com.geovannycode.services.course

import com.geovannycode.models.Course
import com.geovannycode.repository.course.DefaultCourseCachedRepository
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class DefaultCourseServiceTest {

    private val course = Course(
        name = "Kotlin",
    )

    @MockK
    private lateinit var repositoryCache: DefaultCourseCachedRepository

    @MockK
    lateinit var repositoryStudent: DefaultStudentCachedRepository

    @InjectMockKs
    lateinit var service: DefaultCourseService

    init {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findAll() = runTest {
        coEvery { repositoryCache.findAll() } returns flowOf(course)

        val result = service.findAll().toList()

        assertTrue(result.isNotEmpty())

        coVerify { repositoryCache.findAll() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findById() = runTest {
        coEvery { repositoryCache.findById(any()) } returns course
        val result = service.findById(course.id)
        assertEquals(course.name, result.name)
        coVerify { repositoryCache.findById(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save() = runTest {
        coEvery { repositoryCache.save(any()) } returns course

        val result = service.save(course)

        assertEquals(course.name, result.name)

        coVerify { repositoryCache.save(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun update() = runTest {
        coEvery { repositoryCache.update(any(), any()) } returns course
        val result = service.update(course.id, course)
        assertEquals(course.name, result.name)
        coVerify { repositoryCache.update(any(), any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete() = runTest {
        coEvery { repositoryCache.findById(any()) } returns course
        coEvery { repositoryCache.delete(any()) } returns course
        coEvery { repositoryStudent.findAll() } returns flowOf()
        val result = service.delete(course)
        assertEquals(course.name, result.name)
        coVerify { repositoryCache.delete(any()) }
    }
}
