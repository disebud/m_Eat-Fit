package com.weird.eat_n_fit.model.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder


 class OrderViewModel : ViewModel(){
    private val orderRepo: OrderRepository

    init {
        val orderAPI = RetrofitBuilder.createRetrofit().create(OrderAPI::class.java)
        orderRepo = OrderRepository(orderAPI)
    }

     val orderData: LiveData<ResponseOrder> = orderRepo.orderTransaction
     val transactionLiveData: MutableLiveData<List<TransactionUserList>> = orderRepo.ListTransaction
     fun orderFoodPacket(token:String,orderData: OrderFoodPacket) {
         val authToken = "Bearer $token"
         println(orderData)
         orderRepo.orderFoodPacket(authToken,orderData)
     }
     fun getTransaction(token: String, id: String) {
         val authToken = "Bearer $token"
         orderRepo.getTransaction(authToken, id)
     }
     fun PayTransaction(token: String, id: String) {
         val authToken = "Bearer $token"
         orderRepo.PayOrder(authToken, id)
     }
}