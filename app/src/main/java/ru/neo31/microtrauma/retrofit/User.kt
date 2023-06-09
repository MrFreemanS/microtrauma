package ru.neo31.microtrauma.retrofit

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,

    val companyDepartment: String,



    val image: String,
    val token: String
)
