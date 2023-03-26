package com.geovannycode.repository.student

import com.geovannycode.models.Student
import com.geovannycode.services.DataBaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.*

private val log = KotlinLogging.logger {}

@Single
@Named("StudentRepository")
class DefaultStudentRepository(private val dataBaseService: DataBaseService) : StudentRepository {
    override suspend fun findAll(): Flow<Student> {
        log.info { "Obtaining all the Students" }
        return dataBaseService.getTables().tableStudents.values.asFlow()
    }

    override suspend fun findById(id: UUID): Student? {
        log.info { "Searching for student with id: $id" }
        return dataBaseService.getTables().tableStudents[id]
    }

    override suspend fun save(entity: Student): Student {
        log.info { "Student Storage ${entity.firstName} ${entity.lastName}" }
        dataBaseService.getTables().tableStudents[entity.id] = entity
        return dataBaseService.getTables().tableStudents[entity.id]!!
    }

    override suspend fun update(id: UUID, entity: Student): Student? {
        log.info { "Update student with id: $id" }
        val student = findById(id) ?: return null
        val studentCopy = entity.copy(
            id = student.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            email = entity.email,
            avatar = entity.avatar,
            courseId = entity.courseId
        )
        student.let {
            dataBaseService.getTables().tableStudents.replace(id, studentCopy)
        }
        return studentCopy
    }

    override suspend fun delete(entity: Student): Student? {
        log.info { "Deleting student with id: ${entity.id}" }
        val student = findById(entity.id) ?: return null
        dataBaseService.getTables().tableStudents.remove(student.id)
        return student
    }
}
