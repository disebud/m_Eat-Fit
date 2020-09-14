package com.weird.eat_n_fit.ui.sign.signUp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserSignUpApi {
    @POST("auth/register")
    fun loginUser(@Body userSignInModel: UserRegisterModel): Call<UserRegisterResponseData>
}