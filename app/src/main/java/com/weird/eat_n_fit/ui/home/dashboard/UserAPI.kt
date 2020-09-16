package com.weird.eat_n_fit.ui.home.dashboard

import com.weird.eat_n_fit.ui.home.dashboard.User
import retrofit2.Call
import retrofit2.http.*

interface UserAPI {

    @GET("users/{id}")
    fun getUserByID(@Header("Authorization") token: String, @Path("id") id: String): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Header("Authorization") token : String, @Body user: User, @Path("id") id: String): Call<User>


}