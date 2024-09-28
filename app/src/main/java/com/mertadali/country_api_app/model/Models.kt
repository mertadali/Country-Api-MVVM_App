package com.mertadali.country_api_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// -> Room Database için Entity kullanılacak.

@Entity(tableName = "Country")
data class Country(
    @ColumnInfo(name ="name")
    @SerializedName("name")
    val countryName : String?,
    @ColumnInfo(name ="capital")
    @SerializedName("capital")
    val countryCapital : String?,
    @ColumnInfo(name ="region")
    @SerializedName("region")
    val countryRegion : String?,
    @ColumnInfo(name ="currency")
    @SerializedName("currency")
    val countryCurrency : String?,
    @ColumnInfo(name ="flag")
    @SerializedName("flag")
    val countryFlagUrl : String?,
    @ColumnInfo(name ="language")
    @SerializedName("language")
    val countryLanguage : String?

){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0


}