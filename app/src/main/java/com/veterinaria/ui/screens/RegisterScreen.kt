package com.veterinaria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.veterinaria.R

// ==============================================================================
// 1. PANTALLA PRINCIPAL: RegisterScreen (Ajustado el espaciado y el ancho)
// ==============================================================================

// Definimos el ancho de los elementos de entrada para mayor control
private val InputWidthFraction = 0.85f // <-- Ajuste de ancho a 85%

@Composable
fun RegisterScreen(
    navController: NavController
) {
    val primaryButtonColor = Color(0xFF2196F3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- 1. Encabezado de Regreso ---
        RegistrationHeaderVisual(navController = navController)

        // --- 2. Ícono Central (Hueso) ---
        Image(
            painter = painterResource(id = R.drawable.hueso),
            contentDescription = "Icono de Hueso",
            modifier = Modifier.size(80.dp) // Reducimos un poco el ícono
        )


        Spacer(Modifier.height(24.dp)) // Reducimos el espacio después del hueso

        // --- 3. Campos de Entrada (Placeholders Visuales) ---

        InputFieldPlaceholder(label = "Nombre")
        Spacer(Modifier.height(12.dp)) // Reducimos el espacio entre campos

        InputFieldPlaceholder(label = "Apellido")
        Spacer(Modifier.height(12.dp))

        InputFieldPlaceholder(label = "Correo")
        Spacer(Modifier.height(12.dp))

        InputFieldPlaceholder(label = "Contraseña")
        Spacer(Modifier.height(12.dp))

        InputFieldPlaceholder(label = "Confirmas Contraseña")

        // --- 4. ESPACIO ANTES DEL BOTÓN ---
        // Volvemos a un Spacer fijo y corto (en lugar del peso)
        Spacer(Modifier.height(24.dp))

        // --- 5. Botón Registrarte (Ahora más arriba) ---
        Button(
            onClick = { /* No hay funcionalidad, solo visual */ },
            modifier = Modifier.fillMaxWidth(InputWidthFraction), // Usamos el nuevo ancho
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryButtonColor
            )
        ) {
            Text("REGISTRARTE", color = Color.White)
        }

        // Espacio final para que el contenido no pegue con el borde inferior
        Spacer(Modifier.height(16.dp))
    }
}

// ==============================================================================
// 2. MÓDULOS AUXILIARES Y PLACEHOLDERS VISUALES
// ==============================================================================

@Composable
private fun RegistrationHeaderVisual(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.popBackStack() }
            .padding(bottom = 24.dp), // Reducimos el espacio debajo del encabezado
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Volver al Inicio",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
        Spacer(Modifier.width(8.dp))

    }
}

/**
 * Simula visualmente un campo de entrada de texto (PetsField/UserInputField).
 * Ahora usa el ancho definido en la constante InputWidthFraction.
 */
@Composable
fun InputFieldPlaceholder(label: String) {
    Column(modifier = Modifier.fillMaxWidth(InputWidthFraction)) { // <-- Ancho Ajustado
        Text(text = label)
        Spacer(Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
        )
    }
}

// ==============================================================================
// 3. PREVIEW de la Página
// ==============================================================================

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}