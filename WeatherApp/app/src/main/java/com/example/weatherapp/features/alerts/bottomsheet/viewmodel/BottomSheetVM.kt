package com.example.weatherapp.features.alerts.bottomsheet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.alert.AlertItem
import com.example.weatherapp.model.repo.RepoInterface
import com.example.weatherapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BottomSheetVM(val repo: RepoInterface):ViewModel() {
    fun storeAlarm(alertItem: AlertItem) {
        viewModelScope.launch {
            repo.insertAlert(alertItem)
        }
    }
}