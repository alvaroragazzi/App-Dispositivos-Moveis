package com.example.controlepatrimonial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var dados: MutableList<Equipamento>

    lateinit var db: AppDatabase;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
            "banco.db").allowMainThreadQueries().build()

        dados = db.dao().consultarEquipamentos()
        recyclerView = findViewById(R.id.recyclerView)
        print(dados)
        val itemAdapter = ItemAdapter(this, dados)

        recyclerView.adapter = itemAdapter

        val layout = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layout

        val divisor = DividerItemDecoration(recyclerView.context, layout.orientation)
        recyclerView.addItemDecoration(divisor)

        val btnCriar: Button = findViewById(R.id.btnCriar)

        btnCriar.setOnClickListener {
            startActivity(Intent(this, CadastroEquipamentoActivity::class.java))
        }

        itemAdapter.onEditarClick = { item ->
            val Intent = Intent(this, EditarEquipamentoActivity::class.java)
            Intent.putExtra("id", item.id.toString())
            Intent.putExtra("nome", item.nome)
            Intent.putExtra("categoria", item.categoria)
            startActivity(Intent)
        }

        itemAdapter.onApagarClick = { item ->
            db.dao().excluirEquipamento((item))
            dados.remove(item)
            recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(this, "Equipamento ${item.nome.toString()} apagado com sucesso", Toast.LENGTH_SHORT).show()
        }
    }
}