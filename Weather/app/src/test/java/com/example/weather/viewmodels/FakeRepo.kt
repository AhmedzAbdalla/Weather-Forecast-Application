package com.example.weather

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepo(
    private val fakeRemoteSource: FakeRemoteSource,
    private val fakeLocalSource: FakeLocalSource,
) : RepoInterface {
    override suspend fun getForecast(lat: Double, lon: Double): Flow<ForecastModel?> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): Flow<List<ForecastModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertFavorite(forecast: ForecastModel) {
        TODO("Not yet implemented")
    }


    override suspend fun deleteFavorite(timezone: String) {
        TODO("Not yet implemented")
    }


    override suspend fun deleteFavorites() {
        fakeLocalSource.deleteFavorites()
    }

    override suspend fun getAlerts(): Flow<List<AlertItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAlert(alertItem: AlertItem) {
        TODO("Not yet implemented")
    }


    override suspend fun deleteAlert(alertId: Int) {
        fakeLocalSource.deleteAlert(alertId)
    }

    override suspend fun getLanguage(): Flow<String> {
        return flow { fakeLocalSource.getLanguage() }
    }

    override suspend fun getTempUnit(): Flow<String> {
        return flow { fakeLocalSource.getTempUnit() }
    }

    override suspend fun getWindSpeedUnit(): Flow<String> {
        return flow { fakeLocalSource.getWindSpeedUnit() }
    }

    override suspend fun getNotificationFlag(): Flow<Boolean> {
        return flow { fakeLocalSource.getNotificationFlag() }
    }

    override suspend fun setLanguage(lang: String) {
        fakeLocalSource.setLanguage(lang)
    }

    override suspend fun setTempUnit(unit: String) {
        fakeLocalSource.setTempUnit(unit)
    }

    override suspend fun setWindSpeedUnit(unit: String) {
        fakeLocalSource.setWindSpeedUnit(unit)
    }

    override suspend fun setNotificationFlag(notifyFlag: Boolean) {
        fakeLocalSource.setNotificationFlag(notifyFlag)
    }

    override suspend fun setSelectedForecast(forecast: ForecastModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedForecast(): Flow<ForecastModel?> {
        TODO("Not yet implemented")
    }
}