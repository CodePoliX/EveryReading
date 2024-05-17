@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.everyreading.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.everyreading.controller.auth.apibook.ApiLivros
import com.example.everyreading.model.Livros
import com.example.everyreading.ui.theme.Pink300

@Composable
fun HomeScreen(user: String, navController: NavController) {
    // Estado para armazenar a lista de livros
    val livrosState = remember { mutableStateOf<List<Livros>?>(null) }

    // Inicialize os livros de forma assíncrona
    LaunchedEffect(key1 = true) {
        livrosState.value = ApiLivros().fetchLivros()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Pink300),
                    title = { Text(text = "Olá, $user!", color = Color.White) }
                )
            },
            content = { paddingValues ->
                Surface {
                    Column(modifier = Modifier.padding(paddingValues)) {
                        // Verifique se livrosState.value não é nulo antes de chamar ListaLivro
                        livrosState.value?.let { livros ->
                            ListaLivro(livros = livros, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        )
    }
}
