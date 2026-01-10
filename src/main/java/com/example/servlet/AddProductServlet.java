package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.io.File;

import com.example.dao.ProductDAO;
import com.example.model.Product;

@WebServlet("/admin/add-product")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,      
    maxFileSize = 1024 * 1024 * 5,        
    maxRequestSize = 1024 * 1024 * 10     
)
public class AddProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            // 1️⃣ Read fields
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            // 2️⃣ Image upload (INSIDE WEBAPP)
            Part imagePart = req.getPart("image");
            String imagePath = null;
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));


            if (imagePart != null && imagePart.getSize() > 0) {

                String fileName = System.currentTimeMillis() + "_"
                        + imagePart.getSubmittedFileName();

                // 🔥 REAL webapp path
                String uploadPath = getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                imagePart.write(uploadPath + File.separator + fileName);

                // path saved in DB
                imagePath = "uploads/" + fileName;
            }

            // 3️⃣ Product object
            Product p = new Product();
            p.setName(name);
            p.setDescription(description);
            p.setPrice(price);
            p.setQuantity(quantity);
            p.setImagePath(imagePath);
            p.setCategoryId(categoryId);


            // 4️⃣ Save to DB
            productDAO.addProduct(p);

            // 5️⃣ Redirect
            res.sendRedirect(req.getContextPath() + "/view-products");

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error adding product: " + e.getMessage());
        }
    }
}
