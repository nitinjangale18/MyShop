package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import com.example.dao.CartDAO;
import com.example.dao.OrderDAO;
import com.example.model.CartItem;
import com.example.model.User;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();
    private OrderDAO orderDAO = new OrderDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Read payment details (simulated)
        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String expiry = request.getParameter("expiry");
        String cvv = request.getParameter("cvv");

        if (cardName.isEmpty() || cardNumber.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
            response.getWriter().println("Payment Failed! All fields are required.");
            return;
        }

        // Fetch cart items
        List<CartItem> cartItems = cartDAO.getCartItems(user.getId());
        if (cartItems == null || cartItems.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/view-products");
            return;
        }

        // Calculate total
        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        // Simulate payment success
        int orderId = orderDAO.createOrder(user.getId(), totalAmount, cartItems);

        // Forward to success page
        request.setAttribute("orderId", orderId);
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalAmount", totalAmount);

        RequestDispatcher rd = request.getRequestDispatcher("/customer/order-success.jsp");
        rd.forward(request, response);
    }
}
