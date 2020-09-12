package com.weird.eat_n_fit.sign.sign_in

import androidx.lifecycle.MutableLiveData
import com.weird.eat_n_fit.ui.sign.signIn.SigninResponse
import com.weird.eat_n_fit.ui.sign.signIn.UserSignInAPI
import com.weird.eat_n_fit.ui.sign.signIn.UserSignInModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class SignInRepository(private val UserSignInAPI: UserSignInAPI) {

    val userData: MutableLiveData<SigninResponse> = MutableLiveData<SigninResponse>()

    fun signIn(UserSignInData: UserSignInModel) {
        UserSignInAPI.login(UserSignInData).enqueue(object : Callback<SigninResponse> {
            override fun onResponse(call: Call<SigninResponse>, response: Response<SigninResponse>) {
                userData.value = response.body()
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