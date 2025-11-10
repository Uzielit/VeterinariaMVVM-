package com.veterinaria.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface MascotaDao {
//READ
    @Query("SELECT * FROM MASCOTAS ORDER BY nombre ASC")
    fun getAllPets(): Flow<List<Mascota>>
//READ POR ID
    @Query ( "SELECT * FROM MASCOTAS WHERE id = :id")
    suspend fun getPetById(id: Int): Mascota?


    //CREATE
    @Insert()
    suspend fun insertPet(pet: Mascota)

//Delete
@Delete
suspend fun deletePet(pet: Mascota)
//Update
@Update
suspend fun updatePet(pet: Mascota)
}