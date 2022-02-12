package com.example.weatherapplication.ui.favourit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentFavouriteDetailsBinding


class FavouriteDetailsFragment : Fragment() {
lateinit var fragmentBinding:FragmentFavouriteDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment


        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_details, container, false)

        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}