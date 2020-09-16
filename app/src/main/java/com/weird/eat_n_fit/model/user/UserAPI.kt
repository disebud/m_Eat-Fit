package com.weird.eat_n_fit.model.user

import retrofit2.Call
import retrofit2.http.*

interface UserAPI {

    @GET("users/{id}")
    fun getUserByID(@Header("Authorization") token: String, @Path("id") id: String): Call<User>

    @PUT("user/{id}")
    fun updateUser(@Path("id") id: Int): Call<User>

}