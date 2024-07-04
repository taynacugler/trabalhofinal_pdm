package com.example.trabfinaltayna.Bean

class ItemPedido(
    var idItemPedido: String = "",
    var fk_idPedido: String,
    var fk_idProduto: String,
    var quantidade: Int
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "fk_idPedido" to fk_idPedido,
            "fk_idProduto" to fk_idProduto,
            "quantidade" to quantidade
        )
    }
}