$(document).on('authenticationChecked', function(event, isAuthenticated) {
    if (!isAuthenticated) {
        return;
    }

    var supplierId = $('#supplierIdContainer').text();
    const token = localStorage.getItem('authToken');

    console.log(supplierId)

    $.ajax({
        url: `/supplier/${supplierId}`,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        },
        success: function(supplier) {
            var supplierType;
            var documentLabel;
            if(supplier.type ===  'INDIVIDUAL'){
                documentLabel = 'CPF';
                supplierType = 'PESSOA FÍSICA';

            }else{
                documentLabel = 'CNPJ';
                supplierType = 'PESSOA JURÍDICA';
            }

            $('#name').val(supplier.name);
            $('#documentNumber').val(supplier.document);
            $('#document-label').text(documentLabel);
            $('#type').val(supplierType);
            $('#activitiesDescription').val(supplier.activitiesDescription)
            $('#contactName').val(supplier.contactName)
            $('#contactEmail').val(supplier.contactEmail)
            $('#zipCode').val(supplier.zipCode)
            $('#state').val(supplier.state)
            $('#street').val(supplier.street)
            $('#complement').val(supplier.complement)
            $('#number').val(supplier.number)
            $('#neighborhood').val(supplier.neighborhood)
            $('#city').val(supplier.city)
            supplier.phoneNumbers.forEach(function(number) {
                const phoneHtml = `
                    <div class="phone-number">
                        <input class="phone-input custom-input" type="text"/>
                        <button type="button" class="remove-phone small-secondary-button">X</button>
                    </div>
                `;
                phoneInput.val(number);
                $('#phone-list').append(phoneHtml);
                $('.phone-input').mask("(00) 00000 - 0000");
            });
        },
        error: function() {
            Swal.fire({
                title: 'Erro!',
                text: 'Fornecedor não encontrado!',
                icon: 'error',
                confirmButtonText: 'OK'
            }).then((result)=>{
                if (result.value) {
                    window.location.href = '/all-suppliers';
                }
            });
        }
    });
    $('#updateForm').submit(function(event) {
        event.preventDefault();

        console.log("entrou");
        var phoneNumbers = [];
        $('.phone-input').each(function () {
            phoneNumbers.push($(this).val());
        });

        const token = localStorage.getItem('authToken');
        var supplierId = $('#supplierIdContainer').text();
        console.log("fazer put em " + supplierId)
        $.ajax({
            url: `/supplier/${supplierId}`,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                contactName: $('#contactName').val(),
                contactEmail: $('#contactEmail').val(),
                phoneNumbers: phoneNumbers,
                activitiesDescription: $('#activitiesDescription').val(),
                zipCode: $('#zipCode').val(),
                street: $('#street').val(),
                number: $('#number').val(),
                complement: $('#complement').val(),
                neighborhood: $('#neighborhood').val(),
                city: $('#city').val(),
                state: $('#state').val(),
            }),
            headers: {
                'Authorization': 'Bearer ' + token
            },
            success: function (response) {
                console.log("ok")
                loadPage('/view-supplier/' + supplierId)
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Houve um problema ao tentar atualizar o fornecedor! Tente novamente.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        });
    });
});
