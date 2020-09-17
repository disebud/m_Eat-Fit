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

    val foodLiveData: MutableLiveData<List<Food>> = foodRepo.foods
    val foodByID: MutableLiveData<Food> = foodRepo.food

    fun getAllFoods(token: String, page: String, limit: String, keyword: String) {
        val authToken = "Bearer $token"
        foodRepo.getAllFoods(authToken, page, limit, keyword)
    }

    fun getFoodByID(token: String, id: String) {
        val authToken = "Bearer $token"
        foodRepo.getFoodByID(authToken, id)
    }
}


