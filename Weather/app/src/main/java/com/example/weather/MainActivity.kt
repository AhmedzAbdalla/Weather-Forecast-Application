package com.example.weather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.features.favorites.Fragment_Favorite
import com.example.weather.features.home.view.Fragment_home
import com.giraffe.weatherforecasapplication.features.home.viewmodel.HomeVM
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeVM
    private lateinit var factory: ViewModelFactory
    private lateinit var sharedVM: SharedVM
    private lateinit var CurrentFragmentName : TextView

    var bottomNavigationView: BottomNavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        CurrentFragmentName = findViewById(R.id.CurrentFragmentName)


        // Check for internet connection before making any network requests
        if (NetworkUtils.isInternetAvailable(this)) {
            // If internet is available, make network calls
        } else {
            // No internet connection, show a message to the user
            bottomNavigationView?.setSelectedItemId(R.id.nav_favorite) // Replace with the desired item ID

            Toast.makeText(this, "No internet connection available", Toast.LENGTH_SHORT).show()
        }


        //bottomNavigationView = findViewById(R.id.bottom_navigation);

        //MealsRemoteDataSourceImpl temp = MealsRemoteDataSourceImpl.getInstance(this);

        //temp.searchForMeal(this , "Arrabiata");
        // Load default fragment
        if (savedInstanceState == null) {
            //CurrentFragmentName.setText("Home")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Fragment_home()).commit()
        }


        // Check for internet connection before making any network requests
        if (NetworkUtils.isInternetAvailable(this)) {
            // If internet is available, make network calls
        } else {
            // No internet connection, show a message to the user
            //CurrentFragmentName.setText("Favorites")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Fragment_Favorite()).commit()

            Toast.makeText(this, "No internet connection available", Toast.LENGTH_SHORT).show()
        }

        factory =
            ViewModelFactory(Repo.getInstance(ApiClient, ConcreteLocalSource(this)))
        viewModel = ViewModelProvider(this, factory)[HomeVM::class.java]
        sharedVM = ViewModelProvider(this, factory)[SharedVM::class.java]


        sharedVM.getForecast(30.83, 31.29)


        // BottomNavigationView bottomNavigationView = null;
        bottomNavigationView?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            // Use if-else instead of switch-case
            if (item.itemId == R.id.nav_home) {
                selectedFragment = Fragment_home()
                CurrentFragmentName.setText("Home")
                Log.i("TAG", "Home Selected")
            } else if (item.itemId == R.id.nav_favorite) {
                //selectedFragment = new Fragment_Search();
                selectedFragment = Fragment_Favorite()
                CurrentFragmentName.setText("Favorites")
                Log.i("TAG", "Favorites Selected")
            } else if (item.itemId == R.id.nav_settings) {
                selectedFragment = Fragment_Settings()
                CurrentFragmentName.setText("Settings")
                Log.i("TAG", "Settings Selected")
            } else if (item.itemId == R.id.nav_alert) {
                selectedFragment = AlertsFragment()
                CurrentFragmentName.setText("Alerts")
                Log.i("TAG", "Alert Selected")
            }


            //assert selectedFragment != null;
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment!!, "csc"
            ).commit()
            true
        })

    }
}