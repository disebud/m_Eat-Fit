package com.weird.eat_n_fit.ui.sign.register

import com.weird.eat_n_fit.ui.sign.register.UserRegisterModel
import com.weird.eat_n_fit.ui.sign.register.UserRegisterResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterApi {
    @POST("auth/register")
    fun loginUser(@Body userSignInModel: UserRegisterModel): Call<UserRegisterResponseData>
}