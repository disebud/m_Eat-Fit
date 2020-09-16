package com.weird.eat_n_fit.model.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder


class UserViewModel : ViewModel() {

    private val userRepo: UserRepository

    init {
        val userAPI = RetrofitBuilder.createRetrofit().create(UserAPI::class.java)
        userRepo = UserRepository(userAPI)
    }

    val user: LiveData<User> = userRepo.userLiveDataa

    fun getUserInfo() = userRepo.userLiveDataa as LiveData<User>

    fun updateDataUser(token: String, user: User, id: String) = userRepo.updateUser(token,user,id)

    fun getUserByID(token: String, id: String) {
        val authToken = "Bearer $token"
        userRepo.getUserByID(authToken, id)
    }

}