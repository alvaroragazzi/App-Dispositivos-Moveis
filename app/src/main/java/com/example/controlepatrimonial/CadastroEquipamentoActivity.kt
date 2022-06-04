package com.example.controlepatrimonial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room

class CadastroEquipamentoActivity : AppCompatActivity() {
    val categorias = arrayOf("Computador", "Teclado", "Mouse")
    lateinit var db: AppDatabase;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_equipamento)

        val actionBar = supportActionBar

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
            "banco.db").allowMainThreadQueries().build()

        if(actionBar != null) {
            actionBar.title = "Cadastro de equipamento"
        }

        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val spinner = findViewById<Spinner>(R.id.spinnerCategoria)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val edtNome = findViewById<TextView>(R.id.edtNome)

        btnCadastrar.setOnClickListener {
            if (edtNome.text.toString() == "") {
                Toast.makeText(this, "Informe o nome do equipamento", Toast.LENGTH_SHORT).show()
            } else {
                val novoEquipamento = Equipamento(0, edtNome.text.toString(), spinner.getSelectedItem().toString())

                db.dao().incluirEquipamento(novoEquipamento)
                Toast.makeText(this, "Equipamento cadastrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}