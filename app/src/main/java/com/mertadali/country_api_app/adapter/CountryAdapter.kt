package com.mertadali.country_api_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mertadali.country_api_app.databinding.RecyclerRowBinding
import com.mertadali.country_api_app.model.Country
import com.mertadali.country_api_app.view.FeedScreenFragment
import com.mertadali.country_api_app.view.FeedScreenFragmentDirections


class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.RowHolder>() {
    class RowHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.binding.name.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion

        holder.itemView.setOnClickListener {
            val action = FeedScreenFragmentDirections.actionFeedScreenFragmentToCountryDeatilFragment()
            Navigation.findNavController(it).navigate(action)
        }

        // RefresherLayout kullandığımız için

        @SuppressLint("NotifyDataSetChanged")
        fun updateCountryList(newCountryList : List<Country>){
            countryList.clear()
            countryList.addAll(newCountryList)                 // newCountryList'e eklenen yeni elemanlar countryList'e eklenir.
            notifyDataSetChanged()

        }
    }

}

