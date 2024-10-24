package com.giraffe.weatherforecasapplication.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.ForecastModel
import com.example.weather.RepoInterface
import com.example.weather.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeVM(private val repo: RepoInterface) : ViewModel() {
    private val _forecast =
        MutableStateFlow<UiState<ForecastModel?>>(UiState.Loading)
    val forecast: StateFlow<UiState<ForecastModel?>> = _forecast.asStateFlow()

    /*fun getCurrentForecast() {
        _forecast.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCurrent()
                .catch {
                    _forecast.emit(UiState.Fail(it.message ?: "unknown error"))
                }
                .collect {
                    _forecast.emit(it)
                }
        }
    }*/
}

