package com.mertadali.country_api_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mertadali.country_api_app.R
import com.mertadali.country_api_app.databinding.FragmentCountryDeatilBinding
import com.mertadali.country_api_app.util.downloadFromUrl
import com.mertadali.country_api_app.util.placeHolderProgressBar
import com.mertadali.country_api_app.view_model.DetailViewModel


class CountryDeatilFragment : Fragment() {

    private  var countryUuid = 0
    private lateinit var dataBinding: FragmentCountryDeatilBinding
    private lateinit var viewModel : DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_deatil,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryDeatilFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoomDatabase(countryUuid)


        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countriesDetail.observe(viewLifecycleOwner, Observer { country ->
            country.let {

                dataBinding.selectedCountry = country

             /*
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryRegion.text = country.countryRegion
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage

                context?.let {
                    binding.countryFlag.downloadFromUrl(country.countryFlagUrl,
                        placeHolderProgressBar(it)
                    )
                }

                */


            }
        })
    }


}