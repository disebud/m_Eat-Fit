package com.example.enigma_bank.ui.user

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class UserRepository(private val userAPI: UserAPI) {

    val userLiveDataa: MutableLiveData<User> = MutableLiveData<User>()

    fun getUserByID(token:String, id: String) {
        userAPI.getUserByID(token, id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                println("TOKENNYA $token")


                if (response.isSuccessful) {
                    userLiveDataa.value = response.body()
                    println("INI USER getUser ${userLiveDataa.value.toString()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun updateUser(token:String, user:User, id: String) {
        var authToken = "Bearer ${token}"
        userAPI.updateUser(authToken, user, id).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                println("TOKENNYA Response Update $token")
                println("ini Response Update ${response.isSuccessful}")
//                println(response.code())
//                println(response.body())
                if (response.isSuccessful) {
                    userLiveDataa.value = response.body()
                    println("INI USER Update ${userLiveDataa.value.toString()}")
                }
//                println("TOKENNYA $token")

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}