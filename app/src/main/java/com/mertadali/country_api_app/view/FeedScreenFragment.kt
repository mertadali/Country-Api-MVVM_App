package com.mertadali.country_api_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertadali.country_api_app.adapter.CountryAdapter
import com.mertadali.country_api_app.databinding.FragmentFeedScreenBinding
import com.mertadali.country_api_app.view_model.FeedViewModel


class FeedScreenFragment : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private var _binding: FragmentFeedScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.countryListRecycler.layoutManager = LinearLayoutManager(context) //-> Vertical göstereceğimiz için horizontal olsaydı gridLayout olurdu.
        binding.countryListRecycler.adapter = countryAdapter


        binding.refresh.setOnRefreshListener {
            binding.countryListRecycler.visibility = View.GONE
            binding.errorMessage.visibility = View.GONE
            binding.countryLoading.visibility = View.VISIBLE

            viewModel.refreshData()
            binding.refresh.isRefreshing = false
        }

        observeLiveData()


    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { value ->
            value.let {
                binding.countryListRecycler.visibility = View.VISIBLE
                countryAdapter.updateCountryList(value)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error.let {
                if (it){
                    binding.errorMessage.visibility = View.VISIBLE


                }else{
                    binding.errorMessage.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading.let {
                if (it){
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.errorMessage.visibility = View.GONE
                    binding.countryListRecycler.visibility = View.GONE

                }else{
                    binding.countryLoading.visibility = View.GONE

                }
            }
        })
    }


   }

