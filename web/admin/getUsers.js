document.addEventListener('DOMContentLoaded', async () => {
    renderTable();
});

const renderTable = async () => {
    const users = await getUsers(); 
    const tableBody = document.getElementById('table-body');

    tableBody.innerHTML = '';

    if (users.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="7">No users found</td></tr>';
        return;
    }

    users.forEach(user => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${user.employeeId}</td>
            <td>${user.firstName} ${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.position}</td>
            <td>${user.status}</td>
            <td>
                <button class="btn btn-warning btn-sm me-2" onclick="editUser(${user.employeeId})">Modificar</button>
                <button class="btn btn-danger btn-sm" onclick="changeStatus(${user.employeeId})">Inactivar</button>
            </td>
        `;

        tableBody.appendChild(row);
    });
};

const getUsers = async () => {
    const url = `${config.SpringApi}/admin/get-employees`;
    
    try {
        const response = await request('GET', url);
        return response;
    } catch (error) {
        console.error('Error fetching users:', error);
        return [];
    }
};

const editUser = (employeeId) => {
    console.log(`Modificar usuario con ID: ${employeeId}`);
};

const changeStatus = (employeeId) => {
    console.log(`Cambiar estado a inactivo para usuario con ID: ${employeeId}`);
};