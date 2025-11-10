package com.veterinaria.data.repository

import com.veterinaria.data.model.Mascota
import com.veterinaria.data.model.MascotaDao
import kotlinx.coroutines.flow.Flow

class RepositoryMascota ( private val mascotaDao: MascotaDao){

    val allMascota : Flow<List<Mascota>> = mascotaDao.getAllPets()

    /**
     * Pasa la llamada de 'getById' al DAO.
     */
    suspend fun getPetById(id: Int): Mascota? {
        return mascotaDao.getPetById(id)
    }

    /**
     * Pasa la llamada de 'insert' al DAO.
     */
    suspend fun insert(pet: Mascota) {
        mascotaDao.insertPet(pet)
    }

    /**
     * Pasa la llamada de 'update' al DAO.
     */
    suspend fun update(pet: Mascota) {
        mascotaDao.updatePet(pet)
    }

    /**
     * Pasa la llamada de 'delete' al DAO.
     */
    suspend fun delete(pet: Mascota) {
        mascotaDao.deletePet(pet)
    }


}