package com.yns.nim.controllers;

import com.yns.nim.Main;
import com.yns.nim.SoundHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Objects;

public class Navigator{


    public static boolean computer_turn = false;

    public static void switchTo(String path, Button b) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(path)));
        Stage window = (Stage) (b.getScene().getWindow());
        window.setScene(new Scene(root));
    }


    public static void matchResult(Button b , String audio , String title , String path, Label scoreAI,Label winAI,Label scoreP,Label winP) throws IOException, URISyntaxException, SQLException {

        String[] stats = StatsHandler.getStats(SignInController.getEmailText());
        int id = Integer.parseInt(stats[0]);
        int ai_score = Integer.parseInt(stats[3]) + 100;
        int ai_wins = Integer.parseInt(stats[4]) + 1;
        StatsHandler.insertAiStats(id, ai_score, ai_wins);
        scoreAI.setText(String.valueOf(ai_score));
        winAI.setText(String.valueOf(ai_wins));
        System.out.println("computer won");


        int score = Integer.parseInt(stats[1]) + 100;
        int wins = Integer.parseInt(stats[2]) + 1;
        StatsHandler.insertPlayerStats(id, score, wins);
        scoreP.setText(String.valueOf(score));
        winP.setText(String.valueOf(wins));

        SoundHandler.play(audio);
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setOnCloseRequest(e-> {
            try {
                switchTo("fxmlFiles/game-play.fxml",b);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }


    public static void displaySuccess(Button b) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxmlFiles/sign-up-ok.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("success!");
        stage.setOnCloseRequest(e-> {
            try {
                switchTo("fxmlFiles/sign-in.fxml",b);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }


}
