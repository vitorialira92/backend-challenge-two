$(document).ready(function() {
    $('#creationForm').submit(function(event) {
        event.preventDefault();

        var documentValue = $('#documentNumber').val();
        var type = $('#type').val();

        var validated = type === 'INDIVIDUAL' ? (validateCPF(documentValue)) : (validateCNPJ(documentValue));
        console.log("tp ", validateCPF(documentValue))
        if(!validated)
        {
            document.getElementById('documentNumber').classList.add('is-invalid');
            var documentType = type === 'INDIVIDUAL' ? 'CPF' : 'CNPJ';
            Swal.fire({
                title: 'Erro!',
                text: documentType + ' inválido. Por favor, tente novamente.',
                icon: 'error',
                confirmButtonText: 'OK'
            });
            return;
        } else {
            document.getElementById('documentNumber').classList.remove('is-invalid');
        }

        var phoneNumbers = [];
        $('.phone-input').each(function() {
            var phoneNumber = $(this).val().trim();
            if (phoneNumber !== '') {
                phoneNumbers.push(phoneNumber);
            }
        });

        const token = localStorage.getItem('authToken');
        const userId = localStorage.getItem('userId');

        $.ajax({
            url: '/supplier/' + userId,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                name: $('#name').val(),
                type: $('#type').val(),
                document: $('#documentNumber').val(),
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
            success: function(response) {
                console.log('Formulário enviado com sucesso!');
                loadPage('view-supplier/' + response)
            },
            error: function(xhr) {
                if(xhr.status === 409){
                    Swal.fire({
                        title: 'Erro!',
                        text: 'Você já possui um fornecedor com esse CPF/CNPJ.',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }else{
                    Swal.fire({
                        title: 'Erro!',
                        text: 'Houve um problema ao tentar criar o fornecedor! Tente novamente.',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            }
        });
    });
});
