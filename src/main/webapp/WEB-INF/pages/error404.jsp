<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>404 - Page Not Found | PawCare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
</head>

<body>

<div class="error-container">

    <div class="error-card">

        <div class="error-code">404</div>

        <h1>Page Not Found</h1>

        <p>
            Oops! The page you are looking for doesn't exist or has been moved.
        </p>

        <div class="error-actions">
            <a href="${pageContext.request.contextPath}/admin/home" class="btn-primary">
                Go to Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/" class="btn-secondary">
                Back to Home
            </a>
        </div>

    </div>

</div>

</body>
</html>