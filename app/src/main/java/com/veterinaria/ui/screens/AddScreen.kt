package com.veterinaria.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.veterinaria.data.model.MascotaDatabase
import com.veterinaria.data.repository.RepositoryMascota
import com.veterinaria.viewmodel.AddViewModel
import com.veterinaria.ui.components.buttons.PrimaryButton
import com.veterinaria.ui.components.buttons.CancelButton
import com.veterinaria.ui.theme.PurpleGrey40
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(viewModel: AddViewModel, navController: NavController) {


    val nombre by viewModel.nombre.collectAsState()
    val especie by viewModel.especie.collectAsState()
    val selectedImageUri by viewModel.imageUrl.collectAsState()
    val fechaNacimiento by viewModel.fechaNacimientoStr.collectAsState()
    val vacunado by viewModel.vacunado.collectAsState()

    val scope = rememberCoroutineScope()

    val showPreviewImagen = remember { mutableStateOf(false) }
    val context = LocalContext.current


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->

            uri?.let {
                viewModel.onImageUrlChange(it)
            }
        }
    )
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Registrar Nueva Mascota") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            OutlinedTextField(
                value = nombre,
                onValueChange = viewModel::onNombreChange,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
            )


            OutlinedTextField(
                value = especie,
                onValueChange = viewModel::onEspecieChange,
                label = { Text("Especie") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
            )

            OutlinedTextField(
                value = fechaNacimiento,
                onValueChange = viewModel::onFechaNacimientoChange,
                label = { Text("Fecha Nacimiento (dd/MM/yyyy)") },
                modifier = Modifier.fillMaxWidth(),
                // TODO: Reemplazar con un DatePickerDialog
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = vacunado,
                    onCheckedChange = viewModel::onVacunadoChange
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "¿Está vacunado?")
            }

                //Mostar imagen
            if (selectedImageUri != null) {
                AsyncImage(
                    model = selectedImageUri,
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }


            Button(onClick = { galleryLauncher.launch("image/*")  }
            ) {
               showPreviewImagen.value = true
                Text("Seleccionar Imagen")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                
                Box(modifier = Modifier.weight(1f)) {
                    CancelButton(
                        text = "Cancelar",
                        onClick = { navController.popBackStack() }
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    PrimaryButton(
                        text = "Guardar",
                        onClick = {
                            scope.launch {
                                val success = viewModel.savePet(context)

                                if (success) {
                                    navController.popBackStack()
                                }
                            }

                        }
                    )
                }
            }
        }
    }
}



@Preview (showBackground = true)
@Composable
fun AddScreenPreview() {
    val context = LocalContext.current
    val dataBase = remember {
        Room.databaseBuilder(
            context.applicationContext,
            MascotaDatabase::class.java,
            "veterinaria_database"
        ).build()

    }
    val navController = rememberNavController()
    val mascotaRepository = remember { RepositoryMascota(dataBase.mascotaDao()) }
    val addViewModel = remember { AddViewModel(mascotaRepository) }
    AddScreen(viewModel = addViewModel, navController = navController)
}