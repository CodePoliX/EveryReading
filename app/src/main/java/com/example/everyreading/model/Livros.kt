package com.example.everyreading.model

data class Livros(
    var id: Int,
    var nome: String,
    var imagem: String?,
    val autor: String,
    val editora: String,
    val indicacaoIdade: Int,
    val dataLancamento: String,
    var nota: Double,
    var emprestado: Boolean
)
