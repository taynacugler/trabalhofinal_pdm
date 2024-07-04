package com.example.trabfinaltayna.Telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.Bean.Cliente
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.R

class SextaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        val cpf = intent.getStringExtra("CPF_KEY")
        val dao = DAO()

        val botaoAlt: Button = findViewById(R.id.bt_alterar)
        botaoAlt.setOnClickListener {
            val nome: EditText = findViewById(R.id.getNomeAlt)
            val telefone: EditText = findViewById(R.id.getTelAlt)
            val endereco: EditText = findViewById(R.id.getEndAlt)
            val ig: EditText = findViewById(R.id.getIGalt)

            val nomeText = nome.text.toString()
            val telefoneText = telefone.text.toString()
            val enderecoText = endereco.text.toString()
            val igText = ig.text.toString()


            val cliente = cpf?.let {
                Cliente(
                    nome = nomeText,
                    cpf = it,
                    telefone = telefoneText,
                    endereco = enderecoText,
                    instagram = igText
                )
            }

            if (cpf != null) {
                if (cliente != null) {
                    dao.alterarCliente(this, cpf, cliente)
                }
            }
            val intent = Intent(this, QuintaTela::class.java)
            intent.putExtra("CPF_KEY", cpf)
            startActivity(intent)
        }
    }
}