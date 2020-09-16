package com.weird.eat_n_fit.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {

//        private const val BASE_URL = "http://34.101.198.49:8082/"
        private const val BASE_URL = "https://b4e5342d462b.ngrok.io/"


        fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}