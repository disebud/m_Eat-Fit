package com.weird.eat_n_fit.model.signin

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class SignInRepository(private val UserSignInAPI: UserSignInAPI) {

    val userData: MutableLiveData<SigninResponse> = MutableLiveData<SigninResponse>()

    fun signIn(UserSignInData: UserSignInModel) {
        UserSignInAPI.login(UserSignInData).enqueue(object : Callback<SigninResponse> {
            override fun onResponse(call: Call<SigninResponse>, response: Response<SigninResponse>) {
                userData.value = response.body()
                println(response.body())
            }

            override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun signOut() {
        userData.value = null
    }

}