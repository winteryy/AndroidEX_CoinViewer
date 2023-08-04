package com.example.coinviewer.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.example.coinviewer.MainActivity
import com.example.coinviewer.R
import com.example.coinviewer.databinding.ActivityIntroBinding
import timber.log.Timber

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private val viewModel: IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.d("onCreate")

        viewModel.checkFirstFlag()
        viewModel.first.observe(this, Observer {
            if(it){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                binding.animationView.visibility = View.INVISIBLE
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        })
    }
}