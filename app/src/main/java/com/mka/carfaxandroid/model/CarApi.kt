package com.mka.carfaxandroid.model

import io.reactivex.Single
import retrofit2.http.GET

interface CarApi {

    @GET("assignment.json")
    fun getListings() : Single<Car>

}