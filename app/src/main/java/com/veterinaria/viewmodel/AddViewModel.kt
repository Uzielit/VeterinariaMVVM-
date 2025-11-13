package com.veterinaria.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veterinaria.data.model.Mascota
import com.veterinaria.data.repository.RepositoryMascota
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.net.Uri
import java.text.SimpleDateFormat
import java.util.Locale

import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class AddViewModel(private val repository: RepositoryMascota): ViewModel() {


    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _especie = MutableStateFlow("")
    val especie = _especie.asStateFlow()

    private val _imageUrl = MutableStateFlow<Uri?>(null)
    val imageUrl = _imageUrl.asStateFlow()



    private val _fechaNacimientoStr = MutableStateFlow("")
    val fechaNacimientoStr = _fechaNacimientoStr.asStateFlow()

    private val _vacunado = MutableStateFlow(false)
    val vacunado = _vacunado.asStateFlow()


    fun onNombreChange(text: String) { _nombre.value = text }
    fun onEspecieChange(text: String) { _especie.value = text }
    fun onImageUrlChange(uri: Uri?) { _imageUrl.value = uri }
    fun onFechaNacimientoChange(text: String) { _fechaNacimientoStr.value = text }
    fun onVacunadoChange(isChecked: Boolean) { _vacunado.value = isChecked }

    suspend fun savePet(context: Context): Boolean {
        val nombre = _nombre.value
        val especie = _especie.value
        val fechaStr = _fechaNacimientoStr.value
        val vacunado = _vacunado.value
        val imagenUri = _imageUrl.value

        if (nombre.isBlank() || especie.isBlank() || imagenUri == null) {
            return false
        }

        return try {
            val rutaImagenPermanente = saveImageToInternalStorage(context, imagenUri)

            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaLong = try {
                formatter.parse(fechaStr)?.time ?: System.currentTimeMillis()
            } catch (e: Exception) { System.currentTimeMillis() }

            val nuevaMascota = Mascota(
                nombre = nombre,
                especie = especie,
                imageUrl = rutaImagenPermanente,
                fechaNacimiento = fechaLong,
                vacunado = vacunado
            )
            repository.insertPet(nuevaMascota)
            _nombre.value = ""
            _especie.value = ""
            _imageUrl.value = null
            _fechaNacimientoStr.value = ""
            _vacunado.value = false

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun saveImageToInternalStorage(context: Context, uri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(uri)

        val imagesDir = File(context.filesDir, "images")
        if (!imagesDir.exists()) {
            imagesDir.mkdir()
        }

        val fileName = "${UUID.randomUUID()}.jpg"
        val file = File(imagesDir, fileName)

        inputStream.use { input ->
            FileOutputStream(file).use { output ->
                input?.copyTo(output)
            }
        }
        return file.absolutePath
    }

}
