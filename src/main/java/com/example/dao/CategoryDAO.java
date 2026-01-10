package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Category;
import com.example.util.DBUtil;

public class CategoryDAO {

    private static final String INSERT_CATEGORY =
            "INSERT INTO categories (name, description) VALUES (?, ?)";

    private static final String SELECT_ALL =
            "SELECT * FROM categories";

    private static final String SELECT_BY_ID =
            "SELECT * FROM categories WHERE id = ?";

    private static final String UPDATE_CATEGORY =
            "UPDATE categories SET name = ?, description = ? WHERE id = ?";

    private static final String DELETE_CATEGORY =
            "DELETE FROM categories WHERE id = ?";

    // ✅ Add Category (ADMIN)
    public boolean addCategory(Category c) {

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_CATEGORY)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Get All Categories
    public List<Category> getAllCategories() {

        List<Category> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Get Category by ID
    public Category getCategoryById(int id) {

        Category c = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // ✅ Update Category
    public boolean updateCategory(Category c) {

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CATEGORY)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Delete Category
 // ✅ Delete Category (safe check)
    public boolean deleteCategory(int id) {

        try (Connection conn = DBUtil.getConnection()) {

            // 1️⃣ Check if any products belong to this category
            String checkSql = "SELECT COUNT(*) FROM products WHERE category_id = ?";
            try (PreparedStatement psCheck = conn.prepareStatement(checkSql)) {
                psCheck.setInt(1, id);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    // Cannot delete, category has products
                    return false;
                }
            }

            // 2️⃣ Safe to delete
            try (PreparedStatement psDelete = conn.prepareStatement(DELETE_CATEGORY)) {
                psDelete.setInt(1, id);
                return psDelete.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
