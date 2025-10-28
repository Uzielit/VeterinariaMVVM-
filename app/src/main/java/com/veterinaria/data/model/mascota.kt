package com.veterinaria.data.model

class mascota {
    data class Mascota(
        val id: Int,
        val nombre: String,
        val especie: String,
        val edad: Int,
        val descripcion: String
    )

}