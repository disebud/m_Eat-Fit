package com.weird.eat_n_fit.ui.sign.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder
import com.weird.eat_n_fit.sign.sign_in.SignInRepository

class UserSignInViewModel : ViewModel() {

    private val signInRepo : SignInRepository

    init {
        val signInAPI = RetrofitBuilder.createRetrofit().create(UserSignInAPI::class.java)
        signInRepo = SignInRepository(signInAPI)
    }

    val userData: LiveData<SigninResponse> = signInRepo.userData

    fun signIn(loginData: UserSignInModel) {
        signInRepo.signIn(loginData)
    }

    fun signOut() {
        signInRepo.signOut()
    }
}