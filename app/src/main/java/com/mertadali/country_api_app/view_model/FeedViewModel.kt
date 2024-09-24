package com.mertadali.country_api_app.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertadali.country_api_app.model.Country

class FeedViewModel : ViewModel(){
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

   fun refreshData(){
       val country1 = Country("Türkiye","Ankara","Asia","TL","Turkish","www.adadad.com")
       val country2 = Country("Germany","Berlin","Europe","EUR","German","www.adada.com")
       val country3 = Country("France","Paris","Europe","EUR","French","www.adadas.com")

       val countryList = arrayListOf<Country>(country1,country2,country3)
       countries.value = countryList
       countryError.value = false
       countryLoading.value = false

   }


}