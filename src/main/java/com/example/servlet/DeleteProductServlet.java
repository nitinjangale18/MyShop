package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.example.dao.ProductDAO;

@WebServlet("/admin/delete-product")
public class DeleteProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ---------- DEBUG ----------
        System.out.println("DeleteProductServlet called");

        try {
            // 1️⃣ Get the product ID from query parameter
            String idStr = request.getParameter("id");
            System.out.println("Product ID received: " + idStr);

            int id = Integer.parseInt(idStr);

            // 2️⃣ Delete product using DAO
            ProductDAO dao = new ProductDAO();
            boolean deleted = dao.deleteProduct(id);
            System.out.println("Deleted status: " + deleted);

            // 3️⃣ Redirect back to your product view page with message
            if(deleted) {
                response.sendRedirect(request.getContextPath() + "/view-products");
            } else {
                response.sendRedirect(request.getContextPath() + "/view-products");
            }

        } catch (NumberFormatException nfe) {
            // Handle invalid or missing ID
            System.out.println("Invalid product ID");
            nfe.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/view-product");

        } catch (Exception e) {
            // Catch all other exceptions
            System.out.println("Exception in DeleteProductServlet");
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/view-product");
        }
    }
}
