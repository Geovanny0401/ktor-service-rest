package com.geovannycode.Database

import com.geovannycode.models.Course
import com.geovannycode.models.Student
import java.util.*

fun getCourses() = listOf(
    Course(
        id = UUID.fromString("cd2cdec5-e14f-4b98-9ab0-7a1eb4a35e7a"),
        name = "Kotlin",
    ),
    Course(
        id = UUID.fromString("a907b4e1-523c-46be-ac72-21184420d18e"),
        name = "Java",
    ),
)

fun getStudents() = listOf(
    Student(
        id = UUID.fromString("54b0a2a6-5135-4ad5-b2b3-2f94c31d30bc"),
        firstName = "Geovanny",
        lastName = "Mendoza",
        email = "geovanny@gmail.com",
        age = 25,
        avatar = "https://cdn-icons-png.flaticon.com/512/2550/2550260.png",
        courseId = getCourses()[0].id,
    ),
    Student(
        id = UUID.fromString("df8da763-994b-4079-9bfe-b7c88c24faec"),
        firstName = "Geovanny",
        lastName = "Mendoza",
        email = "geovanny@gmail.com",
        age = 25,
        avatar = "https://cdn-icons-png.flaticon.com/512/2550/2550260.png",
        courseId = getCourses()[1].id,
    ),
    Student(
        id = UUID.fromString("f3324ddb-4d2d-4856-a5b2-edf1c7d15a7d"),
        firstName = "Geovanny",
        lastName = "Mendoza",
        email = "geovanny@gmail.com",
        age = 25,
        avatar = "https://cdn-icons-png.flaticon.com/512/2550/2550260.png",
    ),
)

//fun getUsers() = listOf(
    // UsuarioDTORegister("Geovanny10", "1234", User.Role.ADMIN.name),
    // UsuarioDTORegister("Maria15", "1234", User.Role.USER.name),
//)
