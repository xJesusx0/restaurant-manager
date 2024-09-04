const getEmployeeInfo = async () => {
    const route = new URL(window.location.href);
    const params = new URLSearchParams(route.search);

    const employeeId = params.get('employeeId');
    const url = `${config.SpringApi}/admin/get-employee-by-id?employeeId=${employeeId}`;

    try {
        const response = await request('GET', url);
        const employee = response;

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

const update = async () => {
    const url = `${config.SpringApi}/admin/update-employee`;

    let employee = {
        'employeeId': document.getElementById('employeeId').value,
        'firstName': document.getElementById('firstName').value,
        'lastName': document.getElementById('lastName').value,
        'email': document.getElementById('email').value,
        'phone': document.getElementById('phone').value,
        'hireDate': document.getElementById('hireDate').value,
        'position': document.getElementById('position').value,
        'status': document.getElementById('status').value
    };
    
    console.log(employee);

    try {
        const response = await request('PUT', url, employee);
        console.log('Empleado actualizado con éxito:', response);
        alert('Empleado actualizado correctamente')
        location.reload();

    } catch (error) {
        console.error('Error al actualizar el empleado:', error);
    }
};