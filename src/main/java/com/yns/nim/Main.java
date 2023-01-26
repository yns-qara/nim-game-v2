package com.yns.nim;

import javafx.application.Application ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxmlFiles/sign-in.fxml"));

//        Font.loadFont(Objects.requireNonNull(getClass().getResource("style/Kodchasan-Bold.ttf")).toExternalForm(), 10);

        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style/main.css")).toExternalForm());

        stage.setTitle("Nim Game!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}