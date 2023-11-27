document.addEventListener("DOMContentLoaded", function () {
    const registrationButton = document.getElementById("registrationButton");

    registrationButton.addEventListener("click", async function () {
        const username = document.getElementById("username").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        const requestBody = {
            userName: username,
            email: email,
            password: password
        };

        console.log(requestBody);
        try {
            const response = await fetch('http://localhost:8080/api/v1/registration', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            });
            console.log(response);
            if (response.ok) {
                alert("Success");
            } else {
                console.error('Registration failed.');
            }
        } catch (error) {
            console.error('Network error:', error);
        }
    });
});


// const username = document.getElementById('username');
// const email = document.getElementById('email');
// const password = document.getElementById('password');
//
// const users = async () => {
//     const getUsers = await fetch('http://localhost:8080/api/v1/registration');
//     const users = await getUsers.json();
//
//     users.forEach((element) => {
//         const createUser = document.createElement('div');
//         createUser.setAttribute('class', 'user-item');
//         createUser.setAttribute('onclick', `getUserData(${element.id})`);
//         createUser.append(element.name)
//         container.append(createUser)
//     });
// };
//
// users();
//
// const getUserData = async (id) => {
//     postsContainer.style.display  = 'none';
//     postsContainer.textContent = '';
//     const getUser = await fetch(`https://jsonplaceholder.typicode.com/users/${id}`);
//     const user = await getUser.json();
//
//     userInfo.style.display = 'flex';
//     showPostsBtn.style.display = 'flex';
//     userInfoName.textContent = user.name;
//     userInfoUsername.textContent = user.username;
//     userInfoAddress.textContent = `${user.address.city}, ${user.address.street}`;
//     userInfoEmail.textContent = user.email;
//     userInfoPhone.textContent = user.phone;
//     userInfoSite.textContent = user.website;
//     showPostsBtn.setAttribute('onclick', `getPosts(${user.id})`);
// };
//
// const getPosts = async (user) => {
//     postsContainer.style.display = 'flex'
//     const findPosts = await fetch(`https://jsonplaceholder.typicode.com/posts?userId=${user}`);
//     const posts = await findPosts.json();
//
//     posts.forEach(element => {
//         const createPost = document.createElement('div');
//         const h5 = document.createElement('h5');
//         const p = document.createElement('p');
//         createPost.setAttribute('class', 'post');
//
//         h5.textContent = element.title;
//         p.textContent = element.body;
//         createPost.append(h5);
//         createPost.append(p);
//
//         postsContainer.append(createPost);
//     });
// }