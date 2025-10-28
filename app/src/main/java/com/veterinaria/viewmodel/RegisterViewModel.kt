package com.veterinaria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel () {
    // Estado de los campos de texto
    // Usamos 'by' para acceder/modificar el valor directamente (Kotlin property delegate)
    var nombre by mutableStateOf("")
        private set // Solo el ViewModel puede modificar directamente

    var apellido by mutableStateOf("")
        private set

    var correo by mutableStateOf("")
        private set

    var contrasena by mutableStateOf("")
        private set

    var confirmarContrasena by mutableStateOf("")
        private set

    // Propiedad para almacenar mensajes de error (si los hay)
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Funciones para actualizar el estado cuando el usuario escribe
    fun onNombreChange(newValue: String) { nombre = newValue }
    fun onApellidoChange(newValue: String) { apellido = newValue }
    fun onCorreoChange(newValue: String) { correo = newValue }
    fun onContrasenaChange(newValue: String) { contrasena = newValue }
    fun onConfirmarContrasenaChange(newValue: String) { confirmarContrasena = newValue }

    // Función que maneja la lógica de registro
    fun onRegisterClicked(onSuccess: () -> Unit) {
        errorMessage = null // Limpiar errores anteriores

        // 1. Validaciones
        if (nombre.isBlank() || correo.isBlank() || contrasena.isBlank()) {
            errorMessage = "Todos los campos son obligatorios."
            return
        }

        if (contrasena != confirmarContrasena) {
            errorMessage = "Las contraseñas no coinciden."
            return
        }

        // 2. Lógica de Negocio (Aquí llamarías a tu servicio de API o base de datos)
        // Por ahora, solo simulamos un registro exitoso:
        println("Registrando usuario: $correo")

        // 3. Éxito: Llamar a la función de navegación
        onSuccess()
    }
}