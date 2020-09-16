package com.weird.eat_n_fit.ui.home.order

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface OrderAPI {

    @GET("users/{id}")
    fun getOrder(@Header("Authorization") token: String, @Path("id") id: String): Call<Order>

}