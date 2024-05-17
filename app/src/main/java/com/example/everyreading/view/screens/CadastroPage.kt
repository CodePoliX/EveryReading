package com.example.everyreading.view.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.everyreading.R
import com.example.everyreading.controller.auth.EmailAuth
import com.example.everyreading.ui.theme.Grey300
import com.example.everyreading.ui.theme.Pink100
import com.example.everyreading.ui.theme.Pink300

@Composable
fun CadastroPage(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val emailAuth = EmailAuth()
    val activity = LocalContext.current as Activity

    // Define o fundo da tela
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Pink100)
        .verticalScroll(rememberScrollState())
    ) {
        // Define o formulário de login
        Card(
            modifier = Modifier
                .align(Center)
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .padding(bottom = 68.dp, top = 20.dp),
            shape = RoundedCornerShape(72.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White, // Cor do fundo do card
                contentColor = Color.Gray // Cor do conteúdo do card, por exemplo, texto
            ),
        ) {
            // Define a ilustração e o texto da logo
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Beauty illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Text(
                modifier = Modifier
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
                value = username,
                onValueChange = { username = it },
                leadingIcon = {
                    Icon( Icons.Filled.Person, contentDescription = "Icone de usuário" )
                },
                placeholder = {
                    Text(text = "Nome Completo", style = TextStyle(color = Grey300))
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
                value = telefone,
                onValueChange = { novoTelefone ->
                    val numerosApenas = novoTelefone.filter { it.isDigit() }
                    if (numerosApenas.length <= 11) { // Defina o número máximo de caracteres
                        val posicaoCursor = formatPhoneNumber(numerosApenas).length
                        telefone = TextFieldValue(
                            text = formatPhoneNumber(numerosApenas),
                            selection = TextRange(posicaoCursor)
                        ).text
                    }
                },
                leadingIcon = {
                    Icon( Icons.Filled.Phone, contentDescription = "Icone de telefone" )
                },
                placeholder = {
                    Text(text = "Telefone", style = TextStyle(color = Grey300))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
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
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
                    .width(200.dp),
                onClick = {
                    val user: Map<String, String> = mapOf(
                        "username" to username,
                        "email" to email,
                        "senha" to senha,
                        "telefone" to telefone
                    )
                    emailAuth.cadastrarUsuario(user)
                    navController.navigate("LoginPage")
                },
                colors = ButtonDefaults.buttonColors(Pink300),
            ) {
                Text(text = "Cadastrar",
                    style = TextStyle(color = Color.Black,
                        fontWeight = FontWeight.W800))
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Spacer(modifier = Modifier.width(1.dp))
                googleLogin.GoogleLoginButton(activity = activity, navController){
                }
                googleLogin.GoogleLoginButton(activity = activity, navController){
                }
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Já tem conta?\n",
            modifier = Modifier.align(Alignment.BottomCenter),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Pink300
            )
        )
        Text(
            text = "Fazer login!",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                           navController.navigate("LoginPage")
                },
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}

fun formatPhoneNumber(phoneNumber: String): String {
    val cleanedPhoneNumber = phoneNumber.replace(Regex("[^\\d]"), "")

    return when {
        cleanedPhoneNumber.length <= 2 -> cleanedPhoneNumber
        cleanedPhoneNumber.length <= 6 -> "(${cleanedPhoneNumber.substring(0, 2)}) ${cleanedPhoneNumber.substring(2)}"
        cleanedPhoneNumber.length <= 10 -> "(${cleanedPhoneNumber.substring(0, 2)}) ${cleanedPhoneNumber.substring(2, 6)}-${cleanedPhoneNumber.substring(6)}"
        else -> "(${cleanedPhoneNumber.substring(0, 2)}) ${cleanedPhoneNumber.substring(2, 6)}-${cleanedPhoneNumber.substring(6, 11)}"
    }
}




