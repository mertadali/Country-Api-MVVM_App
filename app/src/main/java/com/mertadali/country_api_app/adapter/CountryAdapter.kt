package com.mertadali.country_api_app.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mertadali.country_api_app.R
import com.mertadali.country_api_app.databinding.RecyclerRowBinding
import com.mertadali.country_api_app.model.Country
import com.mertadali.country_api_app.view.FeedScreenFragmentDirections


class CountryAdapter(private val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.RowHolder>(), CountryClickedListener {
    class RowHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(inflater, R.layout.recycler_row,parent,false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.binding.country = countryList[position]

        holder.binding.listener = this


        // ----- !!!! DATA BINDING KULLANDIGIMIZ ICIN BU KODLARA IHTIYAC YOK !!!! -----


        /*holder.itemView.setOnClickListener {
            val action = FeedScreenFragmentDirections.actionFeedScreenFragmentToCountryDeatilFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageView.downloadFromUrl(countryList[position].countryFlagUrl,
            placeHolderProgressBar(holder.itemView.context)
        )
*/



    }
    // RefresherLayout kullandığımız için

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)                 // newCountryList'e eklenen yeni elemanlar countryList'e eklenir.
        notifyDataSetChanged()

    }

    override fun onCountryClicked(view: View) {

        val uuid = view.findViewById<TextView>(R.id.uuidTest).text.toString()

        val action = FeedScreenFragmentDirections.actionFeedScreenFragmentToCountryDeatilFragment(uuid.toInt())
        Navigation.findNavController(view).navigate(action)

    }


}

