package com.weird.eat_n_fit.ui.sign.signIn

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserSignInAPI {

    @POST("auth/login")
    fun login(@Body signInData: UserSignInModel): Call<SigninResponse>
}


