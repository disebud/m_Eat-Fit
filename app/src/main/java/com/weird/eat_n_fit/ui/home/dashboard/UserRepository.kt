package com.example.enigma_bank.ui.user

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class UserRepository(private val userAPI: UserAPI) {

    val user: MutableLiveData<User> = MutableLiveData<User>()

    fun getUserByID(token:String, id: String) {
        userAPI.getUserByID(token, id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                println("TOKENNYA $token")
                if (response.isSuccessful) {
                    user.value = response.body()
                    println("INI USER ${user.value.toString()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}