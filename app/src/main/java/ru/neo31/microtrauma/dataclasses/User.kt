package ru.neo31.microtrauma.dataclasses

data class User(
    val user_id: Int,
    val user_login: String,
    val user_full_name: String,
    val gender: String,
    val image: String,
    val token: String,
    val user_birthDate: String,
    val user_position_id: String,
    val user_department_id: String,
    val user_workExperience_in_organization: Int,
    val user_workExperience_all: Int
)

data class Company (
    val department: String,
    val title:String
)