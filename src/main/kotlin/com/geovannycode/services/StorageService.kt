package com.geovannycode.services

import com.geovannycode.config.StorageConfig
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import java.io.File
import java.time.LocalDateTime
import mu.KotlinLogging

private val log = KotlinLogging.logger {}
@Single
class StorageService(
    private val storageConfig: StorageConfig
) {
    fun initStorageDirectory() {
        if (!File(storageConfig.uploadDir).exists()) {
            log.info { "Creating storage directory: ${storageConfig.uploadDir}" }
            File(storageConfig.uploadDir).mkdir()
        } else {
            log.debug { "Data deletion" }
            File(storageConfig.uploadDir).listFiles()?.forEach { it.delete() }
        }
    }

    suspend fun saveFile(name: String, fileBytes: ByteReadChannel): Map<String, String> =
        withContext(Dispatchers.IO) {
            try {
                log.debug { "Saving file: $name" }
                val file = File("${storageConfig.uploadDir}/$name")
                val res = fileBytes.copyAndClose(file.writeChannel())
                log.debug { "File saved in: $file" }

                return@withContext mapOf(
                    "fileName" to name,
                    "createdAt" to LocalDateTime.now().toString(),
                    "size" to res.toString(),
                )
            } catch (e: Exception) {
                throw Exception("Error saving the file: ${e.message}")
            }
        }

    suspend fun getFile(name: String): File = withContext(Dispatchers.IO) {
        log.debug { "Find file: $name" }
        val file = File("${storageConfig.uploadDir}/$name")
        log.debug { "File path: $file" }

        if (!file.exists()) {
            throw Exception("Not found file: $name")
        } else {
            return@withContext file
        }
    }

    suspend fun deleteFile(fileName: String): Unit = withContext(Dispatchers.IO) {
        log.debug { "Delete file in: $fileName" }
        val file = File("${storageConfig.uploadDir}/$fileName")
        log.debug { "File path: $file" }

        if (!file.exists()) {
            throw Exception("Not found file: $fileName")
        } else {
            file.delete()
        }
    }
}