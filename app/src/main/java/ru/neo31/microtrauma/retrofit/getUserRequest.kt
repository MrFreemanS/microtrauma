package ru.neo31.microtrauma.retrofit

data class getUserRequest(
    val username: String,
    val password: String,
    val token: String
)
