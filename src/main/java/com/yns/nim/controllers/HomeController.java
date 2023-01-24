package com.yns.nim.controllers;

import com.yns.nim.Utilities.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {


    @FXML
    private Button play_now, view_prfile, log_out;

    public void switchToGamePlay() throws IOException {
        Navigator.switchTo("fxmlFiles/game-play.fxml", play_now);
    }

    public void switchToRules() throws IOException {
        Navigator.switchTo("fxmlFiles/rules.fxml",play_now);
    }

    public void switchToViewProfile() throws IOException {
        Navigator.switchTo("fxmlFiles/profile.fxml", view_prfile);
    }

    public void switchToSignIn() throws IOException {
        Navigator.switchTo("fxmlFiles/sign-in.fxml", log_out);
    }

}
