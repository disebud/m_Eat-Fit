package com.weird.eat_n_fit.model.packet

import com.weird.eat_n_fit.model.food.Food
import com.weird.eat_n_fit.model.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PacketAPI {

    @GET("packets")
    fun getAllPacket(
        @Header("Authorization") token: String,
        @Query("page") page: String,
        @Query("limit") limit: String,
        @Query("keyword") keyword: String = ""
    ): Call<List<Packet>>

    @GET("packets/{id}")
    fun getPacketByID(@Header("Authorization") token: String, @Path("id") id: String): Call<WrapperDetail>

}