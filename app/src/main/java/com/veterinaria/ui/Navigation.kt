package com.veterinaria.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veterinaria.viewmodel.LoginViewModel
import com.veterinaria.ui.screens.*

@Composable
fun Navigation (){

    val navController = rememberNavController()

    val loginViewModel: LoginViewModel = viewModel()
    val passportViewModel: PassportViewModel = viewModel()

    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            LoginScreen(loginViewModel,navController)

        }
        composable("passport") {

        }
        composable("stamp") {
            StampScreen(passportViewModel, navController)
        }
    }

}

