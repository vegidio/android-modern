package io.vinicius.androidcommon.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.vinicius.androidcommon.view.countries.CountryListScreen
import io.vinicius.androidcommon.view.home.HomeScreen

sealed class Screen(val route: String)
{
    object Home: Screen("home")
    object CountryList: Screen("countryList")
}

lateinit var navController: NavHostController

@Composable
fun AppNavigation()
{
    navController = rememberNavController()

    NavHost(navController, Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.CountryList.route) { CountryListScreen() }
    }
}