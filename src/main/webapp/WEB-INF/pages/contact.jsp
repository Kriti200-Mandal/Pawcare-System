<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact PawCare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
</head>
<body>

<nav class="navbar">
    <div class="nav-left">
        <span class="logo">🐾 PawCare</span>
    </div>

    <div class="nav-right">
          <a href="index.html">Home</a>
       
        <a href="${pageContext.request.contextPath}/aboutController">About</a>
        <a href="${pageContext.request.contextPath}/contactController">Contact</a>
        <a href="${pageContext.request.contextPath}/LogoutController" class="login-btn">Logout</a>
    </div>
</nav>

<section class="contact-hero">
    <h1>Get in Touch</h1>
    <p>Have questions? We'd love to hear from you.</p>
</section>

<section class="contact-container">

    <!-- LEFT SIDE -->
    <div class="contact-info">
        <h2>Contact Information</h2>

        <p><strong>Email</strong><br>info@pawcare.com</p>
        <p><strong>Phone</strong><br>+977 98XXXXXXX</p>
        <p><strong>Address</strong><br>Kathmandu, Nepal</p>

        <div class="visit-box">
            <h3>Visiting Hours</h3>
            <p>Mon – Fri: 10 AM – 6 PM</p>
            <p>Sat – Sun: 9 AM – 5 PM</p>
        </div>
    </div>

    <!-- RIGHT SIDE FORM -->
    <div class="contact-form">
        <h2>Send us a Message</h2>
             <form action="<%= request.getContextPath() %>/contactController" method="post">
       
            <input type="text" name="name" placeholder="Your name" required>
            <input type="email" name="email" placeholder="Your email" required>
            <input type="text" name="subject" placeholder="Subject" required>
            <textarea name="message" placeholder="Tell us more..." required></textarea>

            <button type="submit">Send Message</button>
        </form>
    </div>

</section>

</body>
</html>
