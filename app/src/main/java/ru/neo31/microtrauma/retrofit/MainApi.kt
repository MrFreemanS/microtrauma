package ru.neo31.microtrauma.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.neo31.microtrauma.dataclasses.User

interface MainApi {
    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): Response<User>

    //заготовка под нормальное получаение данных о юзере
    @POST("auth/user")
    suspend fun getUser(@Body authRequest: AuthRequest,): Response<User>

    //тупо для примера, нужно будет переделать под реально API
    @GET("user/5")
    suspend fun getUserbyID(): Response<User>
}