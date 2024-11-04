package com.example.weatherapp.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.forecast.ForecastModel
import com.example.weatherapp.model.repo.RepoInterface
import com.example.weatherapp.utils.UiState
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

}

