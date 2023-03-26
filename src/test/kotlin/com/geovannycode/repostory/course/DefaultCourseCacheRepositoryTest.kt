package com.geovannycode.repostory.course

import com.geovannycode.models.Course
import com.geovannycode.repository.course.DefaultCourseCachedRepository
import com.geovannycode.repository.course.DefaultCourseRepository
import com.geovannycode.services.cache.CourseCache
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class DefaultCourseCacheRepositoryTest {

    @MockK
    lateinit var repositoryCourse: DefaultCourseRepository

    @SpyK
    var cache = CourseCache()

    @InjectMockKs
    lateinit var repositoryCacheCourse: DefaultCourseCachedRepository

    init {
        MockKAnnotations.init(this)
    }

    private val course = Course(
        id = UUID.randomUUID(),
        name = "ktor",
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findAll() = runTest {
        coEvery { repositoryCourse.findAll() } returns flowOf(course)
        val result = repositoryCourse.findAll().toList()
        assertNotNull(result)
        coVerify { repositoryCourse.findAll() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findById() = runTest {
        coEvery { repositoryCourse.findById(any()) } returns course
        val result = repositoryCourse.findById(course.id)
        assertEquals(course.name, result?.name)
        coVerify { repositoryCourse.findById(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save() = runTest {
        coEvery { repositoryCourse.save(any()) } returns course
        val result = repositoryCourse.save(course)
        assertEquals(course.name, result.name)
        coEvery { repositoryCourse.save(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun update() = runTest {
        coEvery { repositoryCourse.update(any(), any()) } returns course
        val result = repositoryCacheCourse.update(course.id, course)
        assertEquals(course.name, result.name)
        coEvery { repositoryCourse.update(any(), any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete() = runTest {
        coEvery { repositoryCourse.findById(any()) } returns course
        coEvery { repositoryCourse.delete(any()) } returns course

        val result = repositoryCourse.delete(course)
        assertEquals(course.name, result?.name)
        coEvery { repositoryCourse.delete(any()) }
    }
}
