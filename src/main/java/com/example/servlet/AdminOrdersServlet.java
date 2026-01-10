package com.example.servlet;

import com.example.dao.OrderDAO;
import com.example.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/view-orders")
public class AdminOrdersServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> orders = orderDAO.getAllOrders();
        request.setAttribute("orders", orders);

        RequestDispatcher rd = request.getRequestDispatcher("/admin/view-orders.jsp");
        rd.forward(request, response);
    }
}
