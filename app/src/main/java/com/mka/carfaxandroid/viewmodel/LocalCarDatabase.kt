package com.mka.carfaxandroid.viewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mka.carfaxandroid.model.LocalCarData

@Database(entities = [LocalCarData::class], version = 6)
abstract class LocalCarDatabase : RoomDatabase() {

    abstract fun localCarDao(): LocalCarDao

    companion object {
        private var instance: LocalCarDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): LocalCarDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, LocalCarDatabase::class.java,
                    "local_car_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
        }
    }

}