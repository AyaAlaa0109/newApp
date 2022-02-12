package com .exam

import com.example.weatherapplication.ui.favourit.FavouriteAdapter
import com.example.weatherapplication.ui.favourit.favouriteRepoTest


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.FragmentFavouriteBinding
import com.example.weatherapplication.ui.currentui.CurrentAdapter
import com.example.weatherapplication.ui.favourit.FavouriteDetailsFragment
import com.example.weatherapplication.ui.favourit.InterfaceFvourite
import android.R





class FavouriteFragment : Fragment() ,View.OnClickListener{
lateinit var fragmentFavouriteBinding: FragmentFavouriteBinding
//lateinit var favouriteRepoTest: favouriteRepoTest
lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        fragmentFavouriteBinding = DataBindingUtil.inflate(inflater, com.example.weatherapplication.R.layout.fragment_favourite, container, false)

        fragmentFavouriteBinding.floatingActionButton.setOnClickListener(this);
        return fragmentFavouriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var favouriteList = favouriteRepoTest().favList
        Log.i("mmmmmm", "onViewCreated: "+favouriteList.size)


        favouriteAdapter = FavouriteAdapter(favouriteList , requireContext(),inter)
        val layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        fragmentFavouriteBinding.recFavourite.adapter = favouriteAdapter
        fragmentFavouriteBinding.recFavourite.layoutManager = layoutManager


    }

    override fun onClick(view: View?) {
        if(view?.id  == com.example.weatherapplication.R.id.floating_action_button) {
            Toast.makeText(requireContext(), "item added", Toast.LENGTH_SHORT).show()
        }
        }



  val inter:   InterfaceFvourite= object : InterfaceFvourite {
      override fun openFragment() {

          activity?.getSupportFragmentManager()
              ?.beginTransaction()
              ?.replace(com.example.weatherapplication.R.id.fram_fav_detals, FavouriteDetailsFragment())
              ?.commit()
      }
  }
}