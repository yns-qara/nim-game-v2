package com.yns.nim.controllers;

import com.yns.nim.Utilities.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RulesController {

    @FXML
    private Button goHome;

    public void switchHome() throws IOException {
        Navigator.switchTo("fxmlFiles/home.fxml",goHome);
    }
}
