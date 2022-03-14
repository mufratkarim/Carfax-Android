package com.mka.carfaxandroid.di

import com.mka.carfaxandroid.model.CarApi
import com.mka.carfaxandroid.model.CarService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://carfax-for-consumers.firebaseio.com/"

    @Provides
    fun providesCarApi() : CarApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CarApi::class.java)
    }

    @Provides
    fun providesCarServices() : CarService {
        return CarService()
    }
}