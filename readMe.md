## PITCH : https://www.youtube.com/watch?v=BVj8tq1eWTI&feature=youtu.be

## Documentação de de endpoints:

### Entidade de Usuários:
* GET /api/usuario: Obter todos os usuários (200 OK, 404 Not Found se não houver usuários).
* GET /api/usuario/{codigo_usuario}: Obter um usuário por código (200 OK, 404 Not Found se não encontrado).
* POST /api/usuario: Criar um novo usuário (201 Created, 400 Bad Request se a entrada for inválida).
* PUT /api/usuario/{codigo_usuario}: Atualizar um usuário existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/usuario/{codigo_usuario}: Excluir um usuário por código (204 No Content, 404 Not Found se não encontrado).

### Entidade de Solicitações de Compra:
* GET /api/solicitacao: Obter todas as solicitações de compra (200 OK, 404 Not Found se não houver solicitações).
* GET /api/solicitacao/{codigo_solicitacao}: Obter uma solicitação de compra por código (200 OK, 404 Not Found se não encontrado).
* POST /api/solicitacao: Criar uma nova solicitação de compra (201 Created, 400 Bad Request se a entrada for inválida).
* PUT /api/solicitacao/{codigo_solicitacao}: Atualizar uma solicitação de compra existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/solicitacao/{codigo_solicitacao}: Excluir uma solicitação de compra por código (204 No Content, 404 Not Found se não encontrado).

### Entidade de Pedidos de Compra:
* GET /api/pedidos: Obter todos os pedidos de compra (200 OK, 404 Not Found se não houver pedidos).
* GET /api/pedidos/{codigo_pedido}: Obter um pedido de compra por código (200 OK, 404 Not Found se não encontrado).
* POST /api/pedidos: Criar um novo pedido de compra (201 Created, 400 Bad Request se a entrada for inválida).
* PUT /api/pedidos/{codigo_pedido}: Atualizar um pedido de compra existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/pedidos/{codigo_pedido}: Excluir um pedido de compra por código (204 No Content, 404 Not Found se não encontrado).

### Entidade de Produtos:
* GET /api/produtos: Obter todos os produtos (200 OK, 404 Not Found se não houver produtos).
* GET /api/produtos/{sku}: Obter um produto por SKU (200 OK, 404 Not Found se não encontrado).
* POST /api/produtos: Criar um novo produto (201 Created, 400 Bad Request se a entrada for inválida).
* PUT /api/produtos/{sku}: Atualizar um produto existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/produtos/{sku}: Excluir um produto por SKU (204 No Content, 404 Not Found se não encontrado).

### Entidade de Fornecedores:
* GET /api/fornecedores: Obter todos os fornecedores (200 OK, 404 Not Found se não houver fornecedores).
* GET /api/fornecedores/{codigo_fornecedor}: Obter um fornecedor por código (200 OK, 404 Not Found se não encontrado).
* POST /api/fornecedores: Criar um novo fornecedor (201 Created, 400 Bad Request se a entrada for inválida).
* PUT /api/fornecedores/{codigo_fornecedor}: Atualizar um fornecedor existente (200 OK, 404 Not Found se não encontrado).
* DELETE /api/fornecedores/{codigo_fornecedor}: Excluir um fornecedor por código (204 No Content, 404 Not Found se não encontrado).
