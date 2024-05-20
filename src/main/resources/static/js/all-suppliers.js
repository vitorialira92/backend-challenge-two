$(document).ready(function() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('authToken');

    $.ajax({
        url: `/supplier/all/${userId}`,
        type: 'GET',
        headers: {
            'Authorization': token ? 'Bearer ' + token : ''
        },
        success: function(suppliersList) {
            var suppliersHtml = '';
            suppliersList.forEach(function(supp) {
                suppliersHtml += `
                    <div class="custom-card">
                        <div class="custom-items">
                            <p class="card-title">${supp.name}</p>
                            <div class="icons">
                                <img src="/images/eye.png" class = "item" title="Visualizar" alt="Visualizar" onclick="loadPage('/view-supplier/${supp.id}')">
                                <img src="/images/edit.png" class = "item" title="Editar" alt="Editar" onclick="location.href='/edit-supplier/${supp.id}'">
                                <img src="/images/delete.png" class = "item" title="Apagar" alt="Apagar" onclick="deleteSupplier('${encodeURIComponent(supp.id)}')">    
                            </div>    
                        </div>
                       <p class="card-text">${supp.document}</p>
                    </div>
                `;
            });
            $('#suppliersListFrag').html(suppliersHtml);
        },
        error: function() {
            Swal.fire({
                title: 'Erro!',
                text: 'Houve um erro ao tentar encontrar os seus fornecedores! Tente novamente',
                icon: 'error',
                confirmButtonText: 'OK'
            }).then((result)=>{
                if (result.value) {
                    loadPage('/home-page');
                }
            });
        }
    });
});
function deleteSupplier(id) {
    if (confirm('Tem certeza que deseja apagar este fornecedor?')) {
        const token = localStorage.getItem('authToken');
        $.ajax({
            url: '/supplier/' + id,
            type: 'DELETE',
            headers: {
                'Authorization': token ? 'Bearer ' + token : ''
            },
            success: function(result) {
                loadPage('/all-suppliers')
            },
            error: function(err) {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Houve um erro ao tentar deletar o fornecedor! Tente novamente',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
                console.error('Error:', err);
            }
        });
    }
}