package com.weird.eat_n_fit.ui.sign.signUp

import androidx.lifecycle.MutableLiveData
import com.weird.eat_n_fit.ui.sign.signIn.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSignUpRepository(private val userSignUpAPI: UserSignUpApi) {

    val userData: MutableLiveData<User> = MutableLiveData<User>()
    var isDuplicate: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun checkEmail(user: User) {
        userSignUpAPI.checkEmailAvailable(user.user_email).enqueue(object :
            Callback<ErrorMessage> {
            override fun onResponse(call: Call<ErrorMessage>, response: Response<ErrorMessage>) {
                val res = response.body()
                isDuplicate.value = res?.code != "200"
            }

            override fun onFailure(call: Call<ErrorMessage>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun signUp(user: User) {
        userSignUpAPI.signUp(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val res = response.body()
                userData.value = res
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}