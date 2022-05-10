package io.jamshid.navoiyterminology

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import io.jamshid.navoiyterminology.databinding.ActivitySplashBinding


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var binding:ActivitySplashBinding?=null
    private val SPLASH_DISPLAY_LENGTH = 1900L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val anim = AlphaAnimation(1.0f,0.0f)
        anim.duration = 2000L
        binding!!.imageView.startAnimation(anim)
        binding!!.tvAppName.startAnimation(anim)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}