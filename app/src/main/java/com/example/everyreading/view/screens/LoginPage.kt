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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.everyreading.R
import com.example.everyreading.controller.auth.EmailAuth
import com.example.everyreading.controller.auth.GoogleAuth
import com.example.everyreading.ui.theme.Grey300
import com.example.everyreading.ui.theme.Pink100
import com.example.everyreading.ui.theme.Pink300
import com.google.firebase.auth.FirebaseAuth

val emailAuth = EmailAuth()
val googleLogin = GoogleAuth()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {
    val activity = LocalContext.current as Activity
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val auth = FirebaseAuth.getInstance()
    var showError by remember { mutableStateOf(false) }
    var showIncorrectPasswordError by remember { mutableStateOf(false) }
    var passwordErrorText by remember { mutableStateOf("") }
    var emailErrorText by remember { mutableStateOf("") }
    // Define o fundo da tela
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Pink100)
        .verticalScroll(rememberScrollState())
    ) {
        // Define a ilustração e o texto da logo
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
                text = "Seja bem vindo(a) de volta!",
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
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                isError = email.isNotEmpty() && !emailAuth.isEmailValid(email),
                value = email,
                onValueChange = {
                    email = it
                    emailErrorText = if (it.isNotEmpty() && !emailAuth.isEmailValid(it)) {
                        "Email inválido"
                    } else {
                        ""
                    }
                },
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = "Icone de Email")
                },
                placeholder = {
                    Text(text = "Email", style = TextStyle(color = Grey300))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                textStyle = TextStyle(color = Pink300),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (email.isNotEmpty() && !emailAuth.isEmailValid(email)) Color.Red else Pink300,
                    unfocusedBorderColor = if (email.isNotEmpty() && !emailAuth.isEmailValid(email)) Color.Red else Pink300,
                    errorBorderColor = Color.Red,
                    cursorColor = Pink300
                ),
                trailingIcon = {
                    if (email.isNotEmpty() && !emailAuth.isEmailValid(email)) {
                        Icon(Icons.Filled.Error, contentDescription = "Ícone de erro")
                    } else {
                        null
                    }
                }
            )
            if (emailErrorText.isNotEmpty()) {
                Text(
                    text = emailErrorText,
                    style = TextStyle(color = Color.Red),
                    modifier = Modifier
                        .padding(start = 25.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
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
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (showIncorrectPasswordError) Color.Red else Pink300,
                    unfocusedBorderColor = if (showIncorrectPasswordError) Color.Red else Pink300,
                    errorBorderColor = Color.Red,
                    cursorColor = Pink300
                ),
                isError =  showIncorrectPasswordError,
            )
            if (showIncorrectPasswordError) {
                Text(
                    text = passwordErrorText,
                    style = TextStyle(color = Color.Red),
                    modifier = Modifier.padding(start = 25.dp)
                )
            }
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
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                onClick = {
                    if (email.isNotEmpty() && senha.isNotEmpty() && emailAuth.isEmailValid(email)) {
                        showError = true
                        auth.signInWithEmailAndPassword(email, senha)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    println("logado")
                                    navController.navigate("Home")
                                } else {
                                    showIncorrectPasswordError = true
                                    passwordErrorText = "Por favor, preencha os campos corretamente."
                                }
                            }
                    }
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

                Spacer(modifier = Modifier.width(1.dp))
                googleLogin.GoogleLoginButton(activity = activity, navController){
                }
                googleLogin.GoogleLoginButton(activity = activity, navController){
                }
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
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
                    .clickable {
                        navController.navigate("CadastroPage")
                    },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }
}