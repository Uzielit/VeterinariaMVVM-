package com.veterinaria.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java


//Base de datos
@Database(entities = [Mascota::class], version = 1)
abstract class MascotaDatabase : RoomDatabase() {


    abstract fun mascotaDao(): MascotaDao


}