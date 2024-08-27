const fetchRoutes = () => {
    return fetch('/config/routes.json')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => data)
        .catch(error => {
            console.error('Error fetching routes:', error);
            return null;
        });
};
