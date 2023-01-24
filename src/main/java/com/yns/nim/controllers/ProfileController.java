package com.yns.nim.controllers;

import com.yns.nim.Utilities.Navigator;
import com.yns.nim.Utilities.StatsHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Label userName, email, score, wins;
    @FXML
    private Button goHome;
    public void switchToHome() throws IOException {
        Navigator.switchTo("fxmlFiles/home.fxml",goHome);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String em = SignInController.getEmailText();
            String[] stats = StatsHandler.getStats(em);
            String username = stats[5];
            String sc = stats[1];
            String wns = stats[2];
            email.setText(em);
            score.setText(sc);
            wins.setText(wns);
            userName.setText(username);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
