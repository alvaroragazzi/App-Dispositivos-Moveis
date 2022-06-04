package com.example.controlepatrimonial

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Equipamento(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var nome: String,
    var categoria: String
    )
