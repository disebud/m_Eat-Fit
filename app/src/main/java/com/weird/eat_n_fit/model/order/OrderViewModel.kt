package com.weird.eat_n_fit.model.order

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder


 class OrderViewModel : ViewModel(){
    private val orderRepo: OrderRepository

    init {
        val orderAPI = RetrofitBuilder.createRetrofit().create(OrderAPI::class.java)
        orderRepo = OrderRepository(orderAPI)
    }

val orderData: LiveData<ResponseOrder> = orderRepo.orderTransaction
     fun orderFoodPacket(token:String,orderData: OrderFoodPacket) {
         val authToken = "Bearer $token"
         println(orderData)
         orderRepo.orderFoodPacket(authToken,orderData)
     }

}