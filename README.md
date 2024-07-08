# Café Ouro Negro de Minas

## Programa para Venda de Café

### Descrição

Este programa foi desenvolvido para a empresa “Café Ouro Negro de Minas”, fabricante de café especial. Ele permite a inserção de clientes, produtos e pedidos. Cada vendedor pode rodar o programa em seu celular, proporcionando as seguintes funcionalidades:

### Funcionalidades

#### 1. Tela Inicial
- **Clientes**: Dono do supermercado que comprará nosso café.
- **Produtos**: Diversos tipos de café disponíveis.
- **Pedidos**: Gestão dos pedidos realizados.

#### 2. Clientes
- **Cadastrar Clientes**:
  - **CPF** (String)
  - **Nome** (String)
  - **Telefone** (String)
  - **Endereço** (String)
  - **Instagram** (String)
- **Operações**:
  - Inserir
  - Listar
  - Busca individual (pelo ID)
  - Deletar
  - Alterar

#### 3. Produtos
- **Cadastrar Produto**:
  - **ID do Produto** (String)
  - **Tipo do Grão** (String: Arábica do Cerrado / Conilon)
  - **Ponto da Torra** (média / forte)
  - **Valor** (Double)
  - **Blend** (boolean)
- **Operações**:
  - Inserir
  - Listar
  - Busca individual (pelo ID)
  - Deletar
  - Alterar

#### 4. Pedidos
- **Novo Pedido**: Vincula um cliente a um novo pedido.
- **Detalhes do Pedido**:
  - Código do Pedido
  - Data
  - Itens inseridos no pedido
- **Operações**:
  - Listar pedidos (por cliente)
  - Alterar pedido
  - Excluir pedidos

### Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Plataforma**: Android
- **Banco de Dados**: Firebase Firestore
