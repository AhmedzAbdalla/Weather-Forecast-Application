package com.example.weatherapp.model.repo

import com.example.weatherapp.model.forecast.ForecastModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoTest {

    private lateinit var repo: Repo
    private lateinit var fakeLocalSource: FakeLocalSource
    private lateinit var fakeApiClient: FakeApiClient

    @Before
    fun setUp() {
        // Initialize FakeLocalSource and FakeApiClient
        fakeLocalSource = FakeLocalSource()
        fakeApiClient = FakeApiClient

        // Initialize Repo with Fake sources
        repo = Repo.getInstance(fakeApiClient, fakeLocalSource)
    }

    @Test
    fun testGetForecast_returnsExpectedForecast() = runTest {
        // Given
        val latitude = 40.7128
        val longitude = -74.0060

        // When
        val result = repo.getForecast(latitude, longitude).first()

        // Then
        assertEquals("GMT", result?.timezone)
        assertEquals(latitude, result?.lat)
        assertEquals(longitude, result?.lon)
        //assertEquals(75.0, result?.current?.clouds, 0.1)
    }

    @Test
    fun testInsertAndRetrieveFavorites() = runTest {
        // Given
        val favoriteForecast = ForecastModel(
            lat = 40.7128,
            lon = -74.0060,
            timezone = "GMT",
            timezone_offset = 0.0,
            current = null,
            daily = emptyList(),
            hourly = emptyList(),
            isCurrent = false,
            isFavorite = true,
            alerts = emptyList()
        )

        // When
        repo.insertFavorite(favoriteForecast)
        val favorites = repo.getFavorites().first()

        // Then
        assertEquals(1, favorites.size)
        assertEquals(favoriteForecast, favorites[0])
    }

    @Test
    fun testDeleteFavorite() = runTest {
        // Given
        val favoriteForecast = ForecastModel(
            lat = 40.7128,
            lon = -74.0060,
            timezone = "GMT",
            timezone_offset = 0.0,
            current = null,
            daily = emptyList(),
            hourly = emptyList(),
            isCurrent = false,
            isFavorite = true,
            alerts = emptyList()
        )

        // Insert the favorite forecast
        repo.insertFavorite(favoriteForecast)

        // When
        repo.deleteFavorite("GMT")
        val favorites = repo.getFavorites().first()

        // Then
        assertEquals(0, favorites.size)
    }



    @Test
    fun testSetAndRetrieveLanguage() = runTest {
        // Given
        val language = "fr"

        // When
        repo.setLanguage(language)
        val result = repo.getLanguage().first()

        // Then
        assertEquals(language, result)
    }

    @Test
    fun testSetAndRetrieveNotificationFlag() = runTest {
        // Given
        val notifyFlag = true

        // When
        repo.setNotificationFlag(notifyFlag)
        val result = repo.getNotificationFlag().first()

        // Then
        assertEquals(notifyFlag, result)
    }
}
