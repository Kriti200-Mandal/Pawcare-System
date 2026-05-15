<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>About PawCare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css">
</head>
<body>

<nav class="navbar">
    <div class="nav-left">
        <span class="logo">🐾 PawCare</span>
    </div>

    <div class="nav-right">
        <a href="index.html">Home</a>
       <!-- - -- <a href="${pageContext.request.contextPath}/PetController" >Browse Pets</a>--->
        <a href="${pageContext.request.contextPath}/aboutController">About</a>
        <a href="${pageContext.request.contextPath}/contactController">Contact</a>
        <a href="${pageContext.request.contextPath}/LogoutController" class="login-btn">Logout</a>
    </div>
</nav>

<!-- HERO -->
<section class="hero">
    <h1>About PawCare</h1>
    <p>Dedicated to finding loving homes for rescued animals since 2020</p>
</section>

<!-- MISSION -->
<section class="mission">
    <div class="mission-text">
        <h2>Our Mission</h2>
        <p>
            PawCare is committed to rescuing, rehabilitating, and rehoming animals in need.
            We believe every animal deserves a second chance at a loving home.
        </p>
        
<p>
    Our mission extends beyond adoption. We focus on providing medical care,
    emotional support, and a safe environment for animals who have been abandoned,
    neglected, or mistreated.
</p>

<p>
    Through responsible adoption practices, community outreach, and partnerships
    with local organizations, PawCare works to ensure that every animal is placed
    into a caring and permanent home.
</p>
        
    </div>

    <div class="mission-image">
        <img src="${pageContext.request.contextPath}/images/rescued.png" alt="Rescued Animals">
    </div>
</section>

<!-- VALUES -->
<section class="values">
    <h2>Our Values</h2>

    <div class="values-grid">
        <div class="value-card">
            <div class="icon">❤️</div>
            <h3>Compassion</h3>
            <p>Every animal is treated with love and care.</p>
        </div>

        <div class="value-card">
            <div class="icon">👥</div>
            <h3>Community</h3>
            <p>Working with volunteers and organizations.</p>
        </div>

        <div class="value-card">
            <div class="icon">🏅</div>
            <h3>Excellence</h3>
            <p>High standards in animal care and adoption.</p>
        </div>
    </div>
</section>

<!-- STATS (DYNAMIC FROM DB) -->
<section class="stats">
    <div class="stat">
        <h1>${animalsRescued}+</h1>
        <p>Animals Rescued</p>
    </div>

    <div class="stat">
        <h1>${successfulAdoptions}+</h1>
        <p>Successful Adoptions</p>
    </div>

    <div class="stat">
        <h1>${volunteers}+</h1>
        <p>Active Volunteers</p>
    </div>

    <div class="stat">
        <h1>${partners}+</h1>
        <p>Partner Organizations</p>
    </div>
</section>

<!-- CTA -->
<section class="cta">
    <h2>Join Our Mission</h2>
    <p>Adopt, volunteer, or support us to make a difference.</p>

    <a href="${pageContext.request.contextPath}/PetController" class="btn-dark">
        Browse Animals
    </a>
    <a href="#" class="btn-light">
        Get in Touch
    </a>
</section>

</body>
</html>
