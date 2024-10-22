package com.example.weather

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.giraffe.weatherforecasapplication.features.home.viewmodel.HomeVM
import com.giraffe.weatherforecasapplication.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeVM
    private lateinit var factory: ViewModelFactory
    private lateinit var sharedVM: SharedVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        factory =
            ViewModelFactory(Repo.getInstance(ApiClient, ConcreteLocalSource(this)))
        viewModel = ViewModelProvider(this, factory)[HomeVM::class.java]
        sharedVM = ViewModelProvider(this, factory)[SharedVM::class.java]


        sharedVM.getForecast(30.83, 31.29)


    }
}