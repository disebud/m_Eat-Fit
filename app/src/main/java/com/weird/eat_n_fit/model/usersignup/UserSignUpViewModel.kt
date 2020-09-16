package com.weird.eat_n_fit.model.usersignup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder
import com.weird.eat_n_fit.model.signin.User

class UserSignUpViewModel: ViewModel() {
    private val signUpRepo : UserSignUpRepository

    init {
        val signUpAPI = RetrofitBuilder.createRetrofit().create(UserSignUpApi::class.java)
        signUpRepo = UserSignUpRepository(signUpAPI)
    }

    val userData: LiveData<User> = signUpRepo.userData
    val isDuplicate: LiveData<Boolean> = signUpRepo.isDuplicate

    fun checkEmail(user: User) {
        println(user)
        signUpRepo.checkEmail(user)
    }

    fun signUp(user: User) {
        println(user)
        signUpRepo.signUp(user)
    }
}