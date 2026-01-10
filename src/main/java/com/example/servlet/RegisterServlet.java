package com.example.servlet;
import java.util.logging.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import com.example.dao.UserDAO;
import com.example.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println("Received in servlet -> username: " + username + ", password: " + password + ", role: " + role);

        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        boolean success = userDAO.registerUser(user);
        

        System.out.println("DAO returned: " + success);

        if(success) {
        	response.sendRedirect("login.jsp");
        } else {
            response.getWriter().println("Registration Failed! Try again. <a href='register.jsp'>Register</a>");
        }
    }
}
