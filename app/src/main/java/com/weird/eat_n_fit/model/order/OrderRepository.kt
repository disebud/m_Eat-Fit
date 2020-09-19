package com.weird.eat_n_fit.model.order

import androidx.lifecycle.MutableLiveData
import com.weird.eat_n_fit.model.food.Food
import com.weird.eat_n_fit.model.signin.SigninResponse
import com.weird.eat_n_fit.model.signin.UserSignInModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderRepository(private val orderAPI: OrderAPI) {
    val orderTransaction: MutableLiveData<ResponseOrder> = MutableLiveData<ResponseOrder>()


    fun orderFoodPacket(token:String ,orderFoodPacket: OrderFoodPacket) {
        orderAPI.inputOrder(token,orderFoodPacket).enqueue(object : Callback<ResponseOrder> {
            override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                orderTransaction.value = response.body()
                println("inputOrder")
                println(response.body())
            }

            override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
  }