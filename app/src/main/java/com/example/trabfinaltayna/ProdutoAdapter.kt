package com.example.trabfinaltayna

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabfinaltayna.Bean.Produto

class ProdutoAdapter(private val produtos: List<Produto>) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val torraTextView: TextView = itemView.findViewById(R.id.textViewTorra)
        val graoTextView: TextView = itemView.findViewById(R.id.textViewGrao)
        val valorTextView: TextView = itemView.findViewById(R.id.textViewValor)
        val blendTextView: TextView = itemView.findViewById(R.id.textViewBlend)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.torraTextView.text = produto.pontoTorra
        holder.graoTextView.text = produto.tipoGrao
        holder.valorTextView.text = produto.valor.toString()
        holder.blendTextView.text = if (produto.blend) "Sim" else "Não"
    }

    override fun getItemCount() = produtos.size
}
