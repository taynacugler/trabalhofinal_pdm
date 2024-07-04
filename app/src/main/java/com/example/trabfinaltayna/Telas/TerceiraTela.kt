package com.example.trabfinaltayna.Telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.Telas.QuartaTela
import com.example.trabfinaltayna.R
import com.example.trabfinaltayna.Telas.SextaTela


class TerceiraTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        val cpf = intent.getStringExtra("CPF_KEY")


        val botaoProduto: Button =  findViewById(R.id.bt_prod)
        val botaoCliente: Button =  findViewById(R.id.bt_cliente)
        val botaoPedido: Button =  findViewById(R.id.bt_pedidos)

        botaoCliente.setOnClickListener {
            val intent = Intent(this, QuintaTela::class.java)
//            intent.putExtra("CPF_KEY", cpf)
            startActivity(intent)
        }
        botaoProduto.setOnClickListener {
            val intent = Intent(this, QuartaTela::class.java)
//            intent.putExtra("CPF_KEY", cpf)
            startActivity(intent)
        }
        botaoPedido.setOnClickListener {
            val intent = Intent(this, SextaTela::class.java)
//            intent.putExtra("CPF_KEY", cpf)
            startActivity(intent)
        }
    }
}