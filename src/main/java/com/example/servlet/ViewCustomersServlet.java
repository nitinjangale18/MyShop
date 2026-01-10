package com.example.servlet;

import java.io.IOException;
import java.util.List;

import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/view-customers")
public class ViewCustomersServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 🔍 Search text
        String search = req.getParameter("search");
        if (search == null) {
            search = "";
        }

        // 📄 Page number
        int page = 1;
        int limit = 5; // users per page

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        int offset = (page - 1) * limit;

        // 📦 Fetch data
        List<User> users = userDAO.getUsers(search, limit, offset);
        int totalUsers = userDAO.getUserCount(search);

        int totalPages = (int) Math.ceil((double) totalUsers / limit);

        // 📤 Send to JSP
        req.setAttribute("users", users);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("search", search);

        req.getRequestDispatcher("/admin/view-customers.jsp")
           .forward(req, res);
    }
}
