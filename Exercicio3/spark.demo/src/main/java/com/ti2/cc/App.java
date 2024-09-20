package com.ti2.cc;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        port(4567);
        
        String url = "jdbc:postgresql://localhost:5432/user";
        String user = "lucasfernandesmarinho7@gmail.com";
        String password = "senhanova";

        Gson gson = new Gson();

        post("/submit", (request, response) -> {
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String age = request.queryParams("age");

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                UserDAO userDAO = new UserDAO(connection);
                userDAO.saveUser(name, email, Integer.parseInt(age));
            } catch (SQLException e) {
                e.printStackTrace();
                response.status(500);
                return gson.toJson("Erro ao salvar os dados!");
            }

            response.status(200);
            return gson.toJson("Dados recebidos com sucesso!");
        });

        get("/data", (request, response) -> {
            List<User> users = new ArrayList<>();
            
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                UserDAO userDAO = new UserDAO(connection);
                users = userDAO.getAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
                response.status(500);
                return gson.toJson("Erro ao buscar os dados!");
            }

            response.status(200);
            response.type("application/json");
            return gson.toJson(users);
        });
    }
}
