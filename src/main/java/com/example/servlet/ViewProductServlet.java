package com.example.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.dao.ProductDAO;
import com.example.model.Product;
import com.example.model.User;

@WebServlet("/view-products")
public class ViewProductServlet extends HttpServlet {

    private ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String search = req.getParameter("search");

        List<Product> products;

        if (search != null && !search.trim().isEmpty()) {
            products = dao.searchProducts(search);
        } else {
            products = dao.getAllProducts();
        }

        req.setAttribute("products", products);
        req.setAttribute("search", search);

        HttpSession session = req.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("currentUser");
        }

        if (user != null && "ADMIN".equals(user.getRole())) {
            req.getRequestDispatcher("/admin/view-products.jsp")
               .forward(req, res);
        } else {
            req.getRequestDispatcher("/customer/view-products.jsp")
               .forward(req, res);
        }
    }

}
