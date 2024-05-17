package com.example.everyreading.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.everyreading.model.Livros
import com.example.everyreading.ui.theme.Pink300

@Composable
fun LivroInt(livro: Livros, modifier: Modifier){
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (livro.emprestado == true) Pink300 else Color.DarkGray,
            contentColor = Color.White)
    ) {
        Row (Modifier.width(350.dp)){
            AsyncImage(
                modifier = Modifier.width(100.dp),
                model = livro.imagem ?: Icons.Default.Image,
                contentDescription = null,
            )
            Column (modifier = Modifier
                .padding(10.dp)){
                Text(
                    text = "${livro.nome}(${livro.dataLancamento})",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                )
                Text(
                    text = livro.autor,
                    style = TextStyle(fontSize = 17.sp),
                    modifier = Modifier
                )
                Text(
                    text = livro.editora,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                )
                Text(
                    text = "Nota: ${livro.nota}",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                )

            }
        }
    }
}
