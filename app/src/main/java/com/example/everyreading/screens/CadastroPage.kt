package com.example.everyreading.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.everyreading.R
import com.example.everyreading.database.Database
import com.example.everyreading.ui.theme.Grey300
import com.example.everyreading.ui.theme.Pink100
import com.example.everyreading.ui.theme.Pink300

@Composable
fun CadastroPage(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val db: Database
    // Define o fundo da tela
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Pink100)
        .padding(top = 50.dp),
    ) {
        // Define a ilustração e o texto da logo
        Image(
            painter = painterResource(R.drawable.iconbut),
            contentDescription = "Beauty illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .zIndex(1f),
        )
        // Define o formulário de login
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White, // Cor do fundo do card
                contentColor = Color.Gray // Cor do conteúdo do card, por exemplo, texto
            ),
            modifier = Modifier
                .align(Center)
                .padding(horizontal = 20.dp, vertical = 40.dp)
                .fillMaxWidth()
                .height(500.dp)
                .padding(vertical = 20.dp),
            shape = RoundedCornerShape(72.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 85.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Seja bem vindo(a)!",
                style = TextStyle(
                    fontFamily = FontFamily.Cursive, // Substitua pelo tipo de fonte que deseja usar
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W900,
                    color = Pink300
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
                value = senha,
                onValueChange = { senha = it },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(imageVector =
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Icone de olhinho para mostrar senha")
                    }
                }
            )
            Text(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null, // Remove o efeito de fundo ao clicar
                        onClick = { /* Ação ao clicar */ }
                    )
                    .align(Alignment.End)
                    .padding(top = 5.dp, end = 40.dp), // Adiciona uma margem de 16dp
                text = "Esqueci minha senha.",
                style = TextStyle(color = Pink300)
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
                    .width(200.dp),
                onClick = {
                    println("$email e $senha")
                },
                colors = ButtonDefaults.buttonColors(Pink300),
            ) {
                Text(text = "Login",
                    style = TextStyle(color = Color.Black,
                        fontWeight = FontWeight.W800))
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Icon(modifier = Modifier
                    .clickable (
                        interactionSource = interactionSource,
                        indication = null,
                    ){
                    },
                    painter = painterResource(R.drawable.google_icon_foreground),
                    contentDescription = "botão para login com o google",
                    tint = Color.Unspecified)
                Icon(
                    modifier = Modifier
                        .clickable (
                            interactionSource = interactionSource,
                            indication = null,
                        ){
                        },
                    painter = painterResource(R.drawable.facebook_icon_foreground),
                    contentDescription = "botão para login com o google",
                    tint = Color.Unspecified)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Não tem conta ainda?\n",
            modifier = Modifier.align(Alignment.BottomCenter),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Pink300
            )
        )
        Text(
            text = "Cadastrar Agora!",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable { },
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}
