package com.veterinaria.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController



import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.veterinaria.data.repository.RepositoryMascota
import com.veterinaria.viewmodel.AddViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    viewModel: AddViewModel,
    onNavigateBack: () -> Unit
) {
    // --- Estados para el Formulario ---
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var vacunado by remember { mutableStateOf(false) }

    // --- Estado para el Dropdown de Especie ---
    var especieExpanded by remember { mutableStateOf(false) }
    var especieSelected by remember { mutableStateOf("") }
    val especieOptions = listOf("Perro", "Gato", "Ave", "Reptil", "Otro")

    // --- Estados para el DatePicker Moderno ---
    var showDatePicker by remember { mutableStateOf(false) }
    // Almacena la fecha como Long (milisegundos), igual que en Mascota.kt
    var fechaNacimiento by remember { mutableStateOf<Long?>(null) }
    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    // Contexto para Toasts (mensajes de error)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Mascota", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp) // Más padding lateral (estilo Adidas)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // --- Placeholder de Foto Moderno ---
            Text(
                text = "Foto de la Mascota",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        BorderStroke(
                            2.dp,
                            MaterialTheme.colorScheme.outlineVariant
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .clickable { /* TODO: Lógica para abrir la cámara/galería */ },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "Añadir foto",
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        "Tocar para añadir foto",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Campos del Formulario ---
            Text(
                text = "Datos Principales",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de la Mascota") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Dropdown para Especie ---
            ExposedDropdownMenuBox(
                expanded = especieExpanded,
                onExpandedChange = { especieExpanded = !especieExpanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = especieSelected,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Especie") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = especieExpanded)
                    },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = especieExpanded,
                    onDismissRequest = { especieExpanded = false }
                ) {
                    especieOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                especieSelected = option
                                especieExpanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- CAMPO DE FECHA CON DATEPICKER M3 ---
            OutlinedTextField(
                value = if (fechaNacimiento == null) "" else dateFormatter.format(fechaNacimiento),
                onValueChange = { },
                label = { Text("Fecha de Nacimiento") },
                placeholder = { Text("DD/MM/AAAA") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDatePicker = true }, // Abre el diálogo
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Descripción
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción o Raza") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Switch para Vacunado (coincide con Mascota.kt) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "¿Está vacunado?",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        if (vacunado) "Sí, está al día" else "No, está pendiente",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = vacunado,
                    onCheckedChange = { vacunado = it }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botones de Acción ---
            Button(
                onClick = {
                    // Validación simple
                    if (nombre.isNotBlank() && especieSelected.isNotBlank() && fechaNacimiento != null) {
                        // Llama al ViewModel para guardar en la BD
                        viewModel.insertPet(
                            nombre = nombre,
                            especie = especieSelected,
                            fechaNacimiento = fechaNacimiento!!, // Sabemos que no es null
                            descripcion = descripcion,
                            vacunado = vacunado,
                            imagen = 0 // TODO: Cambiar por la imagen real
                        )
                        onNavigateBack() // Regresa a la pantalla anterior
                    } else {
                        // TODO: Mostrar un Toast o SnackBar de error
                        // Toast.makeText(context, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Guardar Mascota", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Cancelar", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    // --- DIÁLOGO DEL DATEPICKER DE MATERIAL 3 ---
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = fechaNacimiento ?: System.currentTimeMillis()
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    fechaNacimiento = datePickerState.selectedDateMillis
                    showDatePicker = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

