package com.example.cvapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cvapp.ui.theme.CVAppTheme

sealed class Destination(val route: String) {
    object Home: Destination("home")
    object Profile: Destination("profile")
    object Portfolio: Destination("portfolio")
    object AboutMe: Destination("about_me")
    object QRcode: Destination("qr_code")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CVAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val navController = rememberNavController()
                    NavigationAppHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationAppHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(Destination.Home.route) { HomeScreen(navController) }
        composable(Destination.Profile.route) { ProfileScreen(navController) }
        composable(Destination.Portfolio.route) { PortfolioScreen(navController) }
        composable(Destination.AboutMe.route) { AboutMeScreen(navController) }
        composable(Destination.QRcode.route) { QRcode(navController) }

    }
}
