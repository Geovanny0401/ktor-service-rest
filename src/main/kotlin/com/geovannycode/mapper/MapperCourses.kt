package com.geovannycode.mapper

import com.geovannycode.dto.CourseDTO
import com.geovannycode.models.Course

fun Course.toDTO(): CourseDTO {
    return CourseDTO(
        name,
    )
}

fun CourseDTO.toCourse(): Course {
    return Course(
        name = name,
    )
}
