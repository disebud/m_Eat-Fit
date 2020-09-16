package com.weird.eat_n_fit.ui.wallet.transaction


import retrofit2.Call
import retrofit2.http.*

interface TransactionAPI {
    @GET("transaction/{id}")
    fun fetchUserTransactionList(@Path("id") id: String): Call<List<Transaction>>

    @POST("/transaction")
    fun fetchPostNewTransaction(@Body transaction: Transaction): Call<Transaction>

    @GET("topup/history/{id}")
    fun TransactionTopUpList(@Path("id") id: String): Call<List<TransactionWallet>>

    @POST("topup/{id}")
    fun PostTopUp  (@Header("Authorization") token : String, @Body amount: Amount, @Path("id") id: String): Call<RespAmount>

}