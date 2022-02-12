package com.example.weatherapplication.ui.mainui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.permisions.TrackingPermisions
import com.example.weatherapplication.ui.currentui.CurrentFragment
import com.example.weatherapplication.ui.nextdays.NextFragment
import com.example.weatherapplication.ui.pagerview.PagerAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import okhttp3.internal.Internal.instance
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.Exception

// EasyPermissions.PermissionCallbacks
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //****************
    lateinit var binding: ActivityMainBinding
    lateinit var actionBarTogel: ActionBarDrawerToggle
    lateinit var pagerAdapter: PagerAdapter
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

//    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)//******
//     //   fetchLocation()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        handelNavigation()
        handelViewPagerwithTap()
        binding.navView.setNavigationItemSelectedListener(this)



    }

//    private fun fetchLocation() {
//        val task = fusedLocationProviderClient.lastLocation
//        if (ActivityCompat.checkSelfPermission(this,
//               android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this,
//                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
//            return
//        }
//
//        task.addOnSuccessListener {
//
//            if (it == null) {
//               // Toast.makeText(this, "${it.latitude} -> ${it.longitude}", Toast.LENGTH_SHORT).show()
//                Log.i("lllllc", "fetchLocation: " + "null")
//
//
//            }
//           else{
//
//               Toast.makeText(this, "${it.latitude} -> ${it.longitude}", Toast.LENGTH_SHORT).show()
////
//                val sharedPreferences: SharedPreferences = this.getSharedPreferences("sharedPrefFile",Context.MODE_PRIVATE)
//
//                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
//                editor.putInt("lat_key",it.latitude.toInt())
//                editor.putInt("lon_key",it.longitude.toInt())
//                editor.apply()
//                editor.commit()
//
//                //
////                val preferences = getSharedPreferences("latlon", MODE_PRIVATE)
////                preferences.edit().putString("latvalue", it.latitude.toString())
////                    .apply()
////                preferences.edit().putString("lonvalue", it.longitude.toString())
////                    .apply()
//
//
//               // currentLangitude=it.longitude
//                //currentLate=it.latitude
//                Log.i("lllllc", "fetchLocation: " + "fine")
//
//            }
//           }
//
//            task.addOnFailureListener {
//                Toast.makeText(this, "${it.localizedMessage}", Toast.LENGTH_SHORT).show()
//
//                Log.i("locccccccccccccc", "fetchLocation: " + "${it.localizedMessage}")
//
//
//            }
//
//
//
//    }
//

    fun handelNavigation() {

        actionBarTogel = ActionBarDrawerToggle(this, binding.myDrawerLayout2,
            R.string.nav_open,
            R.string.nav_close)
        binding.myDrawerLayout2.addDrawerListener(actionBarTogel)
        actionBarTogel.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarTogel.onOptionsItemSelected(item)) {

            true


        } else super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.alert -> {
                //toast("nav_home menu item is selected")
                Log.i("hh", "onNavigationItemSelected: " + "alert")


            }
            R.id.settings -> {
                Log.i("hh", "onNavigationItemSelected: " + "settings")

                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }

        }
        binding.myDrawerLayout2.closeDrawer(GravityCompat.START)
        return true
    }


    fun handelViewPagerwithTap() {

        pagerAdapter = PagerAdapter(this)
        binding.viewPager2.adapter = pagerAdapter
        //connect tab with view pager
        var mediator: TabLayoutMediator = TabLayoutMediator(binding.tabLayout,
            binding.viewPager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {

                    0 -> tab.setText("Current Day")
                    1 -> tab.setText("Next Days ")
                    2 -> tab.setText("Favourite ")

                    else -> tab.setText("Current Day")
                }
            })

        mediator.attach()


    }



}






