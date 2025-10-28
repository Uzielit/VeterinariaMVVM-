package com.veterinaria.ui.screens

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.* // Importamos todo Material3 para Button, Icon, ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Importamos la flecha de regreso
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color // Para el color personalizado
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.veterinaria.R
// Importa tus componentes si ya los tienes creados y quieres usarlos
// import com.veterinaria.ui.components.inputs.UserInputField
// import com.veterinaria.ui.components.inputs.PasswordField

@Composable
fun RegisterScreen(
    navController: NavController // El NavController es para la navegación (volver al inicio, etc.)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp), // Ajusta el padding si es necesario
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
    ) {
        // "Volver al Inicio" MODIFICADO para usar el Icono y el texto
        Row(
            modifier = Modifier.fillMaxWidth().clickable {
                // Aquí iría la lógica para volver, p.ej. navController.popBackStack()
                // o navController.navigate("login_route")
            },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(Modifier.width(8.dp))

        }

        // Imagen del hueso (ajusta el recurso si tu icono tiene otro nombre)
        Image(
            painter = painterResource(id = R.drawable.hueso),
            contentDescription = "Hueso",
            modifier = Modifier.size(96.dp)
        )

        Spacer(Modifier.height(32.dp)) // Espacio después del hueso

        // Campos de entrada (placeholders)
        SimpleInputField(label = "Nombre")
        Spacer(Modifier.height(16.dp))
        SimpleInputField(label = "Apellido")
        Spacer(Modifier.height(16.dp))
        SimpleInputField(label = "Correo")
        Spacer(Modifier.height(16.dp))
        SimpleInputField(label = "Contraseña")
        Spacer(Modifier.height(16.dp))
        SimpleInputField(label = "Confirmas Contraseña")
        Spacer(Modifier.height(32.dp)) // Espacio antes del botón

        // Botón "REGISTRARTE" MODIFICADO con el color de fondo 0xFF2196F3
        Button(
            onClick = { /* Lógica de registro aquí */ },
            modifier = Modifier.fillMaxWidth(0.8f), // Ajusta el ancho del botón
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3) // Color de fondo personalizado
            )
        ) {
            Text("REGISTRARTE", color = Color.White) // Texto en blanco para contraste
        }
    }
}

// ==============================================================================
// Componente Placeholder simple (sin cambios)
// ==============================================================================
@Composable
fun SimpleInputField(label: String) {
    Column(modifier = Modifier.fillMaxWidth(0.8f)) {
        Text(text = label)
        Spacer(Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(1.dp, androidx.compose.ui.graphics.Color.Gray, MaterialTheme.shapes.small)
        ) {
            // Aquí iría tu TextField real
        }
    }
}
// ==============================================================================

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}