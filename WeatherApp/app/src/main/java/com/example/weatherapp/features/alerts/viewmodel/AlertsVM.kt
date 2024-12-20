package com.example.weatherapp.features.alerts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.alert.AlertItem
import com.example.weatherapp.model.repo.RepoInterface
import com.example.weatherapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AlertsVM(private val repo: RepoInterface) : ViewModel() {
    private val _alerts = MutableStateFlow<UiState<List<AlertItem>>>(UiState.Loading)
    val alerts: StateFlow<UiState<List<AlertItem>>> = _alerts.asStateFlow()
    fun getAlerts() {
        viewModelScope.launch {
            repo.getAlerts()
                .catch {
                    _alerts.emit(UiState.Fail(it.message ?: "unknown error"))
                }
                .collect {
                    _alerts.emit(UiState.Success(it))
                }
        }
    }

    fun deleteAlert(alertId: Int) {
        viewModelScope.launch {
            repo.deleteAlert(alertId)
        }
    }
}