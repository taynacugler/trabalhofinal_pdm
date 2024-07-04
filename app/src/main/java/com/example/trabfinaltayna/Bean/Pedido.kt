package com.example.trabfinaltayna.Bean

import java.util.Date

class Pedido(
    var id_pedido: String,
    var data: Date,
    var fk_cpf: String
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id_pedido" to id_pedido,
            "data" to data,
            "fk_cpf" to fk_cpf
        )
    }
}