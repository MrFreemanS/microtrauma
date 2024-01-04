package ru.neo31.microtrauma.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.neo31.microtrauma.dataclasses.User

interface MainApi {
    @POST("auth")
    suspend fun auth(@Body authRequest: AuthRequest): Response<User>

    //заготовка под нормальное получаение данных о юзере
    @POST("user")
    suspend fun getUser(@Header("Authorization") token:String): Response<User>
}