$(document).ready(function() {
    $('#zipCode').on('blur', function() {
        const zipCode = $(this).val().replace(/\D/g, '');
        if (zipCode !== "" && /^[0-9]{8}$/.test(zipCode)) {
            $.getJSON("https://viacep.com.br/ws/"+ zipCode +"/json/", function(data) {
                if (!("erro" in data)) {
                    $('#street').val(data.logradouro);
                    $('#neighborhood').val(data.bairro);
                    $('#city').val(data.localidade);
                    $('#state').val(data.uf);
                } else {
                    $('#zipCode').val("");
                    Swal.fire({
                        title: 'Erro!',
                        text: 'CEP inválido. Por favor, tente novamente.',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            });
        } else {
            $('#zipCode').val("");
            Swal.fire({
                title: 'Erro!',
                text: 'CEP deve ser preenchido com números. Por favor, tente novamente.',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
    });
});
