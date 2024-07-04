package com.example.trabfinaltayna.Telas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.Bean.Cliente
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.R

class SegundaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val botaoCad: Button = findViewById(R.id.bt_enviar)
        botaoCad.setOnClickListener {
            val dao = DAO()

            val nome: EditText = findViewById(R.id.get_nome)
            val cpf: EditText = findViewById(R.id.get_cpf)
            val telefone: EditText = findViewById(R.id.get_tel)
            val endereco: EditText = findViewById(R.id.get_end)
            val ig: EditText = findViewById(R.id.get_instagram)

            val nomeText = nome.text.toString()
            val cpfText = cpf.text.toString()
            val telefoneText = telefone.text.toString()
            val enderecoText = endereco.text.toString()
            val igText = ig.text.toString()

            val cliente = Cliente(
                nome = nomeText,
                cpf = cpfText,
                telefone = telefoneText,
                endereco = enderecoText,
                instagram = igText
            )
            dao.addCliente(this, cliente)


        }
    }
}