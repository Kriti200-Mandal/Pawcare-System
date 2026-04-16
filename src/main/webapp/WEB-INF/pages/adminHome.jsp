<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PawCare Admin Dashboard</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/home.css">

<!-- ICONS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<!-- SIDEBAR -->
<div class="sidebar">
    <h2>🐾 PawCare</h2>
    <ul>
        <li class="active"><i class="fa fa-home"></i> Dashboard</li>
        <li><i class="fa fa-dog"></i> Pets</li>
        <li><i class="fa fa-heart"></i> Adoptions</li>
        <li><i class="fa fa-users"></i> Users</li>
        <li><i class="fa fa-cog"></i> Settings</li>
    </ul>
</div>

<!-- MAIN -->
<div class="main">

    <!-- TOPBAR -->
    <div class="topbar">
        <h2>Dashboard</h2>
        <p>Welcome, Admin 👋</p>
    </div>

    <!-- CARDS -->
    <div class="cards">
        <div class="card blue">
            <i class="fa fa-users"></i>
            <h4>Total Users</h4>
            <p>120</p>
        </div>

        <div class="card green">
            <i class="fa fa-dog"></i>
            <h4>Total Pets</h4>
            <p>45</p>
        </div>

        <div class="card orange">
            <i class="fa fa-heart"></i>
            <h4>Adoptions</h4>
            <p>30</p>
        </div>

        <div class="card red">
            <i class="fa fa-clock"></i>
            <h4>Pending</h4>
            <p>5</p>
        </div>
    </div>

    <!-- TABLE -->
    <div class="table-section">
        <h3>Recent Adoption Requests</h3>

        <table>
            <thead>
                <tr>
                    <th>User</th>
                    <th>Pet</th>
                    <th>Date</th>
                    <th>Status</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td>Kriti</td>
                    <td>Loki</td>
                    <td>2026-04-10</td>
                    <td><span class="badge pending">Pending</span></td>
                </tr>

                <tr>
                    <td>Rahul</td>
                    <td>Luna</td>
                    <td>2026-04-09</td>
                    <td><span class="badge approved">Approved</span></td>
                </tr>

                <tr>
                    <td>Aman</td>
                    <td>Bruno</td>
                    <td>2026-04-08</td>
                    <td><span class="badge rejected">Rejected</span></td>
                </tr>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>