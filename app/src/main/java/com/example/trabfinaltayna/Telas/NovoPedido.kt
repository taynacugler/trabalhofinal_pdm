package com.example.trabfinaltayna.Telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.R

class NovoPedido : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        val criarPedido: Button = findViewById(R.id.id_criarpedido)
        criarPedido.setOnClickListener {
            val cpfCliente: EditText = findViewById(R.id.get_cpfpedido)
            val cpf = cpfCliente.text.toString()

            val dao = DAO()

            dao.procurarClientePorCpf(cpf) { cliente ->
                if (cliente != null) {
                    val intent = Intent(this, NovoPedidoItens::class.java)
                    intent.putExtra("CPF_KEY", cpf)
                    startActivity(intent)

                }
                else {
                    Toast.makeText(this, "Não existe um cliente com esse cpf", Toast.LENGTH_LONG)
                }
            }







        }





    }

}