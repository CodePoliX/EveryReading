package com.example.everyreading.view.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth

@Composable
fun BottomNavGraph(navController: NavHostController){
    val auth = FirebaseAuth.getInstance()
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(BottomBarScreen.Home.route){
            auth.currentUser?.displayName?.let { it1 -> HomeScreen(user = it1, navController = navController) }
        }
        composable(BottomBarScreen.Profile.route){
            ProfilePage()
        }
    }

}