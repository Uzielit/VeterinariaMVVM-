package com.veterinaria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veterinaria.data.model.Mascota
import com.veterinaria.data.repository.RepositoryMascota
import kotlinx.coroutines.launch

class AddViewModel(private val repository: RepositoryMascota) : ViewModel() {


}
