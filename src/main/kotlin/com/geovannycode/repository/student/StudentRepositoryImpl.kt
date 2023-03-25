package com.geovannycode.repository.student

import com.geovannycode.models.Student
import com.geovannycode.services.DataBaseService
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.UUID

@Single
@Named("StudentRepository")
class StudentRepositoryImpl(private val dataBaseService: DataBaseService) : StudentRepository {
    override suspend fun findAll(): Flow<Student> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: UUID): Student? {
        TODO("Not yet implemented")
    }

    override suspend fun save(entity: Student): Student {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: UUID, entity: Student): Student? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: Student): Student? {
        TODO("Not yet implemented")
    }
}
