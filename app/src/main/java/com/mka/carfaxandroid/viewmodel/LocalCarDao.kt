package com.mka.carfaxandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mka.carfaxandroid.model.LocalCarData

@Dao
interface LocalCarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(localCarData: LocalCarData)

    @Delete
    fun delete(localCarData: LocalCarData)

    @Query("SELECT * FROM local_car_data_table WHERE local_car_data_table.id==:id")
    fun getLocalCar(id : Int) : LiveData<LocalCarData>

    @Query("DELETE FROM local_car_data_table")
    fun deleteAllLocalCars()

    @Query("SELECT * FROM local_car_data_table")
    fun getAllLocalCars(): LiveData<List<LocalCarData>>
}