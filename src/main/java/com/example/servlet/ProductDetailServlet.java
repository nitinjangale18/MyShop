package com.example.servlet;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/view-products");
            return;
        }

        int productId = Integer.parseInt(idStr);
        Product product = productDAO.getProductById(productId);

        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/view-products");
            return;
        }

        request.setAttribute("product", product);
        RequestDispatcher rd = request.getRequestDispatcher("/customer/product-detail.jsp");
        rd.forward(request, response);
    }
}
