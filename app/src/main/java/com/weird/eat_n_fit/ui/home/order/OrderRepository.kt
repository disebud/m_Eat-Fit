package com.weird.eat_n_fit.ui.home.order

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderRepository(private val orderAPI: OrderAPI) {
    val order: MutableLiveData<Order> = MutableLiveData<Order>()

    fun getOrder(token:String, id: String) {
        orderAPI.getOrder(token, id).enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                println("TOKENNYA $token")
                if (response.isSuccessful) {
                    order.value = response.body()
                    println("INI USER ${order.value.toString()}")
                }
            }

            override fun onFailure(call: Call<Order>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}