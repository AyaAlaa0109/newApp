package com.example.weatherapplication.location

import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ff {


//    private fun fetchLocation() {
//        val task = mfusedLocationProviderclient.lastLocation
//        if (ActivityCompat.checkSelfPermission(requireContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(requireContext(),
//                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(requireActivity(),
//                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
//            return
//        }
//        task.addOnSuccessListener {
//
//            if (it == null) {
//                //  Toast.makeText(this, "${it.latitude} -> ${it.longitude}", Toast.LENGTH_SHORT).show()
//                MaterialAlertDialogBuilder(requireContext())
//                    .setTitle("Location")
//                    .setMessage("please open the Location to access the location to can use it correctly ")
//
//                    .setNegativeButton("Cancel") { dialog, which ->
//                        // Respond to negative button press
//                        Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
//
//
//                    }
//                    .setPositiveButton("Accept") { dialog, which ->
//                        // Respond to positive button press
//
//                        Toast.makeText(requireContext(), "Accept", Toast.LENGTH_SHORT).show()
//
//                    }
//                    .show()
//
//
//            } else {
//                Toast.makeText(requireContext(),
//                    "${it.latitude} -> ${it.longitude}",
//                    Toast.LENGTH_SHORT).show()
//
//                Log.i("yarab", "fetchLocation: " + "${it.latitude} -> ${it.longitude}")
//
//
////                val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("location",Context.MODE_PRIVATE)
////
////                latt=it.latitude
////                lonn=it.longitude
////
////
////                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
////                editor.putInt("lat",latt.toInt())
////                editor.putInt("lon",lonn.toInt())
////                editor.apply()
////                editor.commit()
////
////                val sharedIdValue = sharedPreferences.getInt("lat",0)
////                val sharedNameValue = sharedPreferences.getInt("lon",0)
////
////
////                Log.i("lllllc", "fetchLocation: " + "fine lat :"+sharedIdValue)
////                Log.i("kkkkkk", "fetchLocation: lon:"+sharedNameValue)
//
//            }
//        }
//
//        task.addOnFailureListener {
//            Toast.makeText(requireContext(), "${it.localizedMessage}", Toast.LENGTH_SHORT).show()
//
//            Log.i("locccccccccccccc", "fetchLocation: " + "${it.localizedMessage}")
//
//
//        }
//
//
//    }

}