package com.example.everyreading.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.everyreading.ui.theme.Pink100
import com.example.everyreading.ui.theme.Pink300
import com.example.everyreading.ui.theme.transparent100

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavController){
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar (Modifier.height(65.dp), containerColor = Pink300){
        screens.forEach{
            screen -> 
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)

        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavController
){
    NavigationBarItem(
        icon = {
            Icon(screen.imageIcon, contentDescription = "icone de navegação", Modifier.size(35.dp))
        },
        selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true,
        onClick = {
                  navController.navigate(screen.route)
        },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = Color.White,
            selectedIconColor = Pink100,
            indicatorColor = transparent100
        ),
    )
}