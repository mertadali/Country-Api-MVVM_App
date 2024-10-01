package com.mertadali.country_api_app.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mertadali.country_api_app.model.Country


@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun CountryDao() : CountryDAO

    // Singleton - Design Pattern

    companion object{    //-> Diğer scopelar ulaşabilsin diye

        @Volatile private var instance : CountryDatabase? = null    //->  Multi threading,bir değişkenin birden fazla thread tarafından güvenli bir şekilde erişilebilmesini sağlamak için kullanılır..

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){   //-> elvis control
            instance ?: makeDatabase(context).also {
                instance = it
            }

        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(context.applicationContext
        ,CountryDatabase::class.java,"countrydatabase").build()

    }

}