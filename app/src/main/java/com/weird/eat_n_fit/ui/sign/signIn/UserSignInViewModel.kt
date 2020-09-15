package com.weird.eat_n_fit.ui.sign.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder

class UserSignInViewModel : ViewModel() {

    private val signInRepo : SignInRepository

    init {
        val signInAPI = RetrofitBuilder.createRetrofit().create(UserSignInAPI::class.java)
        signInRepo = SignInRepository(signInAPI)
    }

    val userData: LiveData<SigninResponse> = signInRepo.userData

    fun signIn(loginData: UserSignInModel) {
        println(loginData)
        signInRepo.signIn(loginData)
    }

    fun signOut() {
        signInRepo.signOut()
    }
}