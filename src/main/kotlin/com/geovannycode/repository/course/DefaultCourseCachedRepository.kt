package com.geovannycode.repository.course

import com.geovannycode.models.Course
import com.geovannycode.services.cache.CourseCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.*

private val log = KotlinLogging.logger {}

@Single
@Named("CourseCachedRepository")
class DefaultCourseCachedRepository(
    @Named("CourseRepository")
    private val repository: DefaultCourseRepository,
    private val cache: CourseCache,
) : CourseRepository {
    override suspend fun findAll(): Flow<Course> {
        log.info { "Get all Courses" }
        return repository.findAll()
    }

    override suspend fun save(entity: Course): Course = withContext(Dispatchers.IO) {
        log.info { "Caching department and database storage" }
        launch {
            cache.cache.put(entity.id, entity)
        }
        launch {
            repository.save(entity)
        }
        return@withContext entity
    }

    override suspend fun update(id: UUID, entity: Course): Course = withContext(Dispatchers.IO) {
        log.info { "Updating department in cache and database" }
        launch {
            cache.cache.put(id, entity)
        }
        launch {
            repository.update(id, entity)
        }
        return@withContext entity
    }

    override suspend fun delete(entity: Course): Course? = withContext(Dispatchers.IO)  {
        log.info { "Deleting department in cache and database: $entity" }
        val exist = findById(entity.id)
        return@withContext exist?.let {
            launch {
                cache.cache.invalidate(entity.id)
            }
            launch {
                repository.delete(entity)
            }
            return@let exist
        }
    }

    override suspend fun findById(id: UUID): Course? {
        log.info { "Searching for course in cache with id:: $id" }

        var exist = cache.cache.get(id)
        if (exist == null) {
            log.info { "Department not found in cache" }
            exist = repository.findById(id)?.also { cache.cache.put(id, it) }
        }
        return exist
    }
}
