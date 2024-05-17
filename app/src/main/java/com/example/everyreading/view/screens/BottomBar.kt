package com.example.everyreading.view.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person2
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val imageIcon: ImageVector
) {
   object Home: BottomBarScreen(
       route = "Home",
       title = "Home",
       imageIcon = Icons.Filled.Home
   )
    object Profile: BottomBarScreen(
        route = "Profile",
        title = "Perfil",
        imageIcon = Icons.Filled.Person2
    )
}