package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.model.forecast.ForecastModel
import com.example.weatherapp.model.repo.FakeRepo
import com.example.weatherapp.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SharedVMTest {

   @get:Rule
   val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SharedVM
    private lateinit var fakeRepo: FakeRepo

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepo = FakeRepo()
        viewModel = SharedVM(fakeRepo)
    }

    @Test
    fun `test setting language updates repo and state`() = runTest {
        viewModel.setLanguage("SPANISH")
        assertEquals("SPANISH", viewModel.language.first())
    }



    @Test
    fun `test setting and getting temperature unit`() = runTest {
        viewModel.setTempUnit("FAHRENHEIT")
        assertEquals("FAHRENHEIT", viewModel.tempUnit.first())
    }

    @Test


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
