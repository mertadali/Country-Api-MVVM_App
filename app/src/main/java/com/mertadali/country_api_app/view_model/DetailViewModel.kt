package com.mertadali.country_api_app.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertadali.country_api_app.model.Country
import com.mertadali.country_api_app.service.CountryDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val countriesDetail = MutableLiveData<Country>()


    fun getDataFromRoomDatabase(uuid : Int){

        launch {
            val dao = CountryDatabase(getApplication()).CountryDao()
            val country = dao.getOneCountry(uuid)
            countriesDetail.value = country
        }
    }
}