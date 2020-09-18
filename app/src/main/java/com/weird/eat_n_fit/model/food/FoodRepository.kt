package com.weird.eat_n_fit.model.food

import androidx.lifecycle.MutableLiveData
import com.weird.eat_n_fit.model.packet.WrapperDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodRepository(private val foodAPI: FoodAPI) {

    val foods: MutableLiveData<List<Food>> = MutableLiveData<List<Food>>()
    val foodsById: MutableLiveData<Food> = MutableLiveData<Food>()

    fun getAllFoods(token: String, page: String, limit: String, keyword: String) {
        foodAPI.getAllFoods(token, page, limit, keyword).enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                foods.value = response.body()
            }

            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
    fun getFoodByID(token:String, id: String) {
        foodAPI.getFoodByID(token, id).enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                println("ini respone ${response.body()}")
                if (response.isSuccessful) {

                    val res= response.body()
                  foodsById.value=res

                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }



}