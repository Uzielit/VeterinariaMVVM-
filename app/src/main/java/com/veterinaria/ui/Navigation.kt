package com.veterinaria.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.veterinaria.data.model.MascotaDatabase
import com.veterinaria.data.repository.RepositoryMascota
import com.veterinaria.viewmodel.LoginViewModel
import com.veterinaria.ui.screens.*
import com.veterinaria.viewmodel.AddViewModel
import com.veterinaria.viewmodel.EditPetsViewModel
import com.veterinaria.viewmodel.ForgotPasswordViewModel
import com.veterinaria.viewmodel.InfoPetsViewModel
import com.veterinaria.viewmodel.PetViewModelFactory
import com.veterinaria.viewmodel.PetsViewModel
import com.veterinaria.viewmodel.RegisterViewModel
import kotlin.jvm.java

@Composable
fun Navigation (){

    val navController = rememberNavController()
    val context = LocalContext.current

    val dataBase = remember {
        Room.databaseBuilder(
            context.applicationContext,
            MascotaDatabase::class.java,
            "veterinaria_database"
        ).build()

    }
    val mascotaRepository = remember { RepositoryMascota(dataBase.mascotaDao()) }
    val factory = remember(mascotaRepository) {
        PetViewModelFactory(mascotaRepository,context)
    }



/*
 composable("petMain") {
            val context = LocalContext.current.applicationContext

            val petRepository = remember {
                // Pasamos el context al repositorio
                PetRepository(RetrofitInstance.api, context)
            }

            val factory = remember(petRepository, context) {
                // Pasamos el context a la factory
                PetViewModelFactory(petRepository, context)
            }

            val viewModel: PetViewModel = viewModel(factory = factory)

            // 4. Tu pantalla funciona sin cambios
            PetScreen(viewModel, navController)
        }
 */



    val forgotViewModel: ForgotPasswordViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()
    val infoPetsViewModel: InfoPetsViewModel = viewModel()

    val registerViewModel: RegisterViewModel = viewModel()



    NavHost(navController = navController, startDestination = "Login") {

        composable("Login") {
            LoginScreen(
                viewModel = loginViewModel,
                navController = navController
            )
        }

        composable("Forgotpassword") {
            ForgotPasswordScreen(navController)
        }

        composable("register") {
            RegisterScreen( navController)
        }

        composable("pets"){
            val petsViewModel = remember { PetsViewModel(mascotaRepository) }
            PetScreen(petsViewModel, navController)
        }

        composable(
            route = "Edit/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.IntType })
        ) { backStackEntry ->
            val petId = backStackEntry.arguments?.getInt("petId")

            if (petId != null) {
                val editViewModel = remember(petId) {
                    EditPetsViewModel(mascotaRepository, petId)
                }
                EditPetsScreen(
                    navController = navController,
                    viewModel = editViewModel
                )
            } else {
                navController.popBackStack() // Si no hay ID, regresa
            }
        }

        composable("add_pet") {
            /*
            val viewModel = remember { AgregarViewModel(repository) }
            AgregarScreen(viewModel = viewModel, navController = navController)
             */
            val addViewModel = remember { AddViewModel(mascotaRepository) }
            AddScreen(viewModel = addViewModel, navController = navController)
        }

        composable("infoPets"){
            InfoPetsScreen( navController)
        }
    }


}

