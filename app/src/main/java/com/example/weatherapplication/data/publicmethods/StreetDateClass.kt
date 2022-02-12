package com.example.weatherapplication.data.publicmethods

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import java.text.SimpleDateFormat
import java.util.*

class StreetDateClass(var context: Context) {



    fun getStreetName(lat: Int, lon: Int): String {
        val geocoder = Geocoder(context)
        val addresses = geocoder.getFromLocation(lat.toDouble(), lon.toDouble(), 1)
        if (addresses.isEmpty()) return "empty address"
        val address = addresses[0]
        val streetName = address.getAddressLine(0)
        Log.i("lat lang", "onMapClick: $streetName")
        return streetName
        //  tvStreetName.setText(streetName)

    }


     fun getDateTime(s: String): String? {
        //dd/MM/yyyy hh:mm:ss
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date((s).toLong() * 1000)
            return sdf.format(netDate)

        } catch (e: Exception) {
            return e.toString()
        }


    }


}