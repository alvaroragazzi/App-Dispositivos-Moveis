package com.example.controlepatrimonial

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Equipamento::class), version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): Dao
}