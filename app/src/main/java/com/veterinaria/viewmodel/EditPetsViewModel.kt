package com.veterinaria.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veterinaria.data.model.Mascota
import com.veterinaria.data.repository.RepositoryMascota
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.net.Uri

import java.io.File
import java.io.FileOutputStream
import java.util.UUID


class EditPetsViewModel(
    private val repository: RepositoryMascota,
    private val petId: Int
) : ViewModel() {

    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _especie = MutableStateFlow("")
    val especie = _especie.asStateFlow()

    private val _imageUrl = MutableStateFlow<String?>("") // Ruta local guardada
    val imageUrl = _imageUrl.asStateFlow()

    private val _fechaNacimientoStr = MutableStateFlow("")
    val fechaNacimientoStr = _fechaNacimientoStr.asStateFlow()

    private val _vacunado = MutableStateFlow(false)
    val vacunado = _vacunado.asStateFlow()

    private val _nuevaImagenUri = MutableStateFlow<Uri?>(null)
    val nuevaImagenUri = _nuevaImagenUri.asStateFlow()

    private var mascotaOriginal: Mascota? = null

    init {
        loadPetData()
    }

    private fun loadPetData() {
        viewModelScope.launch {
            val pet = repository.getPetById(petId)
            if (pet != null) {
                mascotaOriginal = pet
                _nombre.value = pet.nombre
                _especie.value = pet.especie
                _imageUrl.value = pet.imageUrl

                val date = Date(pet.fechaNacimiento)
                _fechaNacimientoStr.value = formatter.format(date)
                _vacunado.value = pet.vacunado
            }
        }
    }

    fun onNombreChange(text: String) { _nombre.value = text }
    fun onEspecieChange(text: String) { _especie.value = text }
    fun onFechaNacimientoChange(text: String) { _fechaNacimientoStr.value = text }
    fun onVacunadoChange(isChecked: Boolean) { _vacunado.value = isChecked }


    fun onNewImageSelected(uri: Uri) {
        _nuevaImagenUri.value = uri
    }

    suspend fun updatePet(context: Context): Boolean {
        return try {
            var rutaImagenFinal = mascotaOriginal?.imageUrl
            val uriNueva = _nuevaImagenUri.value

            if (uriNueva != null) {

                rutaImagenFinal = saveImageToInternalStorage(context, uriNueva)

                mascotaOriginal?.imageUrl?.let { File(it).delete() }
            }

            val fechaLong = try {
                formatter.parse(_fechaNacimientoStr.value)?.time ?: System.currentTimeMillis()
            } catch (e: Exception) { System.currentTimeMillis() }

            val mascotaActualizada = Mascota(
                id = mascotaOriginal!!.id,
                nombre = _nombre.value,
                especie = _especie.value,
                imageUrl = rutaImagenFinal,
                fechaNacimiento = fechaLong,
                vacunado = _vacunado.value
            )
            repository.updatePet(mascotaActualizada)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deletePet(): Boolean {
        return try {
            mascotaOriginal?.let { pet ->

                pet.imageUrl?.let { File(it).delete() }

                repository.deletePet(pet)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


    private fun saveImageToInternalStorage(context: Context, uri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(uri)
        val imagesDir = File(context.filesDir, "images")
        if (!imagesDir.exists()) { imagesDir.mkdir() }
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