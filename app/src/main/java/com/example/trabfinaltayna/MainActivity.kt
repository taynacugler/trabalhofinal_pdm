package com.example.trabfinaltayna

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabfinaltayna.Bean.Cliente
import com.example.trabfinaltayna.Telas.QuartaTela
import com.example.trabfinaltayna.Telas.QuintaTela
import com.example.trabfinaltayna.Telas.SegundaTela
import com.example.trabfinaltayna.Telas.SextaTela
import com.example.trabfinaltayna.Telas.TelaPedido
import com.example.trabfinaltayna.Telas.TerceiraTela
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialize o Firebase
        val database = Firebase.database

        val dao = DAO()
        val novoCliente = Cliente("44494836850", "Tayna", "1319191919", "rua tal", "@ta.yna")
        dao.addCliente(this, novoCliente)
        val botaoProduto: Button =  findViewById(R.id.bt_prod)
        val botaoCliente: Button =  findViewById(R.id.bt_cliente)
        val botaoPedido: Button =  findViewById(R.id.bt_pedidos)

        botaoCliente.setOnClickListener {
            val intent = Intent(this, QuintaTela::class.java)
            startActivity(intent)
        }
        botaoProduto.setOnClickListener {
            val intent = Intent(this, QuartaTela::class.java)
            startActivity(intent)
        }
        botaoPedido.setOnClickListener {
            val intent = Intent(this, TelaPedido::class.java)
            startActivity(intent)
        }


//        val button: Button = findViewById(R.id.bt_cad)
//        button.setOnClickListener {
//            val intentCadastro = Intent(this, SegundaTela::class.java)
//            startActivity(intentCadastro)
//        }
//
//        val editTextCpf: EditText = findViewById(R.id.cpf_entrar)
//        val buttonEntrar: Button = findViewById(R.id.bt_entrar)
//
//        buttonEntrar.setOnClickListener {
//            val cpf = editTextCpf.text.toString()
//            dao.procurarClientePorCpf(cpf, { cliente ->
//                if (cliente != null) {
//                    Toast.makeText(this, "Cliente encontrado", Toast.LENGTH_LONG).show()
//                    try {
//                        val intent = Intent(this, TerceiraTela::class.java)
//                        intent.putExtra("CPF_KEY", cpf)
//                        startActivity(intent)
//                    } catch (e: Exception) {
//                        // Adicione um log para ver qual é o erro
//                        Log.e("Erro", "Erro ao iniciar a TerceiraTela", e)
//                        Toast.makeText(this, "Erro ao iniciar a TerceiraTela", Toast.LENGTH_LONG)
//                            .show()
//                    }
//                } else {
//                    Toast.makeText(this, "Cliente não encontrado", Toast.LENGTH_LONG).show()
//                    val intent = Intent(this, SegundaTela::class.java)
//                    startActivity(intent)
//                }
//            })


//        }




        }

    }

















