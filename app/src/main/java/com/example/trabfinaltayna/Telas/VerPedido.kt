package com.example.trabfinaltayna.Telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.R

class VerPedido : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pedido)
//        val idPedido = intent.getStringExtra("ID_KEY")
        val idPedido: EditText = findViewById(R.id.show_pedido)
        val id = idPedido.text.toString()
        val cpfPedido: TextView = findViewById(R.id.show_cpfpedido)
        val dao = DAO()

        val btDelete: Button = findViewById(R.id.bt_deletepedido)
        btDelete.setOnClickListener {
            dao.deletarPedidoPorId(this, id)
        }
        val btDeletarItem: Button = findViewById(R.id.deleteprod)

        btDeletarItem.setOnClickListener {
            dao.buscarItensPedidoPorId(id) { items ->
                if (items != null) {
                    val idItemString = findViewById<EditText>(R.id.produtodelete).text.toString()

                    items.forEach { item ->
                        if (item.idItemPedido == idItemString) {
                            dao.deletarItemPedido(item.idItemPedido, { success ->
                                if (success) {
                                    println("Item '${item.idItemPedido}' deletado")
                                } else {
                                    println("Erro ao deletar item")
                                }
                            })
                        }
                    }
                } else {
                    println("Nenhum item encontrado encontrado")
                }
            }
        }
}
}



//        if (id != null) {
//            dao.procurarPedidoPorId(id) { pedido ->
//                if (pedido != null) {
//                    cpfPedido.text = "Nome: ${pedido.fk_cpf}"
//                    //deletar
//
//                } else {
//                    Toast.makeText(this, "Pedido não encontrado", Toast.LENGTH_LONG).show()
//                }
//






