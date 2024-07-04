package com.example.trabfinaltayna.Telas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.MainActivity
import com.example.trabfinaltayna.ProdutoAdapter
import com.example.trabfinaltayna.R

class QuartaTela : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var produtoAdapter: ProdutoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val dao = DAO()

        val cadBotao: Button = findViewById(R.id.bt_cadprod)
        cadBotao.setOnClickListener {
            val intent = Intent(this, CadastroProduto::class.java)
            startActivity(intent)
        }

        val verProd: Button = findViewById(R.id.bt_verproduto)
        verProd.setOnClickListener {
            val pegarID: EditText = findViewById(R.id.get_idproduto)
            val id = pegarID.text.toString()
            if (id.isNotEmpty()) {
                dao.procurarProdutoPorId(id) { produto ->
                    if (produto != null) {
                        Toast.makeText(this, "Produto encontrado", Toast.LENGTH_LONG).show()

                        val graoTextView: TextView = findViewById(R.id.show_grao)
                        val torraTextView: TextView = findViewById(R.id.show_torra)
                        val blendTextView: TextView = findViewById(R.id.show_blend)
                        val valorTextView: TextView = findViewById(R.id.showValor)

                        graoTextView.text = "Tipo de grão: ${produto.tipoGrao}"
                        torraTextView.text = "Ponto da torra: ${produto.pontoTorra}"
                        blendTextView.text = "Blend: ${if (produto.blend) "Sim" else "Não"}"
                        valorTextView.text = "Valor: R$ ${produto.valor}"
                    } else {
                        Toast.makeText(this, "Produto não encontrado", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        val deletarProd: Button = findViewById(R.id.bt_deletarprod)
        deletarProd.setOnClickListener {
            val pegarID: EditText = findViewById(R.id.get_idproduto)
            val id = pegarID.text.toString()
            dao.deletarProduto(this, id)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


//        recyclerView = findViewById(R.id.recyclerViewProdutos)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        dao.listarTodosProdutos { produtos ->
//            if (produtos.isNotEmpty()) {
//                produtoAdapter = ProdutoAdapter(produtos)
//                recyclerView.adapter = produtoAdapter
//                produtoAdapter.notifyDataSetChanged()
//            } else {
//                Log.d("QuartaTela", "Nenhum produto encontrado")
//                Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_LONG).show()
//            }
//        }


    }
}

