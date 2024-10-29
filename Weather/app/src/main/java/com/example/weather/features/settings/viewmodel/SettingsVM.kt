package com.example.weather
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsVM(private val repo: RepoInterface) : ViewModel() {
    private val _notifyFlag = MutableStateFlow(false)
    val notifyFlag: StateFlow<Boolean> = _notifyFlag.asStateFlow()
    fun getNotificationFlag() {
        viewModelScope.launch {
            repo.getNotificationFlag().collect {
                _notifyFlag.value = it
            }
        }
    }
    fun setNotificationFlag(notifyFlag: Boolean) {
        viewModelScope.launch {
            repo.setNotificationFlag(notifyFlag)
        }
    }
}