package com.geovannycode.services.course

import com.geovannycode.models.Course
import kotlinx.coroutines.flow.Flow
import java.util.*

interface CourseService {
    suspend fun findAll(): Flow<Course>
    suspend fun findById(id: UUID): Course
    suspend fun save(entity: Course): Course
    suspend fun update(id: UUID, entity: Course): Course
    suspend fun delete(entity: Course): Course
}