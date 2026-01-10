package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import com.example.model.User;

/**
 * AuthFilter - Secures pages based on login status and role
 * 1. Prevent unauthorized access to admin/user pages
 * 2. Prevent logged-in users from visiting login/register pages
 * 3. Allow public resources (CSS, JS, images) without login
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI().substring(req.getContextPath().length());
        HttpSession session = req.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        // --------------------------
        // 1️⃣ Allow public resources (CSS, JS, images)
        // --------------------------
        if (uri.startsWith("/common/") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") 
                || uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".gif")) {
            chain.doFilter(request, response);
            return;
        }

        // --------------------------
        // 2️⃣ Login and Register pages
        // --------------------------
        if (uri.equals("/login.jsp") || uri.equals("/register.jsp")) {
            if (currentUser != null) {
                // Redirect logged-in users away from login/register pages
                if ("ADMIN".equals(currentUser.getRole())) {
                    res.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
                } else {
                    res.sendRedirect(req.getContextPath() + "/customer/home.jsp");
                }
                return;
            }
            chain.doFilter(request, response); // allow access
            return;
        }

        // --------------------------
        // 3️⃣ Login and Logout Servlets
        // --------------------------
        if (uri.equals("/login") || uri.equals("/logout")) {
            chain.doFilter(request, response); // allow servlet to handle
            return;
        }

        // --------------------------
        // 4️⃣ Protected pages: must be logged in
        // --------------------------
        if (currentUser == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // --------------------------
        // 5️⃣ Role-based page protection
        // --------------------------
        if (uri.startsWith("/admin/") && !"ADMIN".equals(currentUser.getRole())) {
            res.sendRedirect(req.getContextPath() + "/customer/home.jsp");
            return;
        }

        if (uri.startsWith("/customer/") && !"USER".equals(currentUser.getRole())) {
            res.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
            return;
        }

        // --------------------------
        // 6️⃣ Allow everything else
        // --------------------------
        chain.doFilter(request, response);
    }
}
