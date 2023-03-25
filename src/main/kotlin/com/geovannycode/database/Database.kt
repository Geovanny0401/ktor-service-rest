package com.geovannycode.database

import com.geovannycode.models.Course
import com.geovannycode.models.Student
import com.geovannycode.models.User
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class Database {
    val tableStudents = mutableMapOf<UUID, Student>()
    val tableCourses = mutableMapOf<UUID, Course>()
    val tableUsers = mutableMapOf<UUID, User>()
}
