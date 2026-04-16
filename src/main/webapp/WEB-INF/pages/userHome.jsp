<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PawCare | User Dashboard</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css">
</head>
<body>

<!-- NAVBAR -->
<div class="navbar">
    <h2>🐾 PawCare</h2>
    <ul>
        <li>Dashboard</li>
        <li>Browse Pets</li>
        <li>My Adoptions</li>
        <li>Logout</li>
    </ul>
</div>

<!-- MAIN CONTENT -->
<div class="main">

    <!-- WELCOME -->
    <div class="welcome">
        <h2>Welcome, User 👋</h2>
        <p>Find your perfect furry companion today!</p>
    </div>

    <!-- QUICK STATS -->
    <div class="cards">
        <div class="card">
            <h4>Available Pets</h4>
            <p>18</p>
        </div>
        <div class="card">
            <h4>My Requests</h4>
            <p>2</p>
        </div>
        <div class="card">
            <h4>Adopted Pets</h4>
            <p>1</p>
        </div>
    </div>

    <!-- PET LIST -->
    <div class="pet-section">
        <h3>Available Pets</h3>

        <div class="pet-grid">
            <div class="pet-card">
                images/dog.jpg
                <h4>Loki</h4>
                <button>Adopt</button>
            </div>

            <div class="pet-card">
                images/cat.jpg
                <h4>Luna</h4>
                <button>Adopt</button>
            </div>

            <div class="pet-card">
                images/rabbit.jpg
                <h4>Milo</h4>
                <button>Adopt</button>
            </div>
        </div>
    </div>

</div>

</body>
</html>
