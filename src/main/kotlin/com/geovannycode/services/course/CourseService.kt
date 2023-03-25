package com.geovannycode.services.course

import com.geovannycode.models.Course
import kotlinx.coroutines.flow.Flow
import java.util.*

interface CourseService {
    suspend fun findAll(): Flow<Course>
    suspend fun findById(id: UUID): Course
    suspend fun save(entity: Course): Course
}