const API_URL_1 = "http://localhost:8081";
const API_URL_2 = "http://localhost:8082";

async function sendOrder() {

    const customerName =
        document.getElementById("customerName").value.trim();

    const items =
        document.getElementById("items").value.trim();

    const responseContainer =
        document.getElementById("response");

    if (!customerName || !items) {

        responseContainer.innerHTML = `
            <p style="color:red">
                Customer Name and Items cannot be empty.
            </p>
        `;

        return;
    }

    try {

        const response = await fetch(
            `${API_URL_1}/orders`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    customerName,
                    items
                })
            }
        );

        if (!response.ok) {
            throw new Error("Request failed");
        }

        const data = await response.json();

        responseContainer.innerHTML = `
            <p style="color:green">
                Order created successfully!
            </p>
            <pre>${JSON.stringify(data, null, 2)}</pre>
        `;

        document.getElementById("customerName").value = "";
        document.getElementById("items").value = "";

        // Refresh right panel automatically
        loadOrders();

    } catch (error) {

        responseContainer.innerHTML = `
            <p style="color:red">
                Failed to create order.
            </p>
        `;
    }
}

async function loadOrders() {

    try {

        const response =
            await fetch(`${API_URL_2}/receivedOrders`);

        if (!response.ok) {
            throw new Error("Request failed");
        }

        const orders = await response.json();

        const container =
            document.getElementById("orders");

        if (!orders.length) {

            container.innerHTML =
                "No events found.";

            return;
        }

        container.innerHTML = "";

        orders.forEach(order => {

            container.innerHTML += `
                <div class="order-card">

                    <div class="order-title">
                        Order #${order.id}
                    </div>

                    <div>
                        <strong>Customer:</strong>
                        ${order.customerName}
                    </div>

                    <div>
                        <strong>Items:</strong>
                        ${order.items}
                    </div>

                </div>
            `;
        });

    } catch (error) {

        document.getElementById("orders").innerHTML = `
            <p style="color:red">
                Could not load events.
            </p>
        `;
    }
}

// Load existing events when page opens
loadOrders();