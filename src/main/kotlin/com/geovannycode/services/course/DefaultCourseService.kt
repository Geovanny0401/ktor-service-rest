package com.geovannycode.services.course

import com.geovannycode.models.Course
import com.geovannycode.repository.course.DefaultCourseCachedRepository
import com.geovannycode.repository.student.DefaultStudentCachedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
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

    override suspend fun update(id: UUID, entity: Course): Course {
        val exist = repository.findById(id)

        exist?.let {
            return repository.update(id, entity)
        } ?: throw Exception("Not found course")
    }

    override suspend fun delete(entity: Course): Course {
        val exist = repository.findById(entity.id)
        exist?.let {
            val students = studentRepository.findAll().toList().filter { it.id == exist.id }
            val count = students.size

            if (count == 0) {
                return repository.delete(exist)!!
            } else {
                throw Exception("It was not possible to delete the course | $count students")
            }
        } ?: throw Exception("Not found course")
    }
}
