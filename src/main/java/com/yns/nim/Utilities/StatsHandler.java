package com.yns.nim.Utilities;

import com.yns.nim.controllers.SignInController;
import javafx.scene.control.Label;

import java.sql.*;

import static com.yns.nim.Utilities.DataBaseOperations.DbUtils.printSQLException;

public class StatsHandler {


    private static final String PlAYER_QUERY = "update users set score = ?, wins = ? where id = ?;";
    private static final String AI_QUERY = "update users set AI_score = ?, AI_wins = ? where id = ?;";
    private static final String ID_QUERY = "select * from users where email = ?;";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/nim_game?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "younesqara";

    public static void insertPlayerStats(int id, int score, int wins){

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(PlAYER_QUERY)
        ) {
            preparedStatement.setString(1, String.valueOf(score));
            preparedStatement.setString(2, String.valueOf(wins));
            preparedStatement.setString(3, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void insertAiStats(int id, int ai_score, int ai_wins) throws SQLException {

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(AI_QUERY)
        ) {
            preparedStatement.setString(1, String.valueOf(ai_score));
            preparedStatement.setString(2, String.valueOf(ai_wins));
            preparedStatement.setString(3, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static String[] getStats(String email) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY);
        preparedStatement.setString(1, email);

        ResultSet response = preparedStatement.executeQuery();

        response.next();
        String id = response.getString(1);
        String score = response.getString(5);
        String wins = response.getString(6);
        String ai_score = response.getString(7);
        String ai_wins = response.getString(8);
        String username = response.getString(2);
        return new String[]{id, score, wins, ai_score, ai_wins, username};
    }


    public static void showStats(Label sp,Label wp,Label sa,Label wa,Label up) throws SQLException {
        String[] stats = getStats(SignInController.getEmailText());
        int score = Integer.parseInt(stats[1]);
        int wins = Integer.parseInt(stats[2]);
        int ai_score = Integer.parseInt(stats[3]);
        int ai_wins = Integer.parseInt(stats[4]);
        String username = stats[5];
        sp.setText(String.valueOf(score));
        wp.setText(String.valueOf(wins));
        sa.setText(String.valueOf(ai_score));
        wa.setText(String.valueOf(ai_wins));
        up.setText(username);
    }

}
