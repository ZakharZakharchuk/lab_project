package com.example.dao;

import com.example.models.Tour;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TourDAO {
    private static final String FIND_ALL_TOURS = "SELECT * FROM tours";
    private static final String UPDATE_TOUR = "UPDATE tours SET name=?, price_per_person=? WHERE id=?";
    private static final String DELETE_TOUR = "DELETE FROM tours WHERE id=?";
    private static final String INSERT_TOUR = "INSERT INTO tours (name, price_per_person) VALUES(?,?)";

    private String URL = "jdbc:mysql://localhost:3306/mydb";
    private String USER = "root";
    private String PASSWORD = "root";

    public List<Tour> findAllTours() {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TOURS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tours.add(mapTour(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    public void insertTour(Tour tour) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = con.prepareStatement(INSERT_TOUR, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, tour.getName());
            statement.setInt(2, tour.getPricePerPerson());
            int n = statement.executeUpdate();
            if (n == 0)
                throw new SQLException("0 rows affected");
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    tour.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateTour(Tour updateTour) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR)) {
            statement.setString(1, updateTour.getName());
            statement.setInt(2, updateTour.getPricePerPerson());
            statement.setInt(3, updateTour.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTour(Tour tour) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE_TOUR)) {
            statement.setInt(1, tour.getId());
            int n = statement.executeUpdate();
            if (n == 0)
                throw new SQLException("No tours to delete");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Tour mapTour(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setId(resultSet.getInt("id"));
        tour.setName(resultSet.getString("name"));
        tour.setPricePerPerson(resultSet.getInt("price_per_person"));
        return tour;
    }
}
