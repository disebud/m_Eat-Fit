package com.weird.eat_n_fit.model.signin

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserSignInAPI {

    @POST("auth/login")
    fun login(@Body signInData: UserSignInModel): Call<SigninResponse>
}


