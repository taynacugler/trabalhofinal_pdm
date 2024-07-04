package com.example.trabfinaltayna.Telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabfinaltayna.ClienteAdapter
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.MainActivity
import com.example.trabfinaltayna.R
import com.example.trabfinaltayna.Bean.Cliente

class QuintaTela : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var clienteAdapter: ClienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val editTextCpf: EditText = findViewById(R.id.getCPFCli)
        val botaoMostrar: Button = findViewById(R.id.bt_mostrar)
        val botaoAlterar: Button = findViewById(R.id.bt_altcli)
        val botaoDeletar: Button = findViewById(R.id.bt_delcli)
        val botaoCad: Button = findViewById(R.id.bt_cadastro)
        val dao = DAO()

        botaoMostrar.setOnClickListener {
            val cpf = editTextCpf.text.toString()
            if (cpf.isNotEmpty()) {
                dao.procurarClientePorCpf(cpf) { cliente ->
                    if (cliente != null) {
                        Toast.makeText(this, "Cliente encontrado", Toast.LENGTH_LONG).show()
                        val nomeTextView: TextView = findViewById(R.id.show_clinome)
                        val telefoneTextView: TextView = findViewById(R.id.show_tel)
                        val instagramTextView: TextView = findViewById(R.id.show_instagram)
                        val enderecoTextView: TextView = findViewById(R.id.show_end)

                        nomeTextView.text = "Nome: ${cliente.nome}"
                        instagramTextView.text = "Instagram: ${cliente.instagram}"
                        telefoneTextView.text = "Telefone: ${cliente.telefone}"
                        enderecoTextView.text = "Endereço: ${cliente.endereco}"
                    } else {
                        Toast.makeText(this, "Cliente não encontrado", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, SegundaTela::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

        botaoCad.setOnClickListener {
            val intent = Intent(this, SegundaTela::class.java)
            startActivity(intent)
        }

        botaoAlterar.setOnClickListener {
            val cpf = editTextCpf.text.toString()
            val intent = Intent(this, SextaTela::class.java)
            intent.putExtra("CPF_KEY", cpf)
            startActivity(intent)
        }

        botaoDeletar.setOnClickListener {
            val cpf = editTextCpf.text.toString()
            if (cpf.isNotEmpty()) {
                dao.deletarCliente(this, cpf)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        recyclerView = findViewById(R.id.recyclerViewClientes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dao.listarTodosClientes { clientes ->
            if (clientes.isNotEmpty()) {
                clienteAdapter = ClienteAdapter(clientes)
                recyclerView.adapter = clienteAdapter
            } else {
                Toast.makeText(this, "Nenhum cliente encontrado", Toast.LENGTH_LONG).show()
            }
        }
    }
}
