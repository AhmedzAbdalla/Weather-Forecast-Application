package com.example.weather
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.RepoInterface
import com.example.weather.SharedVM
import com.giraffe.weatherforecasapplication.features.home.viewmodel.HomeVM



class ViewModelFactory(private val repo: RepoInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            HomeVM(repo) as T
        }
        //else if (modelClass.isAssignableFrom(AlertsVM::class.java)) {
        //    AlertsVM(repo) as T
        //} else if (modelClass.isAssignableFrom(SettingsVM::class.java)) {
        //    SettingsVM(repo) as T
        //} else if (modelClass.isAssignableFrom(MapVM::class.java)) {
        //    MapVM(repo) as T
        //}
         else if (modelClass.isAssignableFrom(SharedVM::class.java)) {
            SharedVM(repo) as T
        }
        // else if (modelClass.isAssignableFrom(BottomSheetVM::class.java)) {
        //    BottomSheetVM(repo) as T
        //}
        else {
            throw IllegalArgumentException("can't create ${modelClass.simpleName}")
        }
    }
}