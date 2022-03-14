package com.mka.carfaxandroid.model

import com.mka.carfaxandroid.di.DaggerApiComponent
import javax.inject.Inject

class CarService {

    @Inject
    lateinit var api: CarApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getListings() = api.getListings()
}