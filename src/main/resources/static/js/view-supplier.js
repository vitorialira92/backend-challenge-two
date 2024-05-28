$(document).ready(function() {
    $(document).on('authenticationChecked', function(event, isAuthenticated) {
        if (!isAuthenticated) {
            return;
        }

        var supplierId = $('#supplierIdContainer').text();
        const token = localStorage.getItem('authToken');
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
                    var phoneDiv = $('<div class="phone-number"></div>');

                    var phoneInput = $('<input class="phone-input custom-input" type="text" readonly>');
                    phoneInput.val(number);

                    phoneDiv.append(phoneInput);

                    $('#phone-list').append(phoneDiv);
                });
            },
            error: function() {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Houve um erro ao buscar os dados. Tente novamente mais tarde.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                }).then((result)=>{
                    if (result.value) {
                        loadPage('/all-suppliers');
                    }
                });
            }
        });
    });
})