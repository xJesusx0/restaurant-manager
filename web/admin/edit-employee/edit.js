const getEmployeeInfo = async () => {
    const route = new URL(window.location.href);
    const params = new URLSearchParams(route.search);

    const employeeId = params.get('employeeId');
    const url = `${config.SpringApi}/admin/get-employee-by-id?employeeId=${employeeId}`;

    try {
        const response = await request('GET', url);
        const employee = response; // Asumiendo que `response` es el objeto del empleado

        // Rellenar el formulario con los datos del empleado
        document.getElementById('employeeId').value = employee.employeeId;
        document.getElementById('firstName').value = employee.firstName;
        document.getElementById('lastName').value = employee.lastName;
        document.getElementById('email').value = employee.email;
        document.getElementById('phone').value = employee.phone;
        document.getElementById('hireDate').value = employee.hireDate;
        document.getElementById('position').value = employee.position;
        document.getElementById('status').value = employee.status;
    } catch (error) {
        console.error('Error al obtener los datos del empleado:', error);
    }
};

getEmployeeInfo();

