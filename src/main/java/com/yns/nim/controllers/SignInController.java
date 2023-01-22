package com.yns.nim.controllers;


import com.yns.nim.DbUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;

public class SignInController{

    @FXML
    private Button signUp , login;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label fillErr , incorrectCredentials;

    private static String emailText ,passwordText;

    public void switchToSignUP() throws IOException {
        Navigator.switchTo("fxmlFiles/registration.fxml",signUp);
    }

    public void signIn() throws IOException, SQLException {
        emailText = email.getText();
        passwordText = password.getText();
        if(emailText.isEmpty() || passwordText.isEmpty()){
            fillErr.setStyle("-fx-opacity:1");
            return;
        }
        DbUtils db = new DbUtils();
        boolean drapeau = db.validate(emailText,passwordText);
        if(drapeau) Navigator.switchTo("fxmlFiles/home.fxml",login);
        else incorrectCredentials.setStyle("-fx-opacity:1");
    }

    public static String getEmailText() {
        return emailText;
    }

}
