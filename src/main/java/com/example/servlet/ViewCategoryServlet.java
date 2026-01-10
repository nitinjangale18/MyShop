package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import com.example.dao.CategoryDAO;
import com.example.model.Category;

@WebServlet("/admin/view-categories")
public class ViewCategoryServlet extends HttpServlet {

    private CategoryDAO dao = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        List<Category> categories = dao.getAllCategories();
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("/admin/view-categories.jsp")
           .forward(req, res);
    }
}
