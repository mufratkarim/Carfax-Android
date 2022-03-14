package com.mka.carfaxandroid.di

import com.mka.carfaxandroid.model.CarService
import com.mka.carfaxandroid.viewmodel.CarViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CarService)
    fun inject(viewModel: CarViewModel)
}