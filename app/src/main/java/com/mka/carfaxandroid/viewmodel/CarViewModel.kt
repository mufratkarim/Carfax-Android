package com.mka.carfaxandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import com.mka.carfaxandroid.di.DaggerApiComponent
import com.mka.carfaxandroid.model.Car
import com.mka.carfaxandroid.model.CarService
import com.mka.carfaxandroid.model.LocalCarData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var carService: CarService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()
    private val repository = CarRepository(application)

    private lateinit var localCar : LiveData<LocalCarData>
    private val allLocalCars = repository.getAllLocalCars()
    val carError = MutableLiveData<Boolean>()

    fun fetchCarInfo() {
        disposable.add(
            carService.getListings()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Car>() {
                    override fun onSuccess(value: Car?) {
                        carError.value = false
                        if (value != null) {
                            repository.insertAll(value.listings)
                        }

                    }

                    override fun onError(e: Throwable?) {
                        carError.value = true
                    }

                })

        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun insert(localCarData: LocalCarData) {
        repository.insert(localCarData)
    }

    fun getLocalCar(id: Int): LiveData<LocalCarData> {
        localCar = repository.getLocalCar(id)
        return localCar
    }

    fun delete(localCarData: LocalCarData) {
        repository.delete(localCarData)
    }

    fun deleteAllLocalCars() {
        repository.deleteAllLocalCars()
    }

    fun getAllLocalCars(): LiveData<List<LocalCarData>> {
        return allLocalCars
    }

}