package com.example.dao;

import com.example.models.Purchase;
import com.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    private static final String FIND_ALL_PURCHASES = "SELECT * FROM purchases";
    private static final String INSERT_PURCHASE = "INSERT INTO purchases (name, tours_id, users_id) VALUES(?,?,?)";

    private String URL = "jdbc:mysql://localhost:3306/mydb";
    private String USER = "root";
    private String PASSWORD = "root";


    public List<Purchase> findAllPurchases() {
        List<Purchase> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PURCHASES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(mapPurchase(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void insertPurchase(Purchase purchase) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_PURCHASE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, purchase.getName());
            statement.setInt(2, purchase.getTourId());
            statement.setInt(3, purchase.getUserId());
            int n = statement.executeUpdate();
            if (n == 0)
                throw new SQLException("0 rows affected");
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    purchase.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Purchase mapPurchase(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int tourId = resultSet.getInt("tours_id");
        int userId = resultSet.getInt("users_id");
        return new Purchase(id, name, tourId, userId);
    }
}
