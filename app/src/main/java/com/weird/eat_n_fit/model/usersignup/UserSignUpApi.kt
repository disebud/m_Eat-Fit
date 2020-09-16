package com.weird.eat_n_fit.model.usersignup

import com.weird.eat_n_fit.model.errormessage.ErrorMessage
import com.weird.eat_n_fit.model.signin.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserSignUpApi {
    @POST("auth/register")
    fun signUp(@Body user: User): Call<User>

    @GET("users/email/{email}")
    fun checkEmailAvailable(@Path("email") email: String): Call<ErrorMessage>

}