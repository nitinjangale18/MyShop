package com.example.servlet;

import java.io.IOException;

import com.example.dao.CartDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart-action")
public class CartActionServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String action = req.getParameter("action");
        int cartId = Integer.parseInt(req.getParameter("id"));

        if ("inc".equals(action)) {
            cartDAO.increaseQuantity(cartId);
        } else if ("dec".equals(action)) {
            cartDAO.decreaseQuantity(cartId);
        } else if ("remove".equals(action)) {
            cartDAO.removeItem(cartId);
        }

        res.sendRedirect(req.getContextPath() + "/view-cart");
    }
}

