// Função para preencher a tabela com os dados da entidade
function populateTable(entityName) {

    // Limpar o conteúdo da tabela
    let tableBody = document.querySelector('#entity-table tbody');
    console.log("*** populate table :: Table body: " + tableBody)
    console.log("*** populate table :: entityName: " + entityName)
    tableBody.innerHTML = '';

    // Fazer uma solicitação AJAX para obter os dados da entidade
    // const xhr = new XMLHttpRequest();
    // xhr.open('GET', '/api/' + entityName, true);
    // xhr.onreadystatechange = function() {
    //     if (xhr.readyState === 4) {
    //         if (xhr.status === 200) {
    //             const data = JSON.parse(xhr.responseText);
    //             if (data.length === 0) {
    //                 // Se não houver dados, exibir uma mensagem na tabela
    //                 const row = tableBody.insertRow();
    //                 const cell = row.insertCell();
    //                 cell.textContent = 'Nenhum dado disponível';
    //                 cell.colSpan = getNumberOfAttributes(data[0]);
    //             } else {
    //                 // Se houver dados, preencher a tabela com os dados da entidade
    //                 const firstEntry = data[0];
    //                 const attributes = Object.keys(firstEntry);
    //
    //                 // Criar o cabeçalho da tabela se ainda não existir
    //                 const tableHeader = document.querySelector('#entity-table thead tr');
    //                 if (tableHeader.innerHTML === '') {
    //                     attributes.forEach(function(attribute) {
    //                         const th = document.createElement('th');
    //                         th.textContent = attribute;
    //                         tableHeader.appendChild(th);
    //                     });
    //                 }
    //
    //                 // Preencher os dados da tabela
    //                 data.forEach(function(entry) {
    //                     const row = tableBody.insertRow();
    //                     attributes.forEach(function(attribute) {
    //                         const cell = row.insertCell();
    //                         cell.textContent = entry[attribute];
    //                     });
    //                 });
    //             }
    //         } else {
    //             // Se a solicitação falhar, exibir uma mensagem de erro na tabela
    //             const row = tableBody.insertRow();
    //             const cell = row.insertCell();
    //             cell.textContent = 'Erro ao carregar os dados';
    //             cell.colSpan = 3; // Supondo que sua entidade tenha 3 atributos
    //         }
    //     }
    // };
    // xhr.send();
}

// Função para obter o número de atributos em uma entrada
// function getNumberOfAttributes(entry) {
//     return Object.keys(entry).length;
// }