$(document).ready(function() {
    $('#password').on('keyup', checkPassword);

    $('#registerButton').on('click', function(event) {
        event.preventDefault();
        var email = $('#email').val();
        var password = $('#password').val();

        if(email === ''){
            Swal.fire({
                icon: 'error',
                title: 'E-mail obrigatório',
                text: 'Por favor, insira um endereço de e-mail.',
                confirmButtonText: 'OK'
            });
            return;
        }

        if(password === ''){
            Swal.fire({
                icon: 'error',
                title: 'Senha obrigatória',
                text: 'Por favor, insira uma senha.',
                confirmButtonText: 'OK'
            });
            return;
        }

        var pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

        if (!pattern.test(email)) {
            Swal.fire({
                icon: 'error',
                title: 'E-mail inválido',
                text: 'Digite um e-mail válido',
                confirmButtonText: 'OK'
            });
            return;
        }

        if(!checkPassword()){
            Swal.fire({
                icon: 'error',
                title: 'Senha inválida',
                text: 'Preencha todos os requisitos para a senha',
                confirmButtonText: 'OK'
            });
            return;
        }

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
                    icon: 'success',
                    title: 'Conta criada com sucesso!',
                    text: 'Sua conta foi criada com sucesso, agora faça login.',
                    confirmButtonText: 'OK'
                }).then((result)=>{
                    window.location.href="/login";
                });
            },
            error: function(xhr) {
                if(xhr.status === 409){
                    Swal.fire({
                        icon: 'error',
                        title: 'E-mail em uso!',
                        text: 'Já existe uma conta utilizando esse e-mail. Use outro e-mail ou faça login.',
                        confirmButtonText: 'OK'
                    });
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: 'Erro!',
                        text: 'Ocorreu um erro interno ao criar sua conta. Tente novamente',
                        confirmButtonText: 'OK'
                    });
                }
            }
        });

    });
});

function checkPassword() {
    const password = $('#password').val();
    const length = password.length >= 8;
    const number = /[0-9]/.test(password);
    const upperCase = /[A-Z]/.test(password);
    const lowerCase = /[a-z]/.test(password);
    const specialChar = /[\@\#\$\%\&\*\(\)\-\+\!]/.test(password);

    $('#length').toggleClass('met', length);
    $('#number').toggleClass('met', number);
    $('#uppercase').toggleClass('met', upperCase);
    $('#lowercase').toggleClass('met', lowerCase);
    $('#specialChar').toggleClass('met', specialChar);

    return length && number && upperCase && lowerCase && specialChar;
}