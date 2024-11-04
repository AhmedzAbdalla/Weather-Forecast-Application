package com.example.weatherapp.model.repo

import com.example.weatherapp.model.forecast.*
import com.example.weatherapp.network.RemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object FakeApiClient : RemoteSource {
    override suspend fun getForecast(lat: Double, lon: Double, lang: String): Flow<Response<ForecastModel>> {
        return flow {
            val fakeForecast = ForecastModel(
                lat = lat,
                lon = lon,
                timezone = "GMT",
                timezone_offset = 0.0,
                current = createFakeCurrent(),
                daily = createFakeDaily(),
                hourly = createFakeHourly(),
                isCurrent = true,
                isFavorite = false,
                alerts = createFakeAlerts()
            )
            emit(Response.success(fakeForecast))
        }
    }

    // Helper function to create a fake Current object
    private fun createFakeCurrent(): Current {
        return Current(
            clouds = 75.0,
            dew_point = 5.0,
            dt = 1633035600.0,
            feels_like = 20.0,
            humidity = 60.0,
            pressure = 1012.0,
            sunrise = 1632988800.0,
            sunset = 1633032000.0,
            snow = Snow(1.0),
            temp = 22.0,
            uvi = 3.0,
            visibility = 10000.0,
            weather = listOf(Weather("overcast clouds", "04d", 804.0, "Clouds")),
            wind_deg = 200.0,
            wind_gust = 8.0,
            wind_speed = 5.0
        )
    }

    // Helper function to create a list of fake Daily objects
    private fun createFakeDaily(): List<Daily> {
        return listOf(
            Daily(
                dt = 1633065600.0,
                sunrise = 1633048800.0,
                sunset = 1633092000.0,
                moonrise = 1633075200.0,
                moonset = 1633099200.0,
                moon_phase = 0.5,
                temp = Temp(21.0, 18.0, 25.0, 15.0, 16.0, 19.0),
                feels_like = FeelsLike(20.0, 17.0, 24.0, 14.0),
                pressure = 1013.0,
                humidity = 65.0,
                dew_point = 7.0,
                wind_speed = 4.0,
                wind_deg = 180.0,
                wind_gust = 6.0,
                weather = listOf(Weather("overcast clouds", "04d", 804.0, "Clouds")),
                clouds = 10.0,
                pop = 0.1,
                uvi = 5.0,
                rain = 0.5

                )
        )
    }

    // Helper function to create a list of fake Hourly objects
    private fun createFakeHourly(): List<Hourly> {
        return listOf(
            Hourly(
                clouds = 75.0,
                dew_point = 5.0,
                dt = 1633039200.0,
                feels_like = 21.0,
                humidity = 60.0,
                pop = 0.2,
                pressure = 1012.0,
                rain = Rain(0.5),
                temp = 22.0,
                uvi = 3.0,
                visibility = 10000.0,
                weather = listOf(Weather("overcast clouds", "04d", 804.0, "Clouds")),
                wind_deg = 190.0,
                wind_gust = 7.0,
                wind_speed = 4.0
            )
        )
    }

    // Helper function to create a list of fake Alert objects
    private fun createFakeAlerts(): List<Alert> {
        return listOf(
            Alert(
                description = "Heavy rain expected in the afternoon.",
                end = 1633100400,
                event = "Rain Alert",
                sender_name = "Weather Service",
                start = 1633089600,
                tags = listOf("Rain", "Warning")
            )
        )
    }
}
