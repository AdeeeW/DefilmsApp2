package com.adewijayanto.defilmsapp2.ui.splashscreen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import com.adewijayanto.defilmsapp2.R
import com.adewijayanto.defilmsapp2.databinding.ActivitySplashScreenBinding
import com.adewijayanto.defilmsapp2.ui.home.HomeActivity

const val SPLASH_TIME: Long = 8000

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    private lateinit var splashScreenBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        WindowManager.LayoutParams.FLAG_FULLSCREEN

        setContentView(splashScreenBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        },SPLASH_TIME)
    }
}