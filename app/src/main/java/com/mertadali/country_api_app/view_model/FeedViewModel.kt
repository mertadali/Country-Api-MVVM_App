package com.mertadali.country_api_app.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertadali.country_api_app.model.Country
import com.mertadali.country_api_app.service.CountryAPIService
import com.mertadali.country_api_app.service.CountryDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


class FeedViewModel(application: Application) : BaseViewModel(application){


    private  val countryAPiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

   fun refreshData(){
       getDataFromAPI()

   }

    private fun getDataFromAPI(){
       countryLoading.value = true
        disposable.add(countryAPiService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(t: List<Country>) {
                    storeInSQLite(t)

                }

                override fun onError(e: Throwable) {
                    countryLoading.value = false
                    countryError.value = true
                    e.printStackTrace()

                }
            }))
    }

    private fun whenOnSuccesss(t: List<Country>){
        countries.value = t
        countryLoading.value = false
        countryError.value = false
    }


   private fun storeInSQLite(list : List<Country>){

       // Listedeki elemanın id ile SQLite uuid eşitleyip alma işlemi.
       launch {
           val dao = CountryDatabase(getApplication()).CountryDao()
           dao.deleteAllCountries()
          val listLong = dao.insertAll(*list.toTypedArray())   // List -> individual
           var i = 0
           while (i < list.size){
               list[i].uuid = listLong[i].toInt()
               i = i +1
           }
           whenOnSuccesss(list)
       }
   }









}