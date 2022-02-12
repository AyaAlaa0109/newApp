package com.example.weatherapplication.ui.nextdays

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentNextBinding
import com.example.weatherapplication.viewmodels.DailyViewModel


class NextFragment : Fragment() {
lateinit var fragmentNextBinding: FragmentNextBinding
lateinit var  dailyViewModel: DailyViewModel
lateinit var nextAdapter: NextAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        activity?.let {
            dailyViewModel = ViewModelProvider(it).get(DailyViewModel::class.java)
        }

        fragmentNextBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_next, container, false)


        return fragmentNextBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(31.026789, 31.0973987, "minutely,hourly", "en", "metric")
        dailyobserveViewModel()


    }
    private fun initViews(lat: Double, lon: Double, exclude: String, lang: String, units: String) {
        dailyViewModel.getWeather(lat,
            lon,
            exclude,
            lang,
            units)
    }


    fun dailyobserveViewModel(){

        dailyViewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {

//
            Log.i("TAGsssssssssss", "onCreateView: " + it.daily.size)
            var currentHourListt = it.daily


            nextAdapter = NextAdapter(currentHourListt, requireContext())
            val layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            fragmentNextBinding.recNext.adapter = nextAdapter
            fragmentNextBinding.recNext.layoutManager = layoutManager



        })

}}