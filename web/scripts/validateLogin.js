document.addEventListener('pageshow',(event) => {
	const data = localStorage.getItem('access_token');

    if (!data) {
        alert('Ha ocurrido un error con la sesion');
        window.location.href = '/';
    }



});

