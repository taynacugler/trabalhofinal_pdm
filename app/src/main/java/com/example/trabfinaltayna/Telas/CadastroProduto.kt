package com.example.trabfinaltayna.Telas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.Bean.Cliente
import com.example.trabfinaltayna.Bean.Produto
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.R

class CadastroProduto  : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        val botaoCad: Button = findViewById(R.id.bt_cadproduto)
        botaoCad.setOnClickListener {
            val dao = DAO()

            val spinnerTipoGrao: Spinner = findViewById(R.id.spinnerTipoGrao)
            val spinnerPontoTorra: Spinner = findViewById(R.id.spinnerPontoTorra)
            val editTextValor: EditText = findViewById(R.id.get_valorProd)
            val switchBlend: Switch = findViewById(R.id.switch1)
            val pegarID: EditText = findViewById(R.id.get_idCad)

            val tipoGraoSelecionado = spinnerTipoGrao.selectedItem.toString()
            val pontoTorraSelecionado = spinnerPontoTorra.selectedItem.toString()
            val textoValor = editTextValor.text.toString()
            val valorProduto = textoValor.toDouble()
            val isBlendSelected = switchBlend.isChecked
            val id = pegarID.text.toString()

            val novoProduto = Produto(
                idProduto = id,
                tipoGrao = tipoGraoSelecionado,
                pontoTorra = pontoTorraSelecionado,
                valor = valorProduto,
                blend = isBlendSelected
            )
            dao.addProduto(this, novoProduto)



        }
        val botaoAlt: Button = findViewById(R.id.bt_altprod)
        botaoAlt.setOnClickListener {
            val dao = DAO()
            val spinnerTipoGrao: Spinner = findViewById(R.id.spinnerGraoAlt)
            val spinnerPontoTorra: Spinner = findViewById(R.id.spinnerTorraAlt)
            val editTextValor: EditText = findViewById(R.id.getValorAlt)
            val switchBlend: Switch = findViewById(R.id.switch2)
            val pegarID: EditText = findViewById(R.id.getIDProd)

            val tipoGraoSelecionado = spinnerTipoGrao.selectedItem.toString()
            val pontoTorraSelecionado = spinnerPontoTorra.selectedItem.toString()
            val textoValor = editTextValor.text.toString()
            val valorProduto = textoValor.toDouble()
            val isBlendSelected = switchBlend.isChecked
            val id = pegarID.text.toString()

            val novoProduto = id?.let {
                Produto(
                    idProduto = it,
                    tipoGrao = tipoGraoSelecionado,
                    pontoTorra = pontoTorraSelecionado,
                    valor = valorProduto,
                    blend = isBlendSelected
                )
            }

            novoProduto?.let {
                dao.alterarProduto(this, it.idProduto, it)
            }

        }
    }
}