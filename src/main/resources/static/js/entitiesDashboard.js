// Função para preencher a tabela com os dados da entidade
function populateTable(entityName) {
    // Limpar o conteúdo da tabela
    let tableBody = document.querySelector('#entity-table tbody');
    tableBody.innerHTML = '';

    // Gerar os cabeçalhos da tabela com base no nome da entidade
    generateTableHeaders(entityName);

    // Fazer uma solicitação AJAX para obter os dados da entidade
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/' + entityName + "/all", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                console.log("response: " + response);
                // console.log("response.content: " + response.content);
                // Verificar se a resposta contém a propriedade 'content' que esperamos
                if (response.hasOwnProperty('content')) {
                    const data = response.content;

                    // Preencher a tabela com os dados da resposta
                    data.forEach(function (entry) {
                        const row = tableBody.insertRow();
                        // Mapear cada atributo e ajustar conforme necessário
                        Object.keys(entry).forEach(function (key) {
                            let value = entry[key];
                            // Ajustes específicos para cada tipo de dado
                            if (key === 'endereco' && entityName === 'fornecedor') {
                                value = value.estado;
                            }
                            if (key === 'pedido_compra' || key === 'solicitacao' || key === 'fornecedor' || key === 'solicitante' || key === 'produto') {
                                value = value.id;
                            }
                            if (key === 'tipoDePagamento' && entityName === 'pedido') {
                                // Se o tipo de pagamento for um enum, value = value
                            } else if (Array.isArray(value)) {
                                value = formatDate(value);
                            }
                            // Adicionar o valor à célula da linha
                            const cell = row.insertCell();
                            cell.textContent = value;
                        });
                    });
                } else if (Array.isArray(response)) {
                    // Preencher a tabela com os dados da resposta
                    response.forEach(function(entry) {
                        const row = tableBody.insertRow();
                        // Mapear cada atributo e ajustar conforme necessário
                        Object.keys(entry).forEach(function(key) {
                            let value = entry[key];
                            // Ajustes específicos para cada tipo de dado
                            if (key === 'endereco' && entityName === 'fornecedor') {
                                value = value.estado;
                            }
                            if (key === 'pedido_compra' || key === 'solicitacao' || key === 'fornecedor' || key === 'solicitante' || key === 'produto') {
                                value = value.id;
                            }
                            if (key === 'tipoDePagamento' && entityName === 'pedido') {
                                // Se o tipo de pagamento for um enum, value = value
                            } else if (Array.isArray(value)) {
                                value = formatDate(value);
                            }
                            // Adicionar o valor à célula da linha
                            const cell = row.insertCell();
                            cell.textContent = value;
                        });
                    });
                } else {
                    console.error("Response does not contain 'content' property:", response);
                }
            } else {
                // Se a solicitação falhar, exibir uma mensagem de erro na tabela
                const row = tableBody.insertRow();
                const cell = row.insertCell();
                cell.textContent = 'Erro ao carregar os dados';
                // cell.colSpan = 3; // Supondo que sua entidade tenha 3 atributos
            }
        }
    };
    xhr.send();
}

// Função para gerar os cabeçalhos das tabelas
function generateTableHeaders(entityName) {
    // Obter a lista de atributos da entidade
    let attributes = [];
    switch (entityName) {
        case 'usuario':
            attributes = ['ID', 'Nome', 'Email'];
            break;
        case 'fornecedor':
            attributes = ['ID', 'Razão Social', 'CNPJ', 'Nome Contato', 'Telefone', 'Email', 'Endereço'];
            break;
        case 'pedido':
            attributes = ['ID', 'Solicitação', 'Tipo de Pagamento', 'Data do Pedido'];
            break;
        case 'produto':
            attributes = ['ID', 'Nome do Produto', 'Modelo', 'Marca', 'Especificações'];
            break;
        case 'proposta':
            attributes = ['ID', 'Pedido de Compra', 'Valor Unitário', 'Valor Total', 'Fornecedor'];
            break;
        case 'solicitacao':
            attributes = ['ID', 'Produto', 'Quantidade', 'Solicitante', 'Status', 'Data da Solicitação'];
            break;
        default:
            console.error('Entidade desconhecida:', entityName);
            break;
    }

    // Obter a tabela
    const tableHeader = document.querySelector('#entity-table thead tr');

    // Limpar os cabeçalhos existentes
    tableHeader.innerHTML = '';

    // Adicionar os cabeçalhos à tabela
    attributes.forEach(function(attribute) {
        const th = document.createElement('th');
        th.textContent = attribute;
        tableHeader.appendChild(th);
    });
}

// Função para lidar com o clique nas entidades
function handleEntityClick(entityName) {
    populateTable(entityName);
}

function formatDate(value) {
    let date = new Date(value[0], value[1] - 1, value[2], value[3], value[4], value[5]);
    return date.toLocaleString();
}

function toggleVisibility(tagId) {
    // Ocultar todos os iframes dos dashboards
    const iframes = document.querySelectorAll('.dashboard-iframe');
    iframes.forEach(function(iframe) {
        iframe.style.visibility = 'hidden';
        iframe.style.display = 'none';
    });

    // Tornar visível o iframe correspondente ao ID passado
    const iframe = document.getElementById(tagId);
    iframe.style.visibility = 'visible';
    iframe.style.display = 'block';
}

// Função para limpar a tabela
function clearTable() {
    // Limpar o conteúdo do corpo da tabela
    let tableBody = document.querySelector('#entity-table tbody');
    tableBody.innerHTML = '';

    // Limpar os cabeçalhos da tabela
    let tableHeader = document.querySelector('#entity-table thead tr');
    tableHeader.innerHTML = '';
}