package com.veterinaria.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veterinaria.viewmodel.LoginViewModel
import com.veterinaria.ui.screens.*
import com.veterinaria.viewmodel.AddViewModel
import com.veterinaria.viewmodel.EditPetsViewModel
import com.veterinaria.viewmodel.ForgotPasswordViewModel
import com.veterinaria.viewmodel.InfoPetsViewModel
import com.veterinaria.viewmodel.PetsViewModel
import com.veterinaria.viewmodel.RegisterViewModel

@Composable
fun Navigation (){

    val navController = rememberNavController()

    val addViewModel:  AddViewModel = viewModel()
    val editPets : EditPetsViewModel = viewModel()
    val forgot : ForgotPasswordViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()
    val infoPets : InfoPetsViewModel = viewModel()
    val pets : PetsViewModel = viewModel()
    val register : RegisterViewModel = viewModel()

    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            LoginScreen(loginViewModel,navController)

        }
        composable("Forgotpassword") {
            // 3. CORRECCIÓN: Así se llama la pantalla que acabamos de arreglar.
            //    Ella sola obtendrá su ViewModel.
            ForgotPasswordScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("pets"){
            PetsScreen(pets,navController)
        }
        composable("Edit") {
            // Le pasamos el ViewModel que ya inicializaste arriba
            EditPetsScreen( navController)
        }
        composable( "infoPets"){
            InfoPetsScreen(navController)
        }
        composable("register"){
            RegisterScreen(navController)
        }
    }

}

