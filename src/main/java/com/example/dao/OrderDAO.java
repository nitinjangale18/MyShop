package com.example.dao;

import com.example.model.CartItem;
import com.example.model.Order;
import com.example.model.User;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // Save order and order items
    public int createOrder(int userId, double totalAmount, List<CartItem> cartItems) {
        int orderId = 0;

        String insertOrder = "INSERT INTO orders (user_id, total_amount, payment_status) VALUES (?, ?, ?)";
        String insertItem = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        String clearCart = "DELETE FROM cart WHERE user_id=?";

        try (Connection conn = DBUtil.getConnection()) {

            // Insert order
            PreparedStatement psOrder = conn.prepareStatement(insertOrder, PreparedStatement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, userId);
            psOrder.setDouble(2, totalAmount);
            psOrder.setString(3, "PAID"); // Simulated payment
            psOrder.executeUpdate();

            // Get generated order ID
            ResultSet rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert order items
            PreparedStatement psItem = conn.prepareStatement(insertItem);
            for (CartItem item : cartItems) {
                psItem.setInt(1, orderId);
                psItem.setInt(2, item.getProduct().getId());
                psItem.setInt(3, item.getQuantity());
                psItem.setDouble(4, item.getProduct().getPrice());
                psItem.addBatch();
            }
            psItem.executeBatch();

            // Clear cart
            PreparedStatement psClear = conn.prepareStatement(clearCart);
            psClear.setInt(1, userId);
            psClear.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }
    
    
 // ------------------------------
 // Fetch all orders for admin
 // ------------------------------
 

 // Fetch all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders ORDER BY created_at DESC"; // ✅ fixed

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setOrderDate(rs.getTimestamp("created_at")); // ✅ fixed
                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

}
