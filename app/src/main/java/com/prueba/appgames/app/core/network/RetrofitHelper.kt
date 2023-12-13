package com.prueba.appgames.app.core.network

import com.prueba.appgames.app.core.Constantes.BASED_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASED_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}