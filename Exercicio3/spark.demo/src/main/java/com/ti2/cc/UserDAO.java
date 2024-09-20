package com.ti2.cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveUser(String name, String email, int age) throws SQLException {
        String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
                users.add(user);
            }
        }
        return users;
    }
}
