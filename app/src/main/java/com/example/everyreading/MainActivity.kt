package com.example.everyreading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.everyreading.view.screens.CadastroPage
import com.example.everyreading.view.screens.HomeScreen
import com.example.everyreading.view.screens.LoginPage
import com.example.everyreading.view.screens.MainScreen
import com.example.everyreading.ui.theme.EveryReadingTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            EveryReadingTheme {
                val navController = rememberNavController()
                val auth = FirebaseAuth.getInstance()

                NavHost(navController = navController, startDestination = "SplashScreen") {
                    composable("SplashScreen"){
                        SplashScreen(navController = navController, viewModel = MainViewModel())
                    }
                    composable("LoginPage") {
                        LoginPage(navController)
                    }
                    composable("MainScreen") {
                        MainScreen()
                    }
                    composable("CadastroPage") {
                        CadastroPage(navController)
                    }
                    composable("Home") {
                        auth.currentUser?.displayName?.let { it1 -> HomeScreen(user = it1, navController = navController) }
                    }
                }
            }
        }
    }
}
class MainViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}
@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {
    LaunchedEffect(key1 = Unit) {
        if (viewModel.isLoggedIn()) {
            navController.navigate("MainScreen")
        } else {
            navController.navigate("LoginPage")
        }
    }
}