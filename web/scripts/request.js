const request = async (method, url, data = null) => {
    const headers = {
        'Content-Type': 'application/json'
    };

    const requestOptions = {
        method: method,
        headers: headers,
    };

    if (method !== 'GET' && data !== null) {
        requestOptions.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(url, requestOptions);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const responseData = await response.json();
        return responseData;
    } catch (error) {
        console.error('Request Error:', error);
        throw error;
    }
};
