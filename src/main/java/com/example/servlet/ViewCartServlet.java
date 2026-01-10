package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import com.example.dao.CartDAO;
import com.example.model.CartItem;
import com.example.model.User;

@WebServlet("/view-cart")
public class ViewCartServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<CartItem> cartItems = cartDAO.getCartItems(user.getId());
        request.setAttribute("cartItems", cartItems);

        RequestDispatcher rd =
            request.getRequestDispatcher("/customer/cart.jsp");
        rd.forward(request, response);
    }
}
