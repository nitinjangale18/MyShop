package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.example.dao.CategoryDAO;
import com.example.model.Category;

@WebServlet("/admin/update-category")
public class UpdateCategoryServlet extends HttpServlet {

    private CategoryDAO dao = new CategoryDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");

            Category c = new Category(id, name, description);
            dao.updateCategory(c);

            res.sendRedirect(req.getContextPath() + "/admin/view-categories");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/admin/edit-category.jsp?error=1");
        }
    }
}
