package com.example.dao;

import com.example.model.Product;
import com.example.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	private static final String INSERT_PRODUCT =
		    "INSERT INTO products (name, description, price, quantity, image_path, category_id) VALUES (?,?,?,?,?,?)";

		private static final String UPDATE_PRODUCT =
		    "UPDATE products SET name=?, description=?, price=?, quantity=?, image_path=?, category_id=? WHERE id=?";

    
    private static final String SELECT_ALL =
            "SELECT * FROM products";

    // Delete product
    private static final String DELETE_PRODUCT =
        "DELETE FROM products WHERE id=?";

    // Get product by ID (for edit)
    private static final String SELECT_BY_ID =
        "SELECT * FROM products WHERE id=?";
    
    
 // 🔍 Search products by name
    private static final String SEARCH_PRODUCTS =
        "SELECT * FROM products WHERE name LIKE ?";

    
    // Add product (ADMIN)
    public boolean addProduct(Product p) {

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PRODUCT)) {

        	ps.setString(1, p.getName());
        	ps.setString(2, p.getDescription());
        	ps.setDouble(3, p.getPrice());
        	ps.setInt(4, p.getQuantity());
        	ps.setString(5, p.getImagePath());
        	ps.setInt(6, p.getCategoryId()); // NEW

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // View all products (ADMIN + USER)
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImagePath(rs.getString("image_path")); // ✅ FIX
                p.setCategoryId(rs.getInt("category_id"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ------------------- NEW METHODS -------------------

    // Update product
    public boolean updateProduct(Product p) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PRODUCT)) {

        	ps.setString(1, p.getName());
        	ps.setString(2, p.getDescription());
        	ps.setDouble(3, p.getPrice());
        	ps.setInt(4, p.getQuantity());
        	ps.setString(5, p.getImagePath());
        	ps.setInt(6, p.getCategoryId()); // NEW
        	ps.setInt(7, p.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete product
    public boolean deleteProduct(int id) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_PRODUCT)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get product by ID
    public Product getProductById(int id) {
        Product p = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setPrice(rs.getDouble("price"));
                    p.setQuantity(rs.getInt("quantity"));
                    p.setImagePath(rs.getString("image_path"));
                    p.setCategoryId(rs.getInt("category_id"));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    
    
 // 🔍 Search products
    public List<Product> searchProducts(String keyword) {

        List<Product> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SEARCH_PRODUCTS)) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImagePath(rs.getString("image_path"));
                p.setCategoryId(rs.getInt("category_id"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    
    

}
