package com.example.weatherapplication.ui.mainui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var  binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_splash_screen)
        binding.ivSplash.alpha=0f

        binding.ivSplash.animate().setDuration(3000).alpha(1f).withEndAction{

        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
       finish()
        }

    }
}