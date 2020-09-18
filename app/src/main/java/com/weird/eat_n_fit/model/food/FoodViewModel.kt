package com.weird.eat_n_fit.model.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder

class FoodViewModel : ViewModel() {
    private val foodRepo: FoodRepository

    init {
        val foodAPI = RetrofitBuilder.createRetrofit().create(FoodAPI::class.java)
        foodRepo = FoodRepository(foodAPI)
    }

//    val foodList: MutableList<Food> = mutableListOf(
//        Food(   "Nasi Goreng",
//            "180000","enak sekali",
//            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
//        Food( "Mie Goreng",
//            "180000","enak sekali",
//            "https://cdn0-production-images-kly.akamaized.net/pRSX09qL0wIxOc3WDYpw0Oih9iA=/673x379/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3129172/original/099632200_1589527804-shutterstock_1455941861.jpg"),
//        Food( "Nasi Goreng",
//            "180000","enak sekali",
//            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
//        Food(  "Nasi Goreng",
//            "180000","enak sekali",
//            "https://www.maangchi.com/wp-content/uploads/2019/07/haemulkimchibokkeumbap.jpg")
//    )

    val foodLiveData: MutableLiveData<List<Food>> = foodRepo.foods
    val foodDetailLiveData: MutableLiveData<Food> = foodRepo.foodsById

    fun getAllFoods(token: String, page: String, limit: String, keyword: String) {
        val authToken = "Bearer $token"
        println(authToken)
        foodRepo.getAllFoods(authToken, page, limit, keyword)
    }
    fun getFoodByID(token: String, id: String) {
        val authToken = "Bearer $token"
        foodRepo.getFoodByID(authToken, id)
    }
}


