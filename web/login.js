const validateLogin = async () => {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const url = `${config.SpringApi}/auth/login`;
    console.log('Request URL:', url);

    const requestBody = {
        "username": username,
        "password": password
    };

	const roles = {
		'1':'waiter',
		'2':'admin',
		'3':'kitchen'
	};

    try {
        const response = await request('POST',url,requestBody);

		console.log(response)
		const role = roles[response.role];
	
		fetchRoutes()
		.then(data => {
			const route = data[role];
			window.location.href = route;
		})		
    } catch (error) {
        console.error('Fetch Error:', error);
    }
};