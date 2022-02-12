package com.example.weatherapplication.ui.currentui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapplication.R
import com.example.weatherapplication.data.publicmethods.StreetDateClass
import com.example.weatherapplication.databinding.FragmentCurrentBinding
import com.example.weatherapplication.ui.favourit.InterfaceFvourite
import com.example.weatherapplication.ui.mainui.MainActivity
import com.example.weatherapplication.ui.mainui.SettingsActivity
import com.example.weatherapplication.viewmodels.HourlyViewModel
import com.example.weatherapplication.viewmodels.WeatherViewModel
import com.example.weatherapplication.viewmodels.testInterface
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*


class CurrentFragment : Fragment() {
    lateinit var binding: FragmentCurrentBinding
    var myadapter: CurrentAdapter? = null
    private lateinit var viewModel: WeatherViewModel
    private lateinit var hourlyViewModel: HourlyViewModel
     var latFromLocation:Double=0.0
    var lonFromLocation:Double=0.0
    lateinit var mfusedLocationProviderclient: FusedLocationProviderClient
    val TAG = "current"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        activity?.let {
            viewModel = ViewModelProvider(it).get(WeatherViewModel::class.java)
            hourlyViewModel = ViewModelProvider(it).get(HourlyViewModel::class.java)
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_current, container, false)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
///



        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("arf",Context.MODE_PRIVATE)
        val sharedLat = sharedPreferences.getFloat("latt",0.0f)
        val sharedLon = sharedPreferences.getFloat("lonn",0.0f)


        Log.i("arf1", "fetchLocation: " + "fine lat :"+sharedLat)
        Log.i("arf2", "fetchLocation: lon:"+sharedLon)


        initViews(sharedLat.toDouble(), sharedLon.toDouble(), "minutely,hourly", "en", "metric")
        observeViewModel()
        hourlyViewModel.getWeather(sharedLat.toDouble(), sharedLon.toDouble(),
            "minutely,current,daily,alerts",
            "en",
            "metric")
        observeHourly()


    }

    private fun checkSettingAndStartLocationUpdate() {
        val locationSettingsRequest =
            LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!).build()
        val settingsClient: SettingsClient = LocationServices.getSettingsClient(requireContext())
        settingsClient.checkLocationSettings(locationSettingsRequest).addOnSuccessListener(
            OnSuccessListener<Any?> {
                startLocationUpdates()
            }).addOnFailureListener(
            OnFailureListener { e ->
                val errorMessage = e.localizedMessage
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                Log.i(TAG, "onFailure: $errorMessage")
            })
    }

    fun askLocationPermision() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            lastLocation
            checkSettingAndStartLocationUpdate()
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                10)
        }
    }

    //know the action of the user on permision
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //  result code -->refers to the code i made when send permision to user
        //grantresults--->reurn 0 if user not making permision  and more than zero if accept permission
        if (requestCode == 10 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            lastLocation
            checkSettingAndStartLocationUpdate()
        }

    }
///


    private val lastLocation: Unit
        private get() {
            //لازم يتأكد ان واخده الpermission قبل ما يجيب الlast location
            if (ActivityCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            // مش هعمل حاجه في ال if
            mfusedLocationProviderclient?.getLastLocation()
                ?.addOnSuccessListener(OnSuccessListener<Location?> { location ->
                    if (location == null) {
                        Toast.makeText(requireContext(), " no access", Toast.LENGTH_LONG).show()
                        return@OnSuccessListener
                    }
                    Log.i(TAG, "onSuccess: " + location.latitude + "'" + location.latitude )

                    Toast.makeText(requireContext(), " ${location.latitude} + \"'\" +${location.longitude}", Toast.LENGTH_SHORT).show()
               /////

                    val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("arf",Context.MODE_PRIVATE)

                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putFloat("latt",location.latitude.toFloat() )
                editor.putFloat("lonn",location.latitude.toFloat() )
                editor.apply()
                editor.commit()

                val sharedIdValue = sharedPreferences.getFloat("latt",0.0f)
                val sharedNameValue = sharedPreferences.getFloat("lonn",0.0f)


                Log.i("lllllc", "fetchLocation: " + "fine lat :"+sharedIdValue)
                Log.i("kkkkkk", "fetchLocation: lon:"+sharedNameValue)



               /////
                })?.addOnFailureListener(OnFailureListener { e ->
                    val errorMessage = e.localizedMessage
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                    Log.i(TAG, "onFailure: $errorMessage")
                })
        }

    //  get location updates
    var locationRequest: LocationRequest? = null
    private fun initLocationRequest() {
        locationRequest = LocationRequest.create()
       locationRequest!!.setInterval(4000)
       locationRequest!!.setFastestInterval(2000)
        locationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    }


    //start location and stop location (بتوقف ال callback و تشغله  )
    private fun startLocationUpdates() {
// هنا برده محتاج يتأكد انه عمل access permision فملناش دعوه بيها
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mfusedLocationProviderclient?.requestLocationUpdates(locationRequest!!,
            locationCallback,
            Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        mfusedLocationProviderclient?.removeLocationUpdates(locationCallback)
    }

    var locationCallback: LocationCallback = object : LocationCallback() {
        // return the update
        override fun onLocationResult(locationResult: LocationResult) { // ترجع بعد الوقت اللي محدده انها تعمل تعديل فيه
            super.onLocationResult(locationResult)
            //if (locationResult == null) return///#######################################
            val late = locationResult.lastLocation.latitude
            val lng = locationResult.lastLocation.longitude
            latFromLocation= late
            latFromLocation=lng

//            latesms = late
//            langsms = lng
            Log.i(TAG, "onLocationResult: $late,$lng")
            val mylatlang = "latitude= $late \nlangitude= $lng"
            Toast.makeText(requireContext(), mylatlang, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
    }

    override fun onStart() {
        super.onStart()
        initLocationRequest()
        mfusedLocationProviderclient = LocationServices.getFusedLocationProviderClient(requireContext())
        askLocationPermision()
    }


    fun observeHourly() {


        hourlyViewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("TAGsssssssssss", "onCreateView: " + it.hourly.size)
            var currentHourListt = it.hourly
            myadapter = CurrentAdapter(currentHourListt, requireContext())
            val layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            binding.recyclerHourCurrent.adapter = myadapter
            binding.recyclerHourCurrent.layoutManager = layoutManager
        })
    }

    private fun observeViewModel() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "observeViewModel: ${it.lat}")
            context?.let { context ->
                var link = "http://openweathermap.org/img/wn/" + (it.current?.weather?.get(0)?.icon) + "@2x.png"
                Glide.with(context).load(link).into(binding?.imgIcon!!)
                Log.d("TAG", "observeViewModel: ${it.current?.weather?.get(0)?.description}")
            }

            binding?.statusCurrent?.text = it.current?.weather?.get(0)?.description
            binding?.currentHumedity?.text = "Humidity:" + it.current?.humidity.toString() + "%"
            binding?.currentClouds?.text = "Clouds:" + it.current?.clouds.toString() + "%"
            //binding?.currentTempMax?.text = it.current?.sunrise.toString()
            binding?.currentTempMin?.text = "uvi: " + it.current?.uvi.toString()
            binding?.currentDegree?.text = it.current?.temp.toString() + " C"
            binding?.currentPressure?.text = "pressure:" + it.current?.pressure.toString()
            binding?.currentWind?.text = "wind:" + it.current?.windSpeed.toString()
            val convertedDate =  StreetDateClass(requireContext()).getDateTime((it.current?.dt).toString())
            binding?.currentDate?.text = convertedDate
            val address = StreetDateClass(requireContext()).getStreetName(it.lat!!.toInt(), it.lon?.toInt()!!)
            binding.currentAddress.text = address


        })
    }


    private fun initViews(lat: Double, lon: Double, exclude: String, lang: String, units: String) {
        viewModel.getWeather(lat,
            lon,
            exclude,
            lang,
            units)
    }
}




