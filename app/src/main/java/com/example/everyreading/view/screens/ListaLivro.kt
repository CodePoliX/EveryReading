package com.example.everyreading.view.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.everyreading.model.Livros
import com.example.everyreading.view.screens.LivroInt

@Composable
fun ListaLivro(
    livros: List<Livros>,
    modifier: Modifier
){
    LazyColumn (
        modifier = modifier
    ){
        items(livros) { livro ->
            Divider(color = Color.White)
            LivroInt(livro = livro, modifier = Modifier.fillMaxWidth())
        }
    }
}