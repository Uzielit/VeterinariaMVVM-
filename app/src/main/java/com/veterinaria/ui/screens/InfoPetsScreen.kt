package com.veterinaria.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.* // Necesario para remember y mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// ==============================================================================
// 1. PANTALLA PRINCIPAL
// ==============================================================================

@Composable
fun InfoPetsScreen(
    navController: NavController,
    onEditClicked: () -> Unit = { println("Navegar a Edición") },
    onDeleteClicked: () -> Unit = { println("Mostrar Diálogo de Eliminación") }
) {
    // ESTADO: Gestión del valor de la fecha de nacimiento (MVVM)
    var fechaNacimiento by remember { mutableStateOf("2024-05-15") }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoPetsHeader()

            PetDisplayArea(petName = "Nombre Mascota")

            Spacer(Modifier.height(32.dp))

            // Módulo 3: Campos de Información
            PetDetailsForm(
                raza = "Golden Retriever",
                fechaNacimiento = fechaNacimiento,
                // Función que actualiza el estado de la fecha
                onFechaNacimientoChange = { fechaNacimiento = it },
                vacunas = "opcion 1",
                especie = "Perro"
            )

            Spacer(Modifier.weight(.5f))

            // Módulo 4: Íconos de Acción (Editar y Eliminar)
            ActionIconsRow(onEditClicked, onDeleteClicked)

            Spacer(Modifier.height(16.dp))
        }
    }
}

// ==============================================================================
// 2. MÓDULOS DE LA INTERFAZ
// ==============================================================================

@Composable
private fun InfoPetsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Ícono de Regresar (Flecha ←)
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Volver",
            modifier = Modifier.size(24.dp).clickable { /* Lógica de regreso */ },
            tint = Color.Black
        )
        Text(text = "Nombre Usuario", fontSize = 14.sp)
    }
}

@Composable
private fun PetDisplayArea(petName: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = petName, fontSize = 28.sp, modifier = Modifier.padding(bottom = 16.dp))
        // Área de la Foto (Usarías tu componente FhotoPet.kt aquí)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)
                .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
        ) {
            Text("Área de Foto", modifier = Modifier.align(Alignment.Center), color = Color.LightGray)
        }
    }
}

@Composable
private fun PetDetailsForm(
    raza: String,
    fechaNacimiento: String,
    onFechaNacimientoChange: (String) -> Unit, // Función de callback para la fecha
    vacunas: String,
    especie: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Raza
        InputDisplayField(label = "Raza", value = raza)
        Spacer(Modifier.height(16.dp))

        // Fecha Nacimiento - USANDO EL DATEPICKER FIELD
        DatePickerField(
            label = "Fecha Nacimiento",
            currentDate = fechaNacimiento,
            onDateSelected = onFechaNacimientoChange // Pasa la función de actualización
        )
        Spacer(Modifier.height(16.dp))


        DropdownDisplayField(label = "Vacunas", option = vacunas)
        Spacer(Modifier.height(16.dp))

        // Especie
        InputDisplayField(label = "Especie", value = especie)
    }
}

@Composable
private fun ActionIconsRow(onEditClicked: () -> Unit, onDeleteClicked: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar Mascota",
            modifier = Modifier.size(48.dp).clickable(onClick = onEditClicked).padding(8.dp), tint = Color.Black)
        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Eliminar Mascota",
            modifier = Modifier.size(48.dp).clickable(onClick = onDeleteClicked).padding(8.dp), tint = Color.Black)
    }
}

// ==============================================================================
// 3. COMPONENTE DATEPICKER (Simulación del diálogo de calendario)
// ==============================================================================

@Composable
fun DatePickerField(
    label: String,
    currentDate: String, // Valor de la fecha actual a mostrar
    onDateSelected: (String) -> Unit // Función para actualizar el estado
) {
    // Estado para controlar si el diálogo está abierto
    var showDatePicker by remember { mutableStateOf(false) }

    // El campo de entrada
    Column(modifier = Modifier.fillMaxWidth(0.9f)) {
        Text(text = label)
        Spacer(Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                .clickable { showDatePicker = true } // <-- ¡Abre el diálogo al hacer clic!
        ) {
            // Muestra la fecha seleccionada
            Text(
                text = currentDate,
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 12.dp)
            )
        }
    }

    // Diálogo del Selector de Fecha (DatePickerDialog)
    if (showDatePicker) {
        // NOTA: Para una implementación real con calendario, usarías DatePickerDialog de Material 3.
        // Aquí usamos AlertDialog como simulación de la interfaz del DatePicker:
        AlertDialog(
            onDismissRequest = { showDatePicker = false },
            title = { Text("Seleccionar Fecha") },
            text = { Text("Simulación de calendario.\nFecha actual: $currentDate") },
            confirmButton = {
                Button(onClick = {
                    // Simulación de selección: reemplazar por la lógica real del DatePicker
                    onDateSelected("2025-10-27")
                    showDatePicker = false
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// ==============================================================================
// 4. PLACEHOLDERS AUXILIARES (Para los otros campos)
// ==============================================================================

@Composable
fun InputDisplayField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth(0.9f)) {
        Text(text = label)
        Spacer(Modifier.height(4.dp))
        Box(
            modifier = Modifier.fillMaxWidth().height(48.dp).border(1.dp, Color.Gray, MaterialTheme.shapes.small)
        ) {
            Text(text = value, modifier = Modifier.align(Alignment.CenterStart).padding(start = 12.dp))
        }
    }
}

@Composable
fun DropdownDisplayField(label: String, option: String) {
    Column(modifier = Modifier.fillMaxWidth(0.9f)) {
        Text(text = label)
        Spacer(Modifier.height(4.dp))
        Box(
            modifier = Modifier.fillMaxWidth().height(48.dp).border(1.dp, Color.Gray, MaterialTheme.shapes.small)
        ) {
            Text(text = option, modifier = Modifier.align(Alignment.CenterStart).padding(start = 12.dp))
            Text(text = "v", modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp))
        }
    }
}

// ==============================================================================
// 5. PREVIEW
// ==============================================================================

@Preview(showBackground = true)
@Composable
fun PreviewInfoPetsScreen() {
    InfoPetsScreen(navController = rememberNavController())
}