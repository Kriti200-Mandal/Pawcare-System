package com.pawcare.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/admin/*")  //  Protect all admin URLs
public class adminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        // STEP 1: NOT LOGGED IN → SAVE URL + REDIRECT TO LOGIN
        if (session == null || session.getAttribute("userType") == null) {

            //  save requested URL
            req.getSession(true).setAttribute("redirectAfterLogin", req.getRequestURI());

            res.sendRedirect(req.getContextPath() + "/LoginController");
            return;
        }

        //  STEP 2: LOGGED IN BUT NOT ADMIN
        if (!"admin".equals(session.getAttribute("userType"))) {

            req.getSession().setAttribute(
                "flashMessage",
                "Access denied ❌ Only authorized admin users can access this page."
            );
            req.getSession().setAttribute("flashType", "error");

            res.sendRedirect(req.getContextPath() + "/user/home");
            return;
        }

        //  STEP 3: ADMIN → ALLOW ACCESS
        chain.doFilter(request, response);
    }
}