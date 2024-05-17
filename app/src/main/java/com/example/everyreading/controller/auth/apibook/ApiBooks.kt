package com.example.everyreading.controller.auth.apibook

import com.example.everyreading.model.Livros
import retrofit2.Call
import retrofit2.http.GET

interface ApiBooks {
    @GET("books")  // Substitua "endpoint_de_livros" pelo endpoint real da sua API
    fun getLivros(): Call<List<Livros>>
}

