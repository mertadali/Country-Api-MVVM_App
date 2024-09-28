package com.mertadali.country_api_app.service

import androidx.room.Database
import com.mertadali.country_api_app.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase {

    abstract fun CountryDao() : CountryDAO
}