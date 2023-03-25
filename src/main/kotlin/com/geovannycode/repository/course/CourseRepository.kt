package com.geovannycode.repository.course

import com.geovannycode.models.Course
import com.geovannycode.repository.CrudRepository
import java.util.*

interface CourseRepository : CrudRepository<Course, UUID>
