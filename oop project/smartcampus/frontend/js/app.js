// Smart Campus Student Management System

console.log("Smart Campus System Started");


// Show a particular page
function showPage(pageName) {

    // Get all pages
    const pages = document.querySelectorAll(".page");

    // Hide all pages
    pages.forEach(function(page) {
        page.classList.add("hidden");
    });

    // Show selected page
    const selectedPage = document.getElementById(pageName);

    if (selectedPage) {
        selectedPage.classList.remove("hidden");
    }

    // Change page title
    const title = document.getElementById("pageTitle");

    if (title) {
        title.textContent =
            pageName.charAt(0).toUpperCase() + pageName.slice(1);
    }
}


// Login
document.getElementById("loginForm").addEventListener("submit", function(event) {

    event.preventDefault();

    const username =
        document.getElementById("username").value;

    const password =
        document.getElementById("password").value;


    // Temporary login
    // We will connect this to Java later.

    if (username === "admin" && password === "admin123") {

        document.getElementById("loginPage")
            .classList.add("hidden");

        document.getElementById("system")
            .classList.remove("hidden");

        document.getElementById("loginMessage")
            .textContent = "";

    } else {

        document.getElementById("loginMessage")
            .textContent =
            "Invalid username or password";

    }

});


// Logout
function logout() {

    document.getElementById("system")
        .classList.add("hidden");

    document.getElementById("loginPage")
        .classList.remove("hidden");

    document.getElementById("username").value = "";

    document.getElementById("password").value = "";
}