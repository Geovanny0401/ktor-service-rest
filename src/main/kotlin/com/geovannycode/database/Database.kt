package com.geovannycode.database

import com.geovannycode.models.Course
import com.geovannycode.models.Student
import org.koin.core.annotation.Single
import java.util.*

@Single
class Database {
    val tableStudents = mutableMapOf<UUID, Student>()
    val tableCourses = mutableMapOf<UUID, Course>()
}
