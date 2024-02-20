package com.example.everyreading.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.material3.TextFieldDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.everyreading.R
import com.example.everyreading.ui.theme.Grey100
import com.example.everyreading.ui.theme.Grey300
import com.example.everyreading.ui.theme.Pink100
import com.example.everyreading.ui.theme.Pink300
import com.example.everyreading.ui.theme.Pink900

@Composable
fun LoginPage() {
    var email by remember { mutableStateOf("") }
    // Define o fundo da tela
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Pink100)
        .padding(top = 50.dp),
    ) {
        // Define a ilustração e o texto da logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Beauty illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .zIndex(1f) // Coloca a ilustração no topo da pilha
        )
        // Define o formulário de login
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White, // Cor do fundo do card
                contentColor = Color.Gray // Cor do conteúdo do card, por exemplo, texto
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-40).dp) // Move o formulário para cima
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(400.dp),
            shape = RoundedCornerShape(72.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 85.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Seja bem vindo(a) de volta!",
                style = TextStyle(
                    fontFamily = FontFamily.Cursive, // Substitua pelo tipo de fonte que deseja usar
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W900
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                value = email,
                onValueChange = { email = it },
                leadingIcon = {
                    Icon( Icons.Filled.Email, contentDescription = "Icone de Email" )
                },
                placeholder = {
                    Text(text = "Email", style = TextStyle(color = Grey300))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                textStyle = TextStyle(color = Pink300)
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                value = email,
                onValueChange = { email = it },
                leadingIcon = {
                    Icon( Icons.Filled.Lock, contentDescription = "Icone de Email" )
                },
                placeholder = {
                    Text(text = "Senha", style = TextStyle(color = Grey300))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                textStyle = TextStyle(color = Pink300),
                trailingIcon = {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Icone de olho para mostrar a senha")
                }
            )

        }
    }
}

@Composable
@Preview
fun LoginPagePreview(){
    LoginPage()
}

