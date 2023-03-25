package com.geovannycode.repository.student

import com.geovannycode.models.Student
import com.geovannycode.repository.CrudRepository
import java.util.UUID

interface StudentRepository : CrudRepository<Student, UUID>
