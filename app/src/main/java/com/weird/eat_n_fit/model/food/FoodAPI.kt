package com.weird.eat_n_fit.model.food

import com.weird.eat_n_fit.model.packet.WrapperDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodAPI {

    @GET("foods")
    fun getAllFoods(
        @Header("Authorization") token: String,
        @Query("page") page: String,
        @Query("limit") limit: String,
        @Query("keyword") keyword: String = ""
    ): Call<List<Food>>

    @GET("foods/{id}")
    fun getFoodByID(@Header("Authorization") token: String, @Path("id") id: String): Call<Food>

}