package com.example.weatherapp.model.repo

import com.example.weatherapp.database.LocalSource
import com.example.weatherapp.model.alert.AlertItem
import com.example.weatherapp.model.forecast.ForecastModel
import com.example.weatherapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLocalSource : LocalSource {

    // In-memory lists for storing data
    private val favoritesList = mutableListOf<ForecastModel>()
    private val alertsList = mutableListOf<AlertItem>()
    private val selectedForecastFlow = MutableStateFlow<ForecastModel?>(null)

    private var language = Constants.Languages.ENGLISH
    private var tempUnit = Constants.TempUnits.CELSIUS
    private var windSpeedUnit = Constants.WindSpeedUnits.METRE
    private var notifyFlag = false

    override suspend fun getFavorites(): Flow<List<ForecastModel>> = flow {
        emit(favoritesList)
    }

    override suspend fun insertFavorite(forecast: ForecastModel) {
        favoritesList.add(forecast)
    }

    override suspend fun deleteFavorite(timezone: String) {
        favoritesList.removeAll { it.timezone == timezone }
    }

    override suspend fun deleteFavorites() {
        favoritesList.clear()
    }

    override suspend fun getAlerts(): Flow<List<AlertItem>> = flow {
        emit(alertsList)
    }

    override suspend fun insertAlert(alertItem: AlertItem) {
        alertsList.add(alertItem)
    }

    override suspend fun deleteAlert(alertId: Int) {
        alertsList.removeAll { it.id == alertId }
    }

    override suspend fun getLanguage(): Flow<String> = flow {
        emit(language)
    }

    override suspend fun getTempUnit(): Flow<String> = flow {
        emit(tempUnit)
    }

    override suspend fun getWindSpeedUnit(): Flow<String> = flow {
        emit(windSpeedUnit)
    }

    override suspend fun getNotificationFlag(): Flow<Boolean> = flow {
        emit(notifyFlag)
    }

    override suspend fun setLanguage(lang: String) {
        language = lang
    }

    override suspend fun setTempUnit(unit: String) {
        tempUnit = unit
    }

    override suspend fun setWindSpeedUnit(unit: String) {
        windSpeedUnit = unit
    }

    override suspend fun setNotificationFlag(notifyFlag: Boolean) {
        this.notifyFlag = notifyFlag
    }

    override suspend fun setSelectedForecast(forecast: ForecastModel) {
        selectedForecastFlow.value = forecast
    }

    override suspend fun getSelectedForecast(): Flow<ForecastModel?> = selectedForecastFlow
}
