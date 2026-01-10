package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/delete-user")
public class DeleteUserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User currentUser = (session != null)
                ? (User) session.getAttribute("currentUser")
                : null;

        // ADMIN check
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);

        // Redirect back to customers list
        res.sendRedirect(req.getContextPath() + "/admin/view-customers");
    }
}
