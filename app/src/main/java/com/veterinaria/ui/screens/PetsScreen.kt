package com.veterinaria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.veterinaria.R
import com.veterinaria.data.model.Mascota
import com.veterinaria.ui.components.MascotaList
import com.veterinaria.ui.components.PetCard
import com.veterinaria.viewmodel.PetsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetScreen(viewModel: PetsViewModel, navController: NavController) {


    val pets by viewModel.petsUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mascotas Atendidas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    navController.navigate("add_pet")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Registrar Nueva Mascota")
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (pets.isEmpty()) {

                Text(
                    text = "No hay mascotas registradas. Presiona '+' para agregar una.",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                MascotaList(
                    lista = pets,
                    modifier = Modifier.fillMaxSize(),
                    onPetClick = { petId ->
                        navController.navigate("Edit/$petId")
                    }
                )
            }
        }
    }
}
