package com.mertadali.country_api_app.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mertadali.country_api_app.R

/* Extension hazır olan yapılara kendi extensionlarımızı yazmaya yarar.

fun String.myExtension(myParamater : String){
    println(myParamater)
}
*/


// Glide resimleri asyc çekmemize olanak sağlayan kütüphane --- Place Holder görünümler inene kadar ne göstereceğimizi ayarlar.



fun ImageView.downloadFromUrl(url  : String?, progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)




    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

// Place Holder bir drawable lazım.

fun placeHolderProgressBar(context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f   // -> Kalınlığı
        centerRadius = 40f
        start()

    }
}

