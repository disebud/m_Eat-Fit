package com.weird.eat_n_fit.ui.home.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enigma_bank.ui.user.User
import com.example.enigma_bank.ui.user.UserAPI
import com.example.enigma_bank.ui.user.UserRepository
import com.weird.eat_n_fit.config.RetrofitBuilder

class OrderViewModel : ViewModel() {
    val orderList = mutableListOf<OrderList>(
        OrderList(   "1235513114",
            "180000","enak sekali",
            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
        OrderList( "2423442",
            "180000","enak sekali",
            "https://cdn0-production-images-kly.akamaized.net/pRSX09qL0wIxOc3WDYpw0Oih9iA=/673x379/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3129172/original/099632200_1589527804-shutterstock_1455941861.jpg"),
        OrderList( "42342r225",
            "180000","enak sekali",
            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
        OrderList(  "52352525",
            "180000","enak sekali",
            "https://www.maangchi.com/wp-content/uploads/2019/07/haemulkimchibokkeumbap.jpg")
    )

    val orderLiveData: MutableLiveData<MutableList<OrderList>> = MutableLiveData(orderList)

    private val orderRepo :OrderRepository

    init {
        val orderAPI = RetrofitBuilder.createRetrofit().create(OrderAPI::class.java)
        orderRepo = OrderRepository(orderAPI)
    }

    val order: LiveData<Order> = orderRepo.order

    fun getOrder(token: String, id: String) {
        val authToken = "Bearer $token"
        orderRepo.getOrder(authToken, id)
    }
}
class OrderList(name: String, price: String, desc: String,image:String) {

    var name = name
    var price = price
    var desc = desc
    var imageUrl = image

}
