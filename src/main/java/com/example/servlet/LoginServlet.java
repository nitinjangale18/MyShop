package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import com.example.dao.UserDAO;
import com.example.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.login(username, password);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user); // ✅ FIXED

            // ✅ ROLE-BASED REDIRECTION
            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/view-products");
            } else {
                response.sendRedirect(request.getContextPath() + "/view-products");
            }

        } else {
            response.getWriter().println(
                "Login Failed! <a href='login.jsp'>Try Again</a>"
            );
        }
    }
}






