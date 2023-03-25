package com.geovannycode.services.student

import com.geovannycode.models.Student
import com.geovannycode.repository.student.DefaultStudentCachedRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

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

    override suspend fun save(student: Student): Student {
        if (student.courseId != null) {
            val existe = courseRepository.findById(student.courseId!!)
            if (existe == null) {
                System.err.println("Course not found")
                student.courseId = null
            }
        }
        return repository.save(student)
    }
}