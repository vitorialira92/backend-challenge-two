$(document).ready(function() {
    const userId = localStorage.getItem('userId');
    if(!userId){
        Swal.fire({
            title: 'Erro!',
            text: 'Usuário deslogado. Faça login novamente',
            icon: 'error',
            confirmButtonText: 'OK'
        }).then((result)=>{
            if (result.value) {
                window.location.href = '/login';
            }
        });
    }else{
        $(document).trigger('authenticationChecked', [true]);
    }
});