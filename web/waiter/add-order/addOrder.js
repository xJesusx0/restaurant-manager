const getDishes = async () => {
    const url = `${config.SpringApi}/waiter/get-dishes`;
    try {
        const dishes = await request('GET', url);
        displayDishes(dishes);
    } catch (error) {
        console.error('Error fetching dishes:', error);
    }
};

const displayDishes = (dishes) => {
    const tableBody = document.getElementById('dishesTableBody');
    tableBody.innerHTML = '';

    dishes.forEach(dish => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${dish.name}</td>
            <td>${dish.description}</td>
            <td>$${dish.price.toFixed(2)}</td>
            <td>${dish.category}</td>
            <td>
                <button class="btn btn-primary btn-sm" data-dish='${JSON.stringify(dish)}' onclick='addToOrder(this)'>Add to Order</button>
            </td>
        `;

        tableBody.appendChild(row);
    });
};

const order = [];

const addToOrder = (button) => {
    const dish = JSON.parse(button.getAttribute('data-dish'));
    const existingDishIndex = order.findIndex(item => item.dishId === dish.dishId);

    if (existingDishIndex > -1) {
        order[existingDishIndex].quantity += 1;
    } else {
        dish.quantity = 1;
        order.push(dish);
    }

    updateOrderTable();
};

const updateOrderTable = () => {
    const orderTableBody = document.getElementById('orderTableBody');
    orderTableBody.innerHTML = '';
    let totalOrderPrice = 0;

    order.forEach(dish => {
        const dishTotal = dish.price * dish.quantity;
        totalOrderPrice += dishTotal;

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${dish.name}</td>
            <td>${dish.quantity}</td>
            <td>$${dish.price.toFixed(2)}</td>
            <td>$${dishTotal.toFixed(2)}</td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="removeFromOrder(${dish.dishId})">Remove</button>
            </td>
        `;

        orderTableBody.appendChild(row);
    });

    document.getElementById('totalOrderPrice').textContent = totalOrderPrice.toFixed(2);
};

const removeFromOrder = (dishId) => {
    const dishIndex = order.findIndex(item => item.dishId === dishId);

    if (dishIndex > -1) {
        if (order[dishIndex].quantity > 1) {
            order[dishIndex].quantity -= 1;
        } else {
            order.splice(dishIndex, 1);
        }
        updateOrderTable();
    }
};


document.addEventListener('DOMContentLoaded', getDishes);
