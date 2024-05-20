$(document).ready(function() {
    $('#creationForm').submit(function(event) {
        event.preventDefault();
        console.log('Name:', $('#name').val());
        console.log('Type:', $('#type').val());
        console.log('Document:', $('#documentNumber').val());
        console.log('State:', $('#state').val());
        var phoneNumbers = [];
        $('.phone-input').each(function() {
            phoneNumbers.push($(this).val());
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
            error: function(xhr, status, error) {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Houve um problema ao tentar criar o fornecedor! Tente novamente.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        });
    });
});
