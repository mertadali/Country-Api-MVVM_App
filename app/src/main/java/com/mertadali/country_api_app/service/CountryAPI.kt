package com.mertadali.country_api_app.service

import com.mertadali.country_api_app.model.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountryAPI {
    // Retrofitle verileri çekeceğiz bu sebeple GET - POST İşlemleri için retrofit kullanılır.

    // BASE_URL ->  https://raw.githubusercontent.com/
    // Extensions -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    // RxJava da Single bir veriyi tek defa çekeceğimiz zaman kullanılırz. Observable ise sürekli bir yenileme olursa veride diye kullanılabilir.

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries() : Single<List<Country>>
}