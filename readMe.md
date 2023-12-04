
## Objetivo:

#### Aplicação voltada para a área de procurement: fornecendo um conjunto completo de funcionalidades para facilitar o processo de aquisição de produtos e serviços.

### [PITCH](https://www.youtube.com/watch?v=BVj8tq1eWTI&feature=youtu.be)

## Documentação de de endpoints:
### Use o [swagger](http://localhost:8080/swagger-ui/index.html) ou 

### Entidade de Usuários: http://localhost:8080
* GET    /api/usuario:                  Obter todos os usuários (200 OK, 404 Not Found se não houver usuários).
* GET    /api/usuario/{codigo_usuario}: Obter um usuário por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/usuario:                  Criar um novo usuário (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/usuario/{codigo_usuario}: Atualizar um usuário existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/usuario/{codigo_usuario}: Excluir um usuário por código (204 No Content, 404 Not Found se não encontrado).

ex genérico json:
{
"nome": "Nome do usuario",
"senha": "p@ssw0rd",
"email": "user@example.com"
}


### Entidade de Solicitações de Compra:
* GET    /api/solicitacao:                         Obter todas as solicitações de compra (200 OK, 404 Not Found se não houver solicitações).
* GET    /api/solicitacao/{codigo_solicitacao}:    Obter uma solicitação de compra por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/solicitacao:                         Criar uma nova solicitação de compra (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/solicitacao/{codigo_solicitacao}:    Atualizar uma solicitação de compra existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/solicitacao/{codigo_solicitacao}:    Excluir uma solicitação de compra por código (204 No Content, 404 Not Found se não encontrado).

ex genérico json:
{
"sku": {
"sku": "SMTVLGOLED4K"
},
"quantidade": 7,
"solicitante_id": {
"id": 4
},
"aprovador_id": {
"id": 2
},
"status": "PENDING",
"motivo_recusado": "",
"data_solicitacao": "2024-10-25T19:09:17"
}


### Entidade de Pedidos de Compra:
* GET    /api/pedido:                 Obter todos os pedidos de compra (200 OK, 404 Not Found se não houver pedidos).
* GET    /api/pedido/{codigo_pedido}: Obter um pedido de compra por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/pedido:                 Criar um novo pedido de compra (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/pedido/{codigo_pedido}: Atualizar um pedido de compra existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/pedido/{codigo_pedido}: Excluir um pedido de compra por código (204 No Content, 404 Not Found se não encontrado).

ex genérico de pedido de compra:
{
"solicitacao_id": {
"id": 2
},
"fornecedor_id": {
"id": 3
},
"data_entrega_prevista": "2023-11-20"
}


### Entidade de Produtos:
* GET    /api/produtos:       Obter todos os produtos (200 OK, 404 Not Found se não houver produtos).
* GET    /api/produtos/{sku}: Obter um produto por SKU (200 OK, 404 Not Found se não encontrado).
* POST   /api/produtos:       Criar um novo produto (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/produtos/{sku}: Atualizar um produto existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/produtos/{sku}: Excluir um produto por SKU (204 No Content, 404 Not Found se não encontrado).

ex genérico de produto:
{
"nome_produto": "Laptop Dell XPS 13",
"estoque": 50,
"valor_unitario": 1499.99
}


### Entidade de Fornecedores:
* GET    /api/fornecedor:                     Obter todos os fornecedores (200 OK, 404 Not Found se não houver fornecedores).
* GET    /api/fornecedor/{codigo_fornecedor}: Obter um fornecedor por código (200 OK, 404 Not Found se não encontrado).
* POST   /api/fornecedor:                     Criar um novo fornecedor (201 Created, 400 Bad Request se a entrada for inválida).
* PUT    /api/fornecedor/{codigo_fornecedor}: Atualizar um fornecedor existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/fornecedor/{codigo_fornecedor}: Excluir um fornecedor por código (204 No Content, 404 Not Found se não encontrado).

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
