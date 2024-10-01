package com.mertadali.country_api_app.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mertadali.country_api_app.model.Country

@Dao
interface CountryDAO {

    // -> DATA ACCESS OBJECT

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>  // -> Long ,Primary key döndürecek
    // Insert ->  Insert into
    // vararg -> multiple country object -> Kaç tane veri çekileceği belli olmadığı zaman kullanılır.
    // suspend fun -> Coroutine , pause & resume


    @Query("SELECT * FROM Country")
    suspend fun getAllCountries() : List<Country>

    @Query("DELETE  FROM Country")
    suspend fun deleteAllCountries()

    @Query("SELECT * FROM Country WHERE uuid =:countryId")
    suspend fun getOneCountry(countryId : Int) : Country



}