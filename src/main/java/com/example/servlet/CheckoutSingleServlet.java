package com.example.servlet;

import com.example.dao.ProductDAO;
import com.example.model.CartItem;
import com.example.model.Product;
import com.example.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkout-single")
public class CheckoutSingleServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Fetch product from DB
        Product product = productDAO.getProductById(productId);
        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/view-products");
            return;
        }

        // Prepare a CartItem for checkout
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setUserId(user.getId());

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(item);

        double totalAmount = product.getPrice() * quantity;

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalAmount", totalAmount);

        // Forward to the existing checkout.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/customer/checkout.jsp");
        rd.forward(request, response);
    }
}
