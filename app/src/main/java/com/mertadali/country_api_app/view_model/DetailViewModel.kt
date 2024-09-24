package com.mertadali.country_api_app.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertadali.country_api_app.model.Country

class DetailViewModel : ViewModel() {
    val countriesDetail = MutableLiveData<Country>()


    fun getDataFromRoomDatabase(){
        val country1 = Country("Turkey","Asia","Ankara","TR","Turkish","www.mert.com")
        countriesDetail.value = country1
    }

}