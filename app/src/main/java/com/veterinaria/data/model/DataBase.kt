package com.veterinaria.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java


//Base de datos
@Database(entities = [Mascota::class], version = 1, exportSchema = false)
abstract class MascotaDatabase : RoomDatabase() {

    // Provee el DAO para ser usado por el Repositorio
    abstract fun mascotaDao(): MascotaDao

    // companion object permite crear un singleton de la base de datos
    // para evitar tener múltiples instancias.
    companion object {
        @Volatile
        private var INSTANCE: MascotaDatabase? = null

        fun getDatabase(context: Context): MascotaDatabase{
            // Retorna la instancia si ya existe,
            // si no, la crea de forma segura (synchronized).
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MascotaDatabase::class.java,
                    "veterinaria_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}