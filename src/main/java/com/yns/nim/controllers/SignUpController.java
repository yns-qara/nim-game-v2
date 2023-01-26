package com.yns.nim.controllers;

import com.yns.nim.Utilities.DataBaseOperations.DbUtils;
import com.yns.nim.Utilities.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpController{

    @FXML
    private Button save;
    @FXML
    private TextField firstName , lastName, email;
    @FXML
    private PasswordField password, confirmPassword;
    @FXML
    private Label errMessage;



    public void switchToSignIn() throws IOException {
        Navigator.switchTo("fxmlFiles/sign-in.fxml",save);
    }


    public void register() throws IOException {

        String userName = firstName.getText() + "-" + lastName.getText();
        String emailText = email.getText();
        String passwordText = password.getText();
        String passwordConfirm = confirmPassword.getText();


        if (userName.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || passwordConfirm.isEmpty()) {
            errMessage.setText("make sure to fill all the fields");
            return;
        }

        if (!passwordConfirm.equals(passwordText)) {
            errMessage.setText("passwords don't match");
            return;
        }


        DbUtils db = new DbUtils();
        try {
            db.insertRecord(userName, emailText, passwordText);
            Navigator.displaySuccess(save);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }






}
