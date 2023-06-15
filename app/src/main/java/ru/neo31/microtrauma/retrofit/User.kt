package ru.neo31.microtrauma.retrofit

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String,
    val birthDate: String,
    val position: String,
    val company:Company
)

data class Company (
    val department: String,
    val title:String
)