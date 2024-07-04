package com.example.trabfinaltayna

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.trabfinaltayna.Bean.Cliente
import com.example.trabfinaltayna.Bean.ItemPedido
import com.example.trabfinaltayna.Bean.Pedido
import com.example.trabfinaltayna.Bean.Produto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException

class DAO {

    private val db = FirebaseFirestore.getInstance()

    fun addCliente(context: Context, cliente: Cliente) {
        val clientesRef = db.collection("clientes")

        clientesRef.document(cliente.cpf).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Toast.makeText(context, "CPF já existe no banco de dados", Toast.LENGTH_LONG).show()
                } else {
                    clientesRef.document(cliente.cpf).set(cliente)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Cliente adicionado com sucesso", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Erro ao adicionar cliente: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Erro ao acessar banco de dados: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    fun addProduto(context: Context, produto: Produto) {
        val produtosRef = db.collection("produtos")

        produtosRef.document(produto.idProduto).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Toast.makeText(context, "ID do Produto já existe no banco de dados", Toast.LENGTH_LONG).show()
                } else {
                    produtosRef.document(produto.idProduto).set(produto)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Produto adicionado com sucesso", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Erro ao adicionar produto: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Erro ao acessar banco de dados: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun addPedido(context: Context, pedido: Pedido, items: List<ItemPedido>) {
        val pedidosRef = db.collection("pedidos")
        val itemsRef = db.collection("itemsPedido")

        pedidosRef.document(pedido.id_pedido).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Toast.makeText(context, "ID do pedido já existe no banco de dados", Toast.LENGTH_LONG).show()
                } else {
                    db.runTransaction { transaction ->
                        val pedidoDocRef = pedidosRef.document(pedido.id_pedido)
                        transaction.set(pedidoDocRef, pedido.toMap())

                        items.forEach { item ->
                            val itemDocRef = itemsRef.document()
                            item.idItemPedido = itemDocRef.id
                            item.fk_idPedido = pedido.id_pedido  // Atualizar o fk_idPedido para o ID fornecido
                            transaction.set(itemDocRef, item.toMap())
                        }
                    }.addOnSuccessListener {
                        Toast.makeText(context, "Pedido adicionado com sucesso", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener { e ->
                        Toast.makeText(context, "Erro ao adicionar pedido: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao acessar banco de dados: ${e.message}", e)
                Toast.makeText(context, "Erro ao verificar ID do pedido: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun procurarClientePorCpf(cpf: String, callback: (Cliente?) -> Unit) {
        val clientesRef = db.collection("clientes")

        clientesRef.whereEqualTo("cpf", cpf).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val cliente = documents.documents[0].toObject(Cliente::class.java)
                    Log.i("", "Erro ao iniciar a TerceiraTela ${cliente?.nome}")

                    callback(cliente)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }
    fun procurarProdutoPorId(idProduto: String, callback: (Produto?) -> Unit) {
        val produtosRef = db.collection("produtos")

        produtosRef.whereEqualTo("idProduto", idProduto).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val produto = documents.documents[0].toObject(Produto::class.java)
                    callback(produto)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                Log.e("Firestore", "Erro ao acessar banco de dados: ${it.message}")
                callback(null)
            }
    }

    fun procurarPedidoPorId(idPedido: String, callback: (Pedido?) -> Unit) {
        val pedidosRef = db.collection("pedidos")

        pedidosRef.whereEqualTo("id_pedido", idPedido).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val pedidoDoc = documents.documents[0]
                    val pedido = pedidoDoc.toObject(Pedido::class.java)
                    callback(pedido)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao acessar banco de dados: ${e.message}")
                callback(null)
            }
    }
    fun buscarItensPedidoPorId(idPedido: String, callback: (List<ItemPedido>?) -> Unit) {
        val pedidosRef = db.collection("pedidos")
        val itemsRef = db.collection("itemsPedido")

        pedidosRef.whereEqualTo("id_pedido", idPedido).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val pedidoDoc = documents.documents[0]
                    val pedidoId = pedidoDoc.getString("id_pedido")

                    itemsRef.whereEqualTo("fk_idPedido", pedidoId).get()
                        .addOnSuccessListener { itemDocuments ->
                            val items = itemDocuments.mapNotNull { itemDoc ->
                                itemDoc.toObject(ItemPedido::class.java)
                            }
                            callback(items)
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore", "Erro ao buscar itens do pedido: ${e.message}", e)
                            callback(null)
                        }
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao acessar banco de dados: ${e.message}", e)
                callback(null)
            }
    }
    fun deletarItemPedido(idItemPedido: String, callback: (Boolean) -> Unit) {
        val itemsRef = db.collection("itemsPedido")

        itemsRef.document(idItemPedido).delete()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao deletar item do pedido: ${e.message}", e)
                callback(false)
            }
    }

    fun alterarCliente(context: Context, cpf: String, cliente: Cliente) {
        val clientesRef = db.collection("clientes")

        clientesRef.document(cpf).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    clientesRef.document(cpf).set(cliente)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Cliente alterado com sucesso", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Erro ao alterar cliente: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(context, "Cliente não encontrado para alteração", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Erro ao acessar banco de dados: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    fun alterarProduto(context: Context, idProduto: String, produto: Produto) {
        val produtosRef = db.collection("produtos")

        produtosRef.document(idProduto).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    produtosRef.document(idProduto).set(produto)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Produto alterado com sucesso", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Erro ao alterar produto: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(context, "Produto não encontrado para alteração", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Erro ao acessar banco de dados: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    fun deletarCliente(context: Context, cpf: String) {
        val clientesRef = db.collection("clientes")

        clientesRef.document(cpf).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    clientesRef.document(cpf).delete()
                        .addOnSuccessListener {
                            Toast.makeText(context, "Cliente deletado com sucesso", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Erro ao deletar cliente: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(context, "Cliente não encontrado para deleção", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Erro ao acessar banco de dados: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    fun deletarProduto(context: Context, idProduto: String) {
        val produtosRef = db.collection("produtos")
        produtosRef.document(idProduto).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    produtosRef.document(idProduto).delete()
                        .addOnSuccessListener {
                            Toast.makeText(context, "Produto deletado com sucesso", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Erro ao deletar produto: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(context, "Produto não encontrado para deleção", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Erro ao acessar banco de dados: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    fun deletarPedidoPorId(context: Context, idPedido: String) {
        val pedidosRef = db.collection("pedidos")
        val itemsRef = db.collection("itemsPedido")

        db.runTransaction { transaction ->
            val pedidoDocRef = pedidosRef.document(idPedido)

            // Verificar se o pedido existe
            val pedidoSnapshot = transaction.get(pedidoDocRef)
            if (!pedidoSnapshot.exists()) {
                throw FirebaseFirestoreException("Pedido não encontrado", FirebaseFirestoreException.Code.NOT_FOUND)
            }

//            // Deletar todos os itens associados ao pedido
//            val itemsQuerySnapshot = transaction.get(itemsRef.whereEqualTo("fk_idPedido", idPedido))
//            for (itemDoc in itemsQuerySnapshot.documents) {
//                transaction.delete(itemsRef.document(itemDoc.id))
//            }

            // Deletar o pedido
            transaction.delete(pedidoDocRef)
        }.addOnSuccessListener {
            Toast.makeText(context, "Pedido deletado com sucesso", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { e ->
            Log.e("Firestore", "Erro ao deletar pedido: ${e.message}", e)
            Toast.makeText(context, "Erro ao deletar pedido: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    fun listarTodosClientes(callback: (List<Cliente>) -> Unit) {
        val clientesRef = db.collection("clientes")

        clientesRef.get()
            .addOnSuccessListener { documents ->
                val clientes = documents.mapNotNull { it.toObject(Cliente::class.java) }
                callback(clientes)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao listar clientes: ${e.message}")
                callback(emptyList())
            }
    }
    fun listarTodosProdutos(callback: (List<Produto>) -> Unit) {
        Log.d("DAO", "listarTodosProdutos called") // Log para verificar a chamada da função
        val produtosRef = db.collection("produtos")

        produtosRef.get()
            .addOnSuccessListener { documents ->
                Log.d("DAO", "listarTodosProdutos success") // Log para sucesso na obtenção dos documentos
                val produtos = documents.mapNotNull { it.toObject(Produto::class.java) }
                callback(produtos)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao listar produtos: ${e.message}")
                callback(emptyList())
            }
    }


}
