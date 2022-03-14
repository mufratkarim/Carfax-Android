package com.mka.carfaxandroid.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.mka.carfaxandroid.model.Listings
import com.mka.carfaxandroid.model.LocalCarData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CarRepository(application: Application) {

    private var localCarDao: LocalCarDao
    private var localCars: LiveData<List<LocalCarData>>

    private val database = LocalCarDatabase.getInstance(application)

    init {
        localCarDao = database.localCarDao()
        localCars = localCarDao.getAllLocalCars()
    }

    private fun subscribeOnBackground(function: () -> Unit) {
        Single.fromCallable {
            function()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun insertAll(localCarDataList: List<Listings>) {

        for (listing in localCarDataList) {
            val localCarData = LocalCarData(
                null,
                listing.images!!.firstPhoto!!.large,
                listing.year,
                listing.make,
                listing.model,
                listing.trim,
                listing.currentPrice,
                listing.mileage,
                listing.dealer?.city,
                listing.dealer?.state,
                listing.interiorColor,
                listing.exteriorColor,
                listing.drivetype,
                listing.transmission,
                listing.engine,
                listing.bodytype,
                listing.dealer?.phone,
                listing.fuel
            )

            subscribeOnBackground {
                localCarDao.insert(localCarData)
            }
        }
    }

    fun insert(localCarData: LocalCarData) {
        subscribeOnBackground {
            localCarDao.insert(localCarData)
        }
    }

    fun getLocalCar(id: Int): LiveData<LocalCarData> {
        return localCarDao.getLocalCar(id)
    }

    fun delete(localCarData: LocalCarData) {
        subscribeOnBackground {
            localCarDao.delete(localCarData)
        }
    }

    fun deleteAllLocalCars() {
        subscribeOnBackground {
            localCarDao.deleteAllLocalCars()
        }
    }

    fun getAllLocalCars(): LiveData<List<LocalCarData>> {
        return localCars
    }

}