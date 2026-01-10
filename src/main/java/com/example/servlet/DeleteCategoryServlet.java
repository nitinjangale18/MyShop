package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.example.dao.CategoryDAO;

@WebServlet("/admin/delete-category")
public class DeleteCategoryServlet extends HttpServlet {

    private CategoryDAO dao = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            
            // ❌ Keep your DAO method as is
            boolean deleted = dao.deleteCategory(id);

            // ✅ Set message in session based on deletion result
            if (!deleted) {
                req.getSession().setAttribute("msg", "Cannot delete category, it has products!");
            } else {
                req.getSession().setAttribute("msg", "Category deleted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("msg", "Error occurred while deleting category!");
        }

        res.sendRedirect(req.getContextPath() + "/admin/view-categories");
    }
}
