package com.geovannycode.services.cache

import com.geovannycode.models.Course
import io.github.reactivecircus.cache4k.Cache
import org.koin.core.annotation.Single
import java.util.*
import kotlin.time.Duration.Companion.minutes

@Single
class CourseCache {
    val cache = Cache.Builder()
        .maximumCacheSize(10)
        .expireAfterAccess(5.minutes)
        .build<UUID, Course>()
}