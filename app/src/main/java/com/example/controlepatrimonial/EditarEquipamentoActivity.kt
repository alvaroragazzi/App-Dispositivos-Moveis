package com.example.controlepatrimonial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room

class EditarEquipamentoActivity : AppCompatActivity() {

    lateinit var db: AppDatabase;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_equipamento)

        val actionBar = supportActionBar

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
            "banco.db").allowMainThreadQueries().build()

        if(actionBar != null) {
            actionBar.title = "Editar equipamento"
        }

        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val edtEquipamentoEdit = findViewById<TextView>(R.id.edtEquipamentoEdit)
        val btnEditarEquip = findViewById<Button>(R.id.btnEditarEquip)
        val spinnerCategoriaEdit = findViewById<Spinner>(R.id.spinnerCategoriaEdit)
        val edtMostrarNomeEquipamento = findViewById<TextView>(R.id.edtMostrarNomeEquipamento)

        val id = getIntent().getStringExtra("id")
        val nome = getIntent().getStringExtra("nome")
        val categoria = getIntent().getStringExtra("categoria")

        edtEquipamentoEdit.text = nome.toString()
        edtMostrarNomeEquipamento.text = "Editando equipamento: ${nome.toString()}"

        val categoriaArray: Array<String> = getResources().getStringArray(R.array.categorias)

        spinnerCategoriaEdit.setSelection(categoriaArray.indexOf(
            categoriaArray.first { elem -> elem == categoria }
        ))

        btnEditarEquip.setOnClickListener{
            val equipamento = Equipamento(id.toString().toInt(), edtEquipamentoEdit.text.toString(), spinnerCategoriaEdit.getSelectedItem().toString())

            db.dao().alterarEquipamento(equipamento)

            Toast.makeText(this, "Equipamento alterado com sucesso", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}