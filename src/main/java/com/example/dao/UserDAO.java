package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.User;
import com.example.util.DBUtil;

public class UserDAO {

    private static final String INSERT_USER =
        "INSERT INTO users(username,password,role) VALUES (?,?,?)";

    private static final String LOGIN_QUERY =
        "SELECT * FROM users WHERE username=? AND password=?";
    
 // 🔍 Search + Pagination
    private static final String SELECT_USERS_PAGINATED =
        "SELECT * FROM users WHERE role='USER' AND username LIKE ? LIMIT ? OFFSET ?";

    private static final String COUNT_USERS =
        "SELECT COUNT(*) FROM users WHERE role='USER' AND username LIKE ?";


    // Register user
    public boolean registerUser(User user) {

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login user
    public User login(String username, String password) {

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(LOGIN_QUERY)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
 // Get all customers (ADMIN)
    public List<User> getAllCustomers() {

        List<User> list = new ArrayList<>();

        String sql = "SELECT id, username, role FROM users WHERE role='USER'";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));

                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
 // ✅ Get users with search + pagination
    public List<User> getUsers(String search, int limit, int offset) {

        List<User> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_USERS_PAGINATED)) {

            ps.setString(1, "%" + search + "%");
            ps.setInt(2, limit);
            ps.setInt(3, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));

                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 // ✅ Get total user count (for pagination)
    public int getUserCount(String search) {

        int count = 0;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(COUNT_USERS)) {

            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
 // Delete user by ID (ADMIN)
    public boolean deleteUser(int id) {

        String sql = "DELETE FROM users WHERE id=? AND role='USER'";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
