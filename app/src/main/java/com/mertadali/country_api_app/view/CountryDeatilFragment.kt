package com.mertadali.country_api_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mertadali.country_api_app.R
import com.mertadali.country_api_app.databinding.FragmentCountryDeatilBinding
import com.mertadali.country_api_app.databinding.FragmentFeedScreenBinding
import com.mertadali.country_api_app.view_model.DetailViewModel


class CountryDeatilFragment : Fragment() {

    private  var countryUuid = 0
    private var _binding: FragmentCountryDeatilBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCountryDeatilBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoomDatabase()

        arguments?.let {
            countryUuid = CountryDeatilFragmentArgs.fromBundle(it).countryUuid
        }
        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countriesDetail.observe(viewLifecycleOwner, Observer { country ->
            country.let {
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryRegion.text = country.countryRegion
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage
            }
        })
    }


}