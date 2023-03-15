package com.geovannycode.repositories.student

import com.geovannycode.models.Student
import com.geovannycode.repositories.CrudRepository
import java.util.UUID

interface StudentRepository : CrudRepository<Student, UUID>
