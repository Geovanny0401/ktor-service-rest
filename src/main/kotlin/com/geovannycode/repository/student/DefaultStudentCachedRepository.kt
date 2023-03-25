package com.geovannycode.repository.student

import com.geovannycode.models.Student
import com.geovannycode.services.cache.StudentCache
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.*

private val log = KotlinLogging.logger {}

@Single
@Named("StudentCachedRepository")
class DefaultStudentCachedRepository(
    @Named("StudentRepository")
    private val repository: DefaultStudentRepository,
    private val cache: StudentCache,
) : StudentRepository {

    private var refreshJob: Job? = null

    init {
        refreshCache()
    }

    private fun refreshCache() {
        if (refreshJob != null) refreshJob?.cancel()

        refreshJob = CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(cache.refreshTime.toLong())

                log.info { "Refresh cache of students" }
                findAll().collect {
                    cache.cache.put(it.id, it)
                }
            }
        }
    }

    override suspend fun findAll(): Flow<Student> {
        log.info { "Find all students" }
        return repository.findAll()
    }

    override suspend fun findById(id: UUID): Student? {
        log.info { "Searching for student in cache with id: $id" }
        var exist = cache.cache.get(id)
        if (exist == null) {
            log.info { "Student not found in cache" }
            exist = repository.findById(id)?.also { cache.cache.put(id, it) }
        }
        return exist
    }

    override suspend fun save(entity: Student): Student = withContext(Dispatchers.IO) {
        log.info { "Storing student in cache and database" }
        launch {
            cache.cache.put(entity.id, entity)
        }
        launch {
            repository.save(entity)
        }
        return@withContext entity
    }
}
