package com.geovannycode.repository.student

import com.geovannycode.models.Student
import com.geovannycode.services.DataBaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

private val log = KotlinLogging.logger {}

@Single
@Named("StudentRepository")
class DefaultStudentRepository(private val dataBaseService: DataBaseService) : StudentRepository {
    override suspend fun findAll(): Flow<Student> {
        log.info { "Obtaining all the Students" }
        return dataBaseService.getTables().tableStudents.values.asFlow()
    }

    override suspend fun save(entity: Student): Student {
        log.info { "Student Storage ${entity.firstName} ${entity.lastName}" }
        dataBaseService.getTables().tableStudents[entity.id] = entity
        return dataBaseService.getTables().tableStudents[entity.id]!!
    }
}
