package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.example.dao.CategoryDAO;
import com.example.model.Category;

@WebServlet("/admin/add-category")
public class AddCategoryServlet extends HttpServlet {

    private CategoryDAO dao = new CategoryDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            String description = req.getParameter("description");

            Category c = new Category(name, description);
            dao.addCategory(c);

            // redirect to category list
            res.sendRedirect(req.getContextPath() + "/admin/view-categories");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/admin/add-category.jsp?error=1");
        }
    }
}
