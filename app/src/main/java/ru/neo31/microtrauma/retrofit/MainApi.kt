package ru.neo31.microtrauma.retrofit

import retrofit2.Response
import retrofit2.http.*

interface MainApi {
    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): Response<User>
}