package com.example.trabfinaltayna.Telas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.Bean.ItemPedido
import com.example.trabfinaltayna.Bean.Pedido
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.R
import java.util.Date

class NovoPedidoItens : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.novo_pedido)

        val cpf = intent.getStringExtra("CPF_KEY")



        val items = mutableListOf<ItemPedido>()
        val produtoIdEditText: EditText = findViewById(R.id.get_produtoNovo)
        val quantidadeEditText: EditText = findViewById(R.id.get_quantidade)

        val botaoAddProd: Button = findViewById(R.id.bt_addprod)
        botaoAddProd.setOnClickListener {
            val produtoId = produtoIdEditText.text.toString()
            val quantidade = quantidadeEditText.text.toString().toInt()
            val novoItem = ItemPedido(
                fk_idPedido = "",
                fk_idProduto = produtoId,
                quantidade = quantidade
            )
            items.add(novoItem)

            Toast.makeText(this, "Produto adicionado: $produtoId", Toast.LENGTH_LONG).show()

            produtoIdEditText.text.clear()
            quantidadeEditText.text.clear()

        }
        val botaoFinalizarPedido: Button = findViewById(R.id.bt_finalizarpedido)

        botaoFinalizarPedido.setOnClickListener {
            val dao = DAO()
            val idPedido: EditText = findViewById(R.id.get_idpedido)
            val id = idPedido.text.toString()
            val pedido = cpf?.let {
                Pedido(
                    id_pedido = id,
                    data = Date(),
                    fk_cpf = it
                )
            }
            if (pedido != null) {
                dao.addPedido(this, pedido, items)
            }
        }


    }
}