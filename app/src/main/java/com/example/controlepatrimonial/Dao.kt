package com.example.controlepatrimonial

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Query("SELECT * FROM equipamento")
    fun consultarEquipamentos(): MutableList<Equipamento>

    @Insert
    fun incluirEquipamento(equipemento: Equipamento)

    @Delete
    fun excluirEquipamento(equipemento: Equipamento)

    @Update
    fun alterarEquipamento(equipemento: Equipamento)
}