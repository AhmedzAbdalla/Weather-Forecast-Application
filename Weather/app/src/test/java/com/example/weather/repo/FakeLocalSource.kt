package com.example.weather

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalSource(
    private val forecastList: MutableList<ForecastModel>,
    private val alertsList: MutableList<AlertItem>,
    private val forecast: ForecastModel?,
    private var language:String,
    private var tempUnit:String,
    private var windSpeedUnit:String,
    private var notificationFlag:Boolean,
) : LocalSource {
    override suspend fun getFavorites(): Flow<List<ForecastModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertFavorite(forecast: ForecastModel) {
        TODO("Not yet implemented")
    }


    override suspend fun deleteFavorites() {
        forecastList.clear()
    }

    override suspend fun deleteFavorite(timezone: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAlerts(): Flow<List<AlertItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAlert(alertItem: AlertItem) {
        TODO("Not yet implemented")
    }


    override suspend fun deleteAlert(alertId: Int) {
        alertsList.removeIf { it.id == alertId }
    }

    override suspend fun getLanguage(): Flow<String> {

        TODO("Not yet implemented")

    }

    override suspend fun getTempUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getWindSpeedUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getNotificationFlag(): Flow<Boolean> {
        TODO("Not yet implemented")
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
        notificationFlag = notifyFlag
    }

    override suspend fun setSelectedForecast(forecast: ForecastModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedForecast(): Flow<ForecastModel?> {
        TODO("Not yet implemented")
    }
}