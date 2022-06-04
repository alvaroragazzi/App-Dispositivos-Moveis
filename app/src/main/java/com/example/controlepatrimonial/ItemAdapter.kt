// Troque o nome do pacote para o do seu projeto
package com.example.controlepatrimonial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    
    private val context: Context,
    
    // Neste parâmetro será recebida a lista de objetos Model (Produto, neste caso)
    // que queremos mostrar no RecyclerView
    // Em outra implementação, se usar uma classe Model que não
    // se chame Produto, troque o nome na declaração abaixo
    private val dados: MutableList<Equipamento>
    
    ) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Esta declaração permite que você consiga programar algo para o clique
    // de uma linha do RecyclerView. Se usar uma classe Model que não se chama
    // Produto, faça a troca na linha abaixo
    var onEditarClick: ((Equipamento) -> Unit)? = null
    var onApagarClick: ((Equipamento) -> Unit)? = null

    // Classe ViewHolder: o "segurador" de "visões" (views)
    // O ViewHolder é o responsável em "reciclar" as linhas da lista, economizando
	// assim o uso da memória do dispositivo.
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        
        // Declare abaixo os widgets que exibirão na linha do RecyclerView os dados
        // existentes na sua classe Model. 
        // Por exemplo, suponha que você criou uma classe Model chamada
        // Despesa para exibir os gastos mensais de uma pessoa, e esta classe tem um
        // atributo chamado "valor" para o valor da Despesa. 
        // Se Você colocar um TextView no seu layout de linha e chamá-lo de, 
        // por exemplo, txtValor, deverá declará-lo da seguinte forma:
        // val txtValor: TextView = view.findViewById(R.id.txtValor)
		// Faça o mesmo para os outros widgets do seu layout de linha que exibirão
		// dados do seu Model

        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtPatrimonio: TextView = view.findViewById(R.id.txtPatrimonio)
        val txtCategoria: TextView = view.findViewById(R.id.txtCategoria)
        val btnEditar: Button = view.findViewById(R.id.btnEditar)
        val btnApagar: Button = view.findViewById(R.id.btnApagar)

        init {
            btnEditar.setOnClickListener {
                onEditarClick?.invoke(dados[adapterPosition])
            }

            btnApagar.setOnClickListener {
                onApagarClick?.invoke(dados[adapterPosition])
            }
        }
    }

	// Evento de criação do ViewHolder ("segurador" de visões ("views"))
	// Esta função precisa existir no seu Adapter mas dificilmente precisará ser
	// modificada. A única mudança talvez seja o nome do seu layout para a linha
	// do RecyclerView. Se for diferente, troque "linha_recyclerview" pelo nome do
	// seu arquivo.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout, parent, false)
        return ViewHolder(layout)
    }

    // Evento IMPORTANTÍSSIMO que acontece na "ligação" do ViewHolder com os seus dados.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Nesta função "colocamos" os dados de cada objeto do seu Model
        // no respectivo textView. Supondo que você tem um objeto da classe Despesa
        // com uma variável chamada valor, e supondo que você declarou acima na
        // declaração da classe ViewHolder o txtValor, você deverá colocar o seguinte:
        // val despesa = dados.get(position)
        // holder.txtValor.text = despesa.valor.toString()
        // Nas duas linhas comentadas acima, "pegamos" dentro da lista de objetos Model
        // (chamada dados) o objeto despesa referente a linha atual do RecyclerView
        // (identificada como position). Em seguida, colocamos o valor do campo no
        // text do TextView
        val item = dados.get(position)
        holder.txtNome.text = item.nome
        holder.txtPatrimonio.text = "Patrimônio: ${item.id}"
        holder.txtCategoria.text = "Categoria: ${item.categoria}"
    }
    
    // Usamos esta função para "dizer" ao RecyclerView a quantidade total de elementos
    // que ele vai conter. No caso, a quantidade é exatamente o tamanho da lista
    // de objetos Model (dados)
    override fun getItemCount(): Int {
        return dados.size
    }
}