package com.geovannycode.mapper

import com.geovannycode.dto.StudentDTO
import com.geovannycode.models.Student
import java.util.*

fun Student.toDTO(): StudentDTO {
    return StudentDTO(
        firstName = firstName,
        lastName = lastName,
        email = email,
        age = age,
        courseId = courseId.toString(),
        avatar = avatar,
    )
}

fun StudentDTO.toStudent(): Student {
    return Student(
        firstName = firstName,
        lastName = lastName,
        email = email,
        age = age,
        courseId = courseId?.let { UUID.fromString(it) },
        avatar = avatar ?: "https://upload.wikimedia.org/wikipedia/commons/f/f4/User_Avatar_2.png",
    )
}
