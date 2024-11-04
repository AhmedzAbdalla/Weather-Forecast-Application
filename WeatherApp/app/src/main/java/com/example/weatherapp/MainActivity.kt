package com.example.weatherapp


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.weatherapp.database.ConcreteLocalSource
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.repo.Repo
import com.example.weatherapp.network.ApiClient
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.ViewModelFactory
import kotlinx.coroutines.launch
import java.util.Locale


class MainActivity : AppCompatActivity(), OnDrawerClick {
    companion object {
        private const val TAG = "MainActivityTag"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var sharedVM: SharedVM
    private lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        factory = ViewModelFactory(Repo.getInstance(ApiClient, ConcreteLocalSource(this)))
        sharedVM = ViewModelProvider(this, factory)[SharedVM::class.java]
        sharedVM.getLanguage()
        lifecycleScope.launch {
            sharedVM.language.collect{
                when(it){
                    Constants.Languages.ENGLISH -> {
                        setLocale(this@MainActivity,"en")
                    }

                    Constants.Languages.ARABIC -> {
                        setLocale(this@MainActivity,"ar")
                    }
                }
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.navigationView, navController)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }


    private fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        @Suppress("DEPRECATION")
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }

}