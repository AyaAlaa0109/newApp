package com.example.weatherapplication.ui.pagerview

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.exam.FavouriteFragment
import com.example.weatherapplication.ui.currentui.CurrentFragment
import com.example.weatherapplication.ui.mainui.MainActivity
import com.example.weatherapplication.ui.nextdays.NextFragment

class PagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3    }

    override fun createFragment(position: Int): Fragment {

            when(position) {

                0 -> return CurrentFragment()
                1-> return NextFragment()
                2-> return FavouriteFragment()

                else->return CurrentFragment()
}

    }
}