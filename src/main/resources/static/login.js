document.addEventListener("DOMContentLoaded", function () {
    const loginButton = document.getElementById("loginButton");

    loginButton.addEventListener("click", async function () {
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        const requestBody = {
            userName: username,
            password: password
        };

        console.log(requestBody);

        try {
            const response = await fetch(`http://localhost:8080/api/v1/login?userName=${username}&password=${password}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
              //  body: JSON.stringify(requestBody)
            });

            console.log(response);
            if (response.ok) {
              alert("Success");
            } else {
                console.error('Login failed.');
            }

        } catch (error) {
            console.error('Network error:', error);
        }
    });
});
