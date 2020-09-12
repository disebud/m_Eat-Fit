package com.example.enigma_bank.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.enigma_bank.config.RetrofitBuilder

class UserViewModel : ViewModel() {

    private val userRepo: UserRepository

    init {
        val userAPI = RetrofitBuilder.createRetrofit().create(UserAPI::class.java)
        userRepo = UserRepository(userAPI)
    }

    val user: LiveData<User> = userRepo.user

    fun getUserByID(token: String, id: String) {
        val authToken = "Bearer $token"
        userRepo.getUserByID(authToken, id)
    }

}