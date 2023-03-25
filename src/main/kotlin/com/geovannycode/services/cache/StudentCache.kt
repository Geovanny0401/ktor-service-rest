package com.geovannycode.services.cache

import com.geovannycode.models.Student
import io.github.reactivecircus.cache4k.Cache
import org.koin.core.annotation.Single
import java.util.*
import kotlin.time.Duration.Companion.minutes

@Single
class StudentCache {
    val refreshTime = 1000 * 60

    val cache = Cache.Builder()
        .maximumCacheSize(10)
        .expireAfterAccess(5.minutes)
        .build<UUID, Student>()
}
