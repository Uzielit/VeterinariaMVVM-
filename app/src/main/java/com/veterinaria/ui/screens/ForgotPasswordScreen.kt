package com.veterinaria.ui.screens

// Para la flecha de retroceso

// Para las opciones del teclado (KeyboardOptions y KeyboardType)
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions // <--- Import correcto
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.veterinaria.viewmodel.ForgotPasswordViewModel
// *** IMPORT NECESARIO PARA LA CORRECCIÓN DEL VIEWMODEL ***
import androidx.lifecycle.viewmodel.compose.viewModel

// Para el icono de candado, necesitarás esta dependencia en tu build.gradle.kts (app)
// implementation("androidx.compose.material:material-icons-extended")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    // *** CAMBIO 1: El ViewModel se obtiene así ***
    // Ya no se pasa como parámetro, se obtiene aquí.
    viewModel: ForgotPasswordViewModel = viewModel()
) {

    var verificationCode by remember { mutableStateOf("") } // Estado para el código
    val userEmailPreview = "******@gmail.com" // Simulación del email oculto

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") }, // El wireframe no tiene título en la barra
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Flecha de retroceso
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Aplicar el padding del Scaffold
                .padding(horizontal = 30.dp, vertical = 20.dp), // Padding adicional
            horizontalAlignment = Alignment.CenterHorizontally,
            // Ajustamos el arrangement para que los elementos se distribuyan en la parte superior
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(30.dp)) // Espacio superior

            // Ícono de candado abierto
            Image(
                imageVector = Icons.Default.LockOpen,
                contentDescription = "Candado abierto",
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary) // Color del tema
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Título "Olvidé mi contraseña"
            Text(
                text = "Olvidé mi contraseña",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Mensaje de verificación
            Text(
                text = "Enviamos un código de verificación a tu correo",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = userEmailPreview, // El email simulado
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary // Podrías usar un color diferente
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Campo para el Código con el guión
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = verificationCode,
                    onValueChange = { verificationCode = it },
                    label = { Text("Código") },
                    // *** CAMBIO 2: Sintaxis de 'keyboardOptions' corregida ***
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Solo números
                    modifier = Modifier.weight(1f), // Toma el espacio disponible
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el campo y el guión
                Text(
                    text = "-", // El guión
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 30.sp),
                    modifier = Modifier.padding(bottom = 5.dp) // Alinea un poco mejor
                )
            }


            Spacer(modifier = Modifier.height(40.dp))

            // Botón "Verificar"
            Button(
                onClick = {
                    // Lógica para verificar el código
                    // viewModel.verifyCode(verificationCode)
                    // Navegar a la pantalla de restablecimiento de contraseña si es exitoso
                },
                modifier = Modifier.fillMaxWidth(0.7f) // El botón es un poco más pequeño
            ) {
                Text("Verificar")
            }

            Spacer(modifier = Modifier.height(20.dp)) // Espacio al final
        }
    }
}

// Para ver la vista previa en Android Studio
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewForgotPasswordScreen() {
    // Necesitas un NavController de prueba para la vista previa
    val navController = rememberNavController()

    // Para la PREVIA, sí tenemos que crear una instancia "falsa"
    // La advertencia de "Constructing a view model" aparecerá aquí, pero es seguro ignorarla
    // solo en el contexto de un @Preview.
    val viewModel = ForgotPasswordViewModel()

    MaterialTheme { // Envuelve en tu tema de Material para que los colores se vean bien
        // *** CAMBIO 3: Se actualiza la llamada al Preview ***
        ForgotPasswordScreen(navController = navController, viewModel = viewModel)
    }
}