package com.example.weatherapp.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.model.alert.AlertItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class AlertsDaoTest {

    private lateinit var database: AppDataBase
    private lateinit var alertsDao: AlertsDao

    @Before
    fun setup() {
        // Create an in-memory database for testing
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()

        alertsDao = database.getAlertsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAlert_and_getAlerts() = runBlocking {
        // Arrange: Create an alert item
        val startDateTime = LocalDateTime.now()
        val endDateTime = startDateTime.plusHours(1) // End time is 1 hour from now
        val alert = AlertItem(
            startDateTime = startDateTime,
            type = "Weather Warning",
            locationName = "Test Location",
            lat = 40.7128,
            lon = -74.0060,
            endDateTime = endDateTime
        )

        // Act: Insert the alert
        alertsDao.insertAlert(alert)

        // Retrieve the alerts from the database
        val alerts = alertsDao.getAlerts()

        // Assert: Check that the retrieved alert matches the inserted one
        assertEquals(1, alerts.size)
        assertEquals(alert, alerts[0])
    }

    @Test
    fun deleteAlert_and_confirmDeletion() = runBlocking {
        // Arrange: Insert an alert item
        val startDateTime = LocalDateTime.now()
        val endDateTime = startDateTime.plusHours(1)
        val alert = AlertItem(
            startDateTime = startDateTime,
            type = "Weather Warning",
            locationName = "Test Location",
            lat = 40.7128,
            lon = -74.0060,
            endDateTime = endDateTime
        )

        alertsDao.insertAlert(alert)

        // Act: Delete the alert
        alertsDao.deleteAlert(alert.id)

        // Retrieve alerts to confirm deletion
        val alerts = alertsDao.getAlerts()

        // Assert: Ensure the list is empty after deletion
        assertTrue(alerts.isEmpty())
    }
}
