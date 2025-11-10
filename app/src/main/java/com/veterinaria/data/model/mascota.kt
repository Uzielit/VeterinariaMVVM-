package com.veterinaria.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mascotas")
    data class Mascota(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val imagen : String,
        val imageUrl: String?,
        val especie: String,
        val edad: Int,
        val vacunado: Boolean,
        val descripcion: String
    )

