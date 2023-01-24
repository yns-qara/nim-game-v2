package com.yns.nim.Utilities.DataBaseOperations;

import java.sql.*;

public class DbUtils {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/nim_game?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "younesqara";
    private static final String INSERT_QUERY = "INSERT INTO users (userName, email, password) VALUES (?, ?, ?)";
    private static final String VALIDATE_QUERY = "SELECT * FROM users WHERE email = ? and password = ?";

    public void insertRecord(String username, String email, String password) throws SQLException {

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    public boolean validate(String email, String password) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_QUERY);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet response = preparedStatement.executeQuery();
            return response.next();


        } catch (SQLException e) {
            printSQLException(e);
        }

        return false;
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
