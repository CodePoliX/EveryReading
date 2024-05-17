package com.example.everyreading.controller.auth.apibook

import android.util.Log
import com.example.everyreading.model.Livros
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class ApiLivros {
    // Substitua pela URL base da sua API real
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://apibooks-production.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val livroApi = retrofit.create(ApiBooks::class.java)

    // Função suspensa para buscar livros de forma assíncrona
    suspend fun fetchLivros(): List<Livros>? {
        return try {
            val response = livroApi.getLivros().awaitResponse()
            if (response.isSuccessful) {
                Log.d("apiLivros", "fetchLivros: ${response.body()}")
                response.body()
            } else {
                Log.e("ApiLivros", "Erro na resposta: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: HttpException) {
            Log.e("ApiLivros", "Erro na chamada da API: ${e.message}")
            null
        } catch (e: Exception) {
            Log.e("ApiLivros", "Erro ao buscar livros: ${e.localizedMessage}")
            null
        }
    }
}
