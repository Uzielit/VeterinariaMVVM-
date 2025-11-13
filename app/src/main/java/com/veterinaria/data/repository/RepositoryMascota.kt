package com.veterinaria.data.repository

import com.veterinaria.data.model.Mascota
import com.veterinaria.data.model.MascotaDao
import kotlinx.coroutines.flow.Flow



class RepositoryMascota ( private val mascotaDao: MascotaDao)  {

    fun getAllPets (): Flow<List<Mascota>> =  mascotaDao.getAllPets()
    suspend fun insertPet (mascota: Mascota) {
        mascotaDao.insertPet(mascota)
    }
    suspend fun updatePet (mascota: Mascota) {
        mascotaDao.updatePet(mascota)
    }
    suspend fun deletePet (mascota: Mascota) {
        mascotaDao.deletePet(mascota)
    }
   suspend fun getPetById (id: Int): Mascota? {
       return mascotaDao.getPetById(id)
   }



}
