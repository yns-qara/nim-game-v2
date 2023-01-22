package com.yns.nim.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RulesController {

    @FXML
    private Button goHome;

    public void switchH() throws IOException {
        Navigator.switchTo("fxmlFiles/home.fxml",goHome);
    }
}
