package com.weird.eat_n_fit.model.order

import com.weird.eat_n_fit.ui.wallet.transaction.Amount
import com.weird.eat_n_fit.ui.wallet.transaction.RespAmount
import com.weird.eat_n_fit.ui.wallet.transaction.Transaction
import com.weird.eat_n_fit.ui.wallet.transaction.TransactionWallet
import retrofit2.Call
import retrofit2.http.*

interface OrderAPI {


    @POST("/transactions")
    fun inputOrder(@Header("Authorization") token : String,@Body orderFood: OrderFoodPacket): Call<ResponseOrder>

}