package com.example.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BottomSheetVM(val repo: RepoInterface):ViewModel() {
    fun storeAlarm(alertItem: AlertItem) {
        viewModelScope.launch {
            repo.insertAlert(alertItem)
        }
    }
}