### Integrantes:
* #### RM 97373 Raquel Calmon
* #### RM 97306 Lau Costa
* #### RM 96553 Felipe Seiji
* #### RM 97041 Johan Marzolla
* #### RM 96542 Gustavo Ballogh


## Objetivo:

#### Aplicação voltada para a área de procurement: fornecendo um conjunto de funcionalidades para facilitar o processo de aquisição de produtos e serviços.

### [PITCH](https://www.youtube.com/watch?v=BVj8tq1eWTI&feature=youtu.be)
### [Diagrama de classes](procurement.png)
### [Apresentação Sprint4 Youtube]()
### [Link github](https://github.com/r4cs/procurement)

## Documentação de de endpoints:
### Use o [swagger](https://app-procurement.azurewebsites.net/swagger-ui/index.html) + documentação abaixo: 

### Entidade de Usuários:
#### Endpoint Base: https://app-procurement.azurewebsites.net/api/usuario
* GET    /api/usuario:      Obter todos os usuários (200 OK, 404 Not Found se não houver usuários).
* GET    /api/usuario/{id}: Obter um usuário por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/usuario:      Criar um novo usuário (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/usuario/{id}: Atualizar um usuário existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/usuario/{id}: Excluir um usuário por código (204 No Content, 404 Not Found se não encontrado).
#### Exemplo de JSON para criação de usuário:
{
"nome": "Nome do usuário",
"email": "user@example.com"
}


### Entidade de Produtos:
#### Endpoint Base: https://app-procurement.azurewebsites.net/api/produto
* GET    /api/produto:      Obter todos os produtos (200 OK, 404 Not Found se não houver produtos).
* GET    /api/produto/{id}: Obter um produto por SKU (200 OK, 404 Not Found se não encontrado).
* POST   /api/produto:      Criar um novo produto (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/produto/{id}: Atualizar um produto existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/produto/{id}: Excluir um produto por SKU (204 No Content, 404 Not Found se não encontrado).
* Exemplo genérico de produto:
#### Exemplo genérico de produto
{
"nome_produto": "Laptop Dell XPS 13",
"marca": "Dell",
"modelo": "XPS 13",
"especificacoes": "SDD 1tb, 32RAM"
}


### Entidade de Fornecedores:
* GET    /api/fornecedor:      Obter todos os fornecedores (200 OK, 404 Not Found se não houver fornecedores).
* GET    /api/fornecedor/{id}: Obter um fornecedor por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/fornecedor:      Criar um novo fornecedor (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/fornecedor/{id}: Atualizar um fornecedor existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/fornecedor/{id}: Excluir um fornecedor por código (204 No Content, 404 Not Found se não encontrado).

{
"razao_social": "Fornecedor FORNECE LTDA",
"cnpj": "44564432000100",
"nome_contato": "Maria da Silva",
"telefone": "(11) 444-1234",
"email": "maria@fornecedora.com",
"endereco": {
    "logradouro": "rua dos fornecedores",
    "numero": 55,
    "complemento": "nao",
    "bairro": "fornecedores",
    "cidade": "sao paulo",
    "estado": "SP",
    "cep": "0535-001"
    }
}

### Entidade de Solicitações de Compra:
#### Endpoint Base: https://app-procurement.azurewebsites.net/api/solicitacao
* GET    /api/solicitacao:      Obter todas as solicitações de compra (200 OK, 404 Not Found se não houver solicitações).
* GET    /api/solicitacao/{id}: Obter uma solicitação de compra por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/solicitacao:      Criar uma nova solicitação de compra (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/solicitacao/{id}: Atualizar uma solicitação de compra existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/solicitacao/{id}: Excluir uma solicitação de compra por código (204 No Content, 404 Not Found se não encontrado).
#### Exemplo de JSON para criação de solicitação de compra:
{
"produto": { "id": 1 },
"quantidade": 7,
"solicitante": { "id": 4 },
"status": "PENDING"
}

### Entidade de Pedidos de Compra:
#### Endpoint Base: https://app-procurement.azurewebsites.net/api/pedido
* GET    /api/pedido:      Obter todos os pedidos de compra (200 OK, 404 Not Found se não houver pedidos).
* GET    /api/pedido/{id}: Obter um pedido de compra por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/pedido:      Criar um novo pedido de compra (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/pedido/{id}: Atualizar um pedido de compra existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/pedido/{id}: Excluir um pedido de compra por código (204 No Content, 404 Not Found se não encontrado).
#### Exemplo genérico de pedido de compra:
{
"solicitacao": { "id": 2 },
"tipo_de_pagamento": "PIX"
}

### Entidade de Propostas de Venda:
#### Endpoint Base: https://app-procurement.azurewebsites.net/api/proposta
* GET    /api/proposta:      Obter todas as propostas de venda (200 OK, 404 Not Found se não houver propostas).
* GET    /api/proposta/{id}: Obter uma proposta de venda por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/proposta:      Criar uma nova proposta de venda (201 Created, 400 Bad Request se a entrada for inválida).* DELETE /api/proposta-venda/{codigo_proposta}: Excluir uma proposta de venda por código (204 No Content, 404 Not Found se não encontrado).
* PUT    /api/proposta/{id}: Atualizar proposta de venda por código (200 OK, 404 Not Found se não encontrado).
* DELETE /api/proposta/{id}: Excluir uma proposta de venda por código (200 OK, 404 Not Found se não encontrado).
#### Exemplo de JSON para criação de proposta de venda:
{
"pedido_compra": { "id": 1 },
"valor_unitario": 53.54,
"valor_total": 100.00,
"fornecedor": { "id": 1 }
}
