package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.example.dao.ProductDAO;
import com.example.model.Product;
import java.sql.Connection;
import com.example.util.DBUtil;

@WebServlet("/admin/update-product")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            String imagePath = request.getParameter("imagePath"); // existing image path or new path after upload
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));


            Product p = new Product();
            p.setId(id);
            p.setName(name);
            p.setDescription(description);
            p.setPrice(price);
            p.setImagePath(imagePath);
            p.setCategoryId(categoryId);


            Connection conn = DBUtil.getConnection();
            ProductDAO dao = new ProductDAO();

            boolean updated = dao.updateProduct(p);
            conn.close();

            if(updated) {
                response.sendRedirect(request.getContextPath() + "/view-products");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/edit-product.jsp?id=" + id + "&msg=error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/all-products.jsp?msg=error");
        }
    }
}
