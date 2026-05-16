package com.pawcare.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import com.pawcare.config.DbConfig;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //  SHOW LOGIN PAGE
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/login.jsp")
               .forward(request, response);
    }

    //  HANDLE LOGIN
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        //  1. ADMIN LOGIN (STATIC CHECK)
        if ("admin@gmail.com".equals(email) && "admin123".equals(password)) {

            session.setAttribute("userType", "admin");
            session.setAttribute("userEmail", email);

            handleRedirectAfterLogin(session, request, response);
            return;
        }

        // 2. USER LOGIN FROM DATABASE
        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection con = new DbConfig().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                //  store session
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("userName", rs.getString("name"));
                session.setAttribute("userType", "user");
                session.setAttribute("userEmail", email);

                handleRedirectAfterLogin(session, request, response);

            } else {
                // ❌ login failed
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  REUSABLE METHOD (VERY CLEAN)
    private void handleRedirectAfterLogin(HttpSession session,
                                          HttpServletRequest request,
                                          HttpServletResponse response)
            throws IOException {

        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        String userType = (String) session.getAttribute("userType");

        //  If user tried to access something before login
        if (redirectUrl != null) {

            session.removeAttribute("redirectAfterLogin");

            //  USER trying admin page
            if (redirectUrl.contains("/admin") && !"admin".equals(userType)) {

                session.setAttribute("flashMessage",
                    "Access denied ❌ Only authorized admin users can access that page.");
                session.setAttribute("flashType", "error");

                response.sendRedirect(request.getContextPath() + "/user/home");
                return;
            }

            //  allow redirect
            response.sendRedirect(request.getContextPath() + redirectUrl);
            return;
        }

        //  DEFAULT REDIRECT
        if ("admin".equals(userType)) {
            response.sendRedirect(request.getContextPath() + "/admin/pets");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/home");
        }
    }
}