package com.geovannycode.services.student

import com.geovannycode.models.Student
import kotlinx.coroutines.flow.Flow
import java.util.*

interface StundetService {
    suspend fun findAll(): Flow<Student>
    suspend fun findById(id: UUID): Student
    suspend fun save(student: Student): Student
    //suspend fun update(id: UUID, student: Student): Student
    //suspend fun delete(student: Student): Student
}