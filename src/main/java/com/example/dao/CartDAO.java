package com.example.dao;

import com.example.model.CartItem;
import com.example.model.Product;
import com.example.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    // Add product to cart
    public void addToCart(int userId, int productId) {

        String checkSql =
            "SELECT quantity FROM cart WHERE user_id=? AND product_id=?";

        String insertSql =
            "INSERT INTO cart (user_id, product_id, quantity) VALUES (?,?,1)";

        String updateSql =
            "UPDATE cart SET quantity = quantity + 1 WHERE user_id=? AND product_id=?";

        try (Connection conn = DBUtil.getConnection()) {

            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, userId);
            checkPs.setInt(2, productId);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                // product already in cart → increase qty
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, userId);
                updatePs.setInt(2, productId);
                updatePs.executeUpdate();
            } else {
                // new product
                PreparedStatement insertPs = conn.prepareStatement(insertSql);
                insertPs.setInt(1, userId);
                insertPs.setInt(2, productId);
                insertPs.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View cart items for user
    public List<CartItem> getCartItems(int userId) {

        List<CartItem> list = new ArrayList<>();

        String sql =
        	    "SELECT c.id AS cart_id, c.quantity, p.* " +
        	    "FROM cart c JOIN products p ON c.product_id = p.id " +
        	    "WHERE c.user_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setImagePath(rs.getString("image_path"));

                CartItem item = new CartItem();
                item.setId(rs.getInt("cart_id")); // ✅ use alias
                item.setProduct(p);
                item.setQuantity(rs.getInt("quantity"));
                item.setUserId(userId);

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
 // Increase quantity
    public void increaseQuantity(int cartId) {
        String sql = "UPDATE cart SET quantity = quantity + 1 WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Decrease quantity
    public void decreaseQuantity(int cartId) {
        String sql = "UPDATE cart SET quantity = quantity - 1 WHERE id=? AND quantity > 1";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Remove item
    public void removeItem(int cartId) {
        String sql = "DELETE FROM cart WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
