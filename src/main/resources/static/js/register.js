$(document).ready(function() {
    $('#registerButton').on('click', function(event) {
        event.preventDefault();
        var email = $('#email').val();
        var password = $('#password').val();

        $.ajax({
            url: '/auth/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                password: password
            }),

            success: function(response) {
                Swal.fire({
                    title: 'Conta criada com sucesso!',
                    text: 'Conta criada com sucesso, agora faça login.',
                    confirmButtonText: 'OK'
                }).then((result)=>{
                    window.location.href="/auth/login";
                });
            },
            error: function(xhr, status, error) {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Ocorreu um erro ao criar sua conta. Tente novamente',
                    confirmButtonText: 'OK'
                }).then((result)=>{
                    location.reload();
                });
            }
        });
    });
});
