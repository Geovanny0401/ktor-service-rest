package com.geovannycode.services.course

import com.geovannycode.models.Course
import com.geovannycode.repository.course.DefaultCourseCachedRepository
import com.geovannycode.repository.student.DefaultStudentCachedRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.*

@Single
class DefaultCourseService(
    @Named("CourseCachedRepository")
    private val repository: DefaultCourseCachedRepository,
    @Named("StudentCachedRepository")
    private val studentRepository: DefaultStudentCachedRepository,
) : CourseService {
    override suspend fun findAll(): Flow<Course> {
        return repository.findAll()
    }

    override suspend fun findById(id: UUID): Course {
        return repository.findById(id) ?: throw Exception("Course does not exist")
    }

    override suspend fun save(entity: Course): Course {
        return repository.save(entity)
    }
}
