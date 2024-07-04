package com.example.trabfinaltayna.Telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabfinaltayna.DAO
import com.example.trabfinaltayna.R

class TelaPedido : AppCompatActivity()  {

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main7)

     val dao = DAO()

     val botaoNovoPedido: Button = findViewById(R.id.bt_addpedido)
     val showPedido: Button = findViewById(R.id.bt_ped)

     botaoNovoPedido.setOnClickListener {
         val intent = Intent(this, NovoPedido::class.java)
         startActivity(intent)
     }
     showPedido.setOnClickListener {

         val intent = Intent(this, VerPedido::class.java)
                startActivity(intent)

     }
 }
}