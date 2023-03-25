package com.geovannycode.Database

import com.geovannycode.dto.UserDTORegister
import com.geovannycode.models.Course
import com.geovannycode.models.Student
import com.geovannycode.models.User
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
        avatar = "https://picsum.photos/id/1/5000/3333",
        courseId = getCourses()[0].id,
    ),
    Student(
        id = UUID.fromString("df8da763-994b-4079-9bfe-b7c88c24faec"),
        firstName = "Teddy",
        lastName = "Mendoza",
        email = "Teddy@gmail.com",
        age = 25,
        avatar = "https://picsum.photos/id/103/2592/1936",
        courseId = getCourses()[1].id,
    ),
    Student(
        id = UUID.fromString("f3324ddb-4d2d-4856-a5b2-edf1c7d15a7d"),
        firstName = "Valeria",
        lastName = "Ahumada",
        email = "valeria@gmail.com",
        age = 25,
        avatar = "https://upload.wikimedia.org/wikipedia/commons/f/f4/User_Avatar_2.png",
    ),
)

fun getUsers() = listOf(
    UserDTORegister("Geovanny10", "1234", User.Role.ADMIN.name),
    UserDTORegister("Maria15", "1234", User.Role.USER.name),
)
