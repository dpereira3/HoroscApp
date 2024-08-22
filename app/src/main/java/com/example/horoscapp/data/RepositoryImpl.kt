package com.example.horoscapp.data

import android.util.Log
import com.example.horoscapp.data.network.HoroscopeApiService
import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.Repository
import com.example.horoscapp.domain.model.PredictionModel
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        //llamar retrofit
        runCatching {
            apiService.getHoroscope(sign)
        }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure {
                Log.i("Aris","Ha ocurrido un error ${it.message}")
            }
        return null
    }
}