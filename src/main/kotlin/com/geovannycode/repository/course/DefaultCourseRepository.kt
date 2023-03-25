package com.geovannycode.repository.course

import com.geovannycode.models.Course
import com.geovannycode.services.DataBaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.*

private val log = KotlinLogging.logger {}

@Single
@Named("CourseRepository")
class DefaultCourseRepository(private val dataBaseService: DataBaseService) :
    CourseRepository {
    override suspend fun findAll(): Flow<Course> {
        log.info { "Getting all courses..." }

        return dataBaseService.getTables().tableCourses.values.asFlow()
    }

    override suspend fun save(entity: Course): Course {
        log.info { "Storing course ${entity.name}" }
        dataBaseService.getTables().tableCourses[entity.id] = entity
        return dataBaseService.getTables().tableCourses[entity.id]!!
    }

    override suspend fun findById(id: UUID): Course? {
        log.info { "Looking for apartment with id: $id" }

        return dataBaseService.getTables().tableCourses[id]
    }
}
