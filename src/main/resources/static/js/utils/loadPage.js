function loadPage(url) {
    const token = localStorage.getItem('authToken');
    $.ajax({
        url: url,
        type: 'GET',
        headers: {
            'Authorization': token ? 'Bearer ' + token : ''
        },
        success: function(response) {
            $('body').html(response);
        },
        error: function(xhr, status, error) {
            Swal.fire({
                title: 'Não autorizado.',
                text: 'Faça login novamente.',
                icon: 'error',
                confirmButtonText: 'OK'
            }).then((result)=>{
                if (result.value) {
                    window.location.href = '/login';
                }
            });
        }
    });
}
