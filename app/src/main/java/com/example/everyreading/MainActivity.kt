package com.example.everyreading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.everyreading.screens.CadastroPage
import com.example.everyreading.screens.HomeScreen
import com.example.everyreading.screens.LoginPage
import com.example.everyreading.ui.theme.EveryReadingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EveryReadingTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Home") {
                    composable("Home"){
                       entry -> entry.arguments?.getString("user")?.let { user ->
                           HomeScreen(user)} ?: LaunchedEffect(null){
                               navController.navigate("LoginPage")
                    }
                    }
                    composable("LoginPage") {
                        LoginPage(
                        )
                    }
                    composable("CadastroPage") {
                        CadastroPage(navController)
                    }
                }
            }
        }
    }
}