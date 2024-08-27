const validateLogin = () => {
	const username = document.getElementById('username').value;
	const password = document.getElementById('password').value;

	const url = `${config.SpringApi}/auth/login`;
	console.log(url);

	const requestBody = {
		'username': username,
		'password': password
	}

	const roles = {
		'1':'waiter',
		'2':'admin',
		'3':'kitchen'
	}

	request('POST', url, requestBody)
		.then(response => {
			console.log(response)
			
			const role = roles[response.role];

			fetchRoutes()
			.then(data => {
				const route = data[role];
				window.location.href = route;
			})

		
		})
}
