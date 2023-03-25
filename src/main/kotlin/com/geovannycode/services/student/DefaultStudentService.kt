package com.geovannycode.services.student

import com.geovannycode.models.Student
import com.geovannycode.repository.course.DefaultCourseCachedRepository
import com.geovannycode.repository.student.DefaultStudentCachedRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.*

@Single
class DefaultStudentService(
    @Named("StudentCachedRepository")
    private val repository: DefaultStudentCachedRepository,
    @Named("CourseCachedRepository")
    private val courseRepository: DefaultCourseCachedRepository,
) : StundetService {
    override suspend fun findAll(): Flow<Student> {
        return repository.findAll()
    }

    override suspend fun findById(id: UUID): Student {
        return repository.findById(id) ?: throw Exception("No student exists")
    }

    override suspend fun save(student: Student): Student {
        if (student.courseId != null) {
            val exist = courseRepository.findById(student.courseId!!)
            if (exist == null) {
                System.err.println("Course not found")
                student.courseId = null
            }
        }
        return repository.save(student)
    }
}