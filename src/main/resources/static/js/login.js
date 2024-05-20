$(document).ready(function() {
    $('#loginButton').on('click', function(event) {
        event.preventDefault();
        var email = $('#email').val();
        var password = $('#password').val();

        $.ajax({
            url: '/auth/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                password: password
            }),

            success: function(response) {
                localStorage.setItem('authToken', response.token);
                localStorage.setItem('userId', response.userId);
                if(response.redirectUrl) {
                    loadPage('/' + response.redirectUrl);
                }
            },
            error: function(xhr, status, error) {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Não foi possível fazer a autenticação!.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                }).then((result)=>{
                    location.reload();
                });
            }
        });
    });
});
