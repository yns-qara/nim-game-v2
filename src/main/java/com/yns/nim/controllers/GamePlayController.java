package com.yns.nim.controllers;


import com.yns.nim.SoundHandler;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;


public class GamePlayController implements Initializable {


    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, re, validate1, validate, goHome;
    @FXML
    private Label winP, scoreP, usernameP, winAI, scoreAI;

    public Button getB1() {
        return b1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            StatsHandler.showStats(scoreP, winP, scoreAI, winAI, usernameP);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToHome() throws IOException {
        Navigator.switchTo("fxmlFiles/home.fxml", goHome);
    }

    public void switchToRules() throws IOException {
        Navigator.switchTo("fxmlFiles/rules.fxml", goHome);
    }

    public void restart() throws IOException {
        Navigator.switchTo("fxmlFiles/game-play.fxml", re);
        validate.setDisable(true);
        validate1.setDisable(false);
    }

    /*------------------------------------Human player logic here: --------------------------------------*/

    public void selectItem(Event e) throws URISyntaxException {

        if (Navigator.computer_turn) return;

        Button[] items = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16};

        Button item = (Button) e.getTarget();
        item.setStyle("-fx-opacity:0");

        int currentRow = Utilities.getCurrentRow(item, items);

        for (Button b : items) {
            if (currentRow == 1 && b != b1) {
                b.setDisable(true);
            } else if (currentRow == 2 && b != b2 && b != b3 && b != b4) {
                b.setDisable(true);
            } else if (currentRow == 3 && b != b5 && b != b6 && b != b7 && b != b8 && b != b9) {
                b.setDisable(true);
            } else if (currentRow == 4 && b != b10 && b != b11 && b != b12 && b != b13 && b != b14 && b != b15 && b != b16) {
                b.setDisable(true);
            }
        }

        validate.setDisable(true);

        SoundHandler.play("audio/select.mp3");


    }

    public void validateChoice() throws IOException, SQLException, URISyntaxException {

        Button[] items = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16};

        int[] rows_before = Utilities.countPerRow(items);

        for (Button B : items) {
            if (Objects.equals(B.getStyle(), "-fx-opacity:0")) {
                B.setDisable(true);
            } else {
                B.setDisable(false);
            }

        }

        int[] rows_after = Utilities.countPerRow(items);

        if (Utilities.count(items) == 1 && !Navigator.computer_turn) {
//            String[] stats = StatsHandler.getStats(SignInController.getEmailText());
//            int id = Integer.parseInt(stats[0]);
//            int score = Integer.parseInt(stats[1]) + 100;
//            int wins = Integer.parseInt(stats[2]) + 1;
//            StatsHandler.insertPlayerStats(id, score, wins);
//            scoreP.setText(String.valueOf(score));
//            winP.setText(String.valueOf(wins));
            Navigator.matchResult(validate, "audio/win.mp3", "win!", "fxmlFiles/win.fxml",scoreAI,winAI,scoreP,winP);
        }

        if (!Arrays.toString(rows_before).equals(Arrays.toString(rows_after))) {
            SoundHandler.play("audio/validate.mp3");
            Navigator.computer_turn = true;
            validate1.setDisable(true);
            validate.setDisable(false);
        }


    }

    /*-------------------------------------------------------------------------------------------------*/



    /*------------------------------------computer logic here: --------------------------------------*/

//    public void computerTurn() throws IOException, SQLException, URISyntaxException {
//        /*
//         * the computer turn function tries to simulate how a skilled player would play the game of NIM.
//         * it gives each row its own array of buttons (row_1, row_2...)
//         * row_counter is used to count how many items are there in each row of the four
//         * row_result saves the returned array of the function canMakeNimSum
//         *
//         * if it's cmp turn && all the buttons are still not disabled --> remove one from a random row
//         *
//         */
//        Button[] items = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16};
//        Button[] row_1 = {b1};
//        Button[] row_2 = {b2, b3, b4};
//        Button[] row_3 = {b5, b6, b7, b8, b9};
//        Button[] row_4 = {b10, b11, b12, b13, b14, b15, b16};
//        int[] row_counter = {0, 0, 0, 0};
//        int[] row_result;
//        int[] current_row_count;
//
//        if (!row_1[0].isDisabled()) row_counter[0]++;
//        for (Button b : row_2) {
//            if (!b.isDisabled()) row_counter[1]++;
//        }
//        for (Button b : row_3) {
//            if (!b.isDisabled()) row_counter[2]++;
//        }
//        for (Button b : row_4) {
//            if (!b.isDisabled()) row_counter[3]++;
//        }
//
//        int r1_len = Integer.parseInt(Integer.toBinaryString(row_counter[0]));
//        int r2_len = Integer.parseInt(Integer.toBinaryString(row_counter[1]));
//        int r3_len = Integer.parseInt(Integer.toBinaryString(row_counter[2]));
//        int r4_len = Integer.parseInt(Integer.toBinaryString(row_counter[3]));
//
//
//        boolean first_move = true;
//        for (Button b : items) {
//            if (b.isDisabled()) {
//                first_move = false;
//                break;
//            }
//        }
//
//
//        int[] c = Utilities.countPerRow(items);
//        int c0 = Integer.parseInt(String.valueOf(c[0]), 2);
//        int c1 = Integer.parseInt(String.valueOf(c[1]), 2);
//        int c2 = Integer.parseInt(String.valueOf(c[2]), 2);
//        int c3 = Integer.parseInt(String.valueOf(c[3]), 2);
//
//        Navigator.computer_turn = true;
//        if (Navigator.computer_turn && first_move) {
//
//
//            double random_row = Math.floor((Math.random() * 4) + 1);
//            if (random_row == 1)
//                Utilities.hideAndDisable(row_1[0]);
//            else if (random_row == 2)
//                Utilities.hideAndDisable(row_2[0]);
//            else if (random_row == 3)
//                Utilities.hideAndDisable(row_3[0]);
//            else if (random_row == 4)
//                Utilities.hideAndDisable(row_4[0]);
//
//        }
//        /*-- start of particular cases , when we have 3 rows in wich two of them have only one button and the other is greater
//         than 1.
//         --*/
//        else if ((c[0] == 0 && c[1] > 1 && c[2] == 1 && c[3] == 1) || (c[0] == 1 && c[1] > 1 && c[2] == 0 && c[3] == 1) || (c[0] == 1 && c[1] > 1 && c[2] == 1 && c[3] == 0)) {
//            int i = 0;
//            for (Button b : row_2) {
//                if (!b.isDisabled() && i < c1 - 1) {
//                    Utilities.hideAndDisable(b);
//                    i++;
//                }
//            }
//        } else if ((c[0] == 0 && c[1] == 1 && c[2] > 1 && c[3] == 1) || (c[0] == 1 && c[1] == 0 && c[2] > 1 && c[3] == 1) || (c[0] == 1 && c[1] == 1 && c[2] > 1 && c[3] == 0)) {
//            int i = 0;
//            for (Button b : row_3) {
//                if (!b.isDisabled() && i < c2 - 1) {
//                    Utilities.hideAndDisable(b);
//                    i++;
//                }
//            }
//        } else if ((c[0] == 0 && c[1] == 1 && c[2] == 1 && c[3] > 1) || (c[0] == 1 && c[1] == 0 && c[2] == 1 && c[3] > 1) || (c[0] == 1 && c[1] == 1 && c[2] == 0 && c[3] > 1)) {
//            int i = 0;
//            for (Button b : row_4) {
//                if (!b.isDisabled() && i < c3 - 1) {
//                    Utilities.hideAndDisable(b);
//                    i++;
//                }
//            }
//        }
//
//        /*-- start of the particular case 2 : where we have 3 items left and it's the computer turn --*/
//        else if (c[0] + c[1] + c[2] + c[3] == 11 && (c[0] == 10 || c[1] == 10 || c[2] == 10 || c[3] == 10)) {
//            if (c[1] == 10) {
//                for (Button b : row_2) {
//                    if (!b.isDisabled()) Utilities.hideAndDisable(b);
//                }
//            } else if (c[2] == 10) {
//                for (Button b : row_3) {
//                    if (!b.isDisabled()) Utilities.hideAndDisable(b);
//                }
//            } else if (c[3] == 10) {
//                for (Button b : row_4) {
//                    if (!b.isDisabled()) Utilities.hideAndDisable(b);
//                }
//            }
//        } else if ((c[1] > 1 && c[0] == 0 && c[2] == 0 && c[3] == 0) || (c[2] > 1 && c[0] == 0 && c[1] == 0 && c[3] == 0) || (c[3] > 1 && c[0] == 0 && c[2] == 0 && c[1] == 0)) {
//            if (c[1] > 1) {
//                int i = 0;
//                for (Button b : row_2) {
//                    if (!b.isDisabled() && i < c1 - 1) {
//                        Utilities.hideAndDisable(b);
//                        i++;
//                    }
//                }
//            } else if (c[2] > 1) {
//                int i = 0;
//                for (Button b : row_3) {
//                    if (!b.isDisabled() && i < c2 - 1) {
//                        Utilities.hideAndDisable(b);
//                        i++;
//                    }
//                }
//            } else {
//                int i = 0;
//                for (Button b : row_4) {
//                    if (!b.isDisabled() && i < c3 - 1) {
//                        Utilities.hideAndDisable(b);
//                        i++;
//                    }
//                }
//            }
//        }
//        /* --
//         Particular case 3 : if there is two rows and one of them has only one item
//         -- */
//        else if ((c1 > 1 && c0 == 0 && c2 == 0 && c3 == 1) || (c1 > 1 && c0 == 0 && c2 == 1 && c3 == 0) || (c1 > 1 && c0 == 1 && c2 == 0 && c3 == 0)) {
//            for (Button b : row_2) {
//                if (!b.isDisabled()) Utilities.hideAndDisable(b);
//            }
//        } else if ((c2 > 1 && c0 == 0 && c1 == 0 && c3 == 1) || (c2 > 1 && c0 == 0 && c1 == 1 && c3 == 0) || (c2 > 1 && c0 == 1 && c1 == 0 && c3 == 0)) {
//            for (Button b : row_3) {
//                if (!b.isDisabled()) Utilities.hideAndDisable(b);
//            }
//        } else if ((c3 > 1 && c0 == 0 && c1 == 0 && c2 == 1) || (c3 > 1 && c0 == 0 && c1 == 1 && c2 == 0) || (c3 > 1 && c0 == 1 && c1 == 0 && c2 == 0)) {
//            for (Button b : row_4) {
//                if (!b.isDisabled()) Utilities.hideAndDisable(b);
//            }
//        }
//        /* -- end of particular case 3 -- */
//        /* -- start of p c 4 ... when 3 rows have one item and the other is full*/
//
//        else if ((c0 == 1 && c1 == 1 && c2 == 1)) {
//            for (Button b : row_4) {
//                if (!b.isDisabled()) Utilities.hideAndDisable(b);
//            }
//        } else if ((c0 == 1 && c1 == 1 && c3 == 1)) {
//            for (Button b : row_3) {
//                if (!b.isDisabled()) Utilities.hideAndDisable(b);
//            }
//        } else if ((c0 == 1 && c2 == 1 && c3 == 1)) {
//            for (Button b : row_2) {
//                if (!b.isDisabled()) Utilities.hideAndDisable(b);
//            }
//        }
//
//        /* -- end of p c 4 --*/
//        else {
//
//
//            current_row_count = Utilities.countPerRow(items);
//            row_result = Utilities.possibleNimSum(r1_len, r2_len, r3_len, r4_len);
//
//            int row2InDecimal = Integer.parseInt(String.valueOf(row_result[1]), 2);
//            int row3InDecimal = Integer.parseInt(String.valueOf(row_result[2]), 2);
//            int row4InDecimal = Integer.parseInt(String.valueOf(row_result[3]), 2);
//
//            int r2 = Integer.parseInt(String.valueOf(current_row_count[1]), 2);
//            int r3 = Integer.parseInt(String.valueOf(current_row_count[2]), 2);
//            int r4 = Integer.parseInt(String.valueOf(current_row_count[3]), 2);
//
//
//
//            /* -- disabling the buttons here : -- */
//            if (row_result[0] != current_row_count[0]) {
//                Utilities.hideAndDisable(row_1[0]);
//            } else if (row_result[1] != current_row_count[1]) {
//                int i = 0;
//                for (Button b : row_2) {
//                    if (!b.isDisabled() && i < r2 - row2InDecimal) {
//                        Utilities.hideAndDisable(b);
//                        i++;
//                    }
//                }
//            } else if (row_result[2] != current_row_count[2]) {
//                int i = 0;
//                for (Button b : row_3) {
//                    if (!b.isDisabled() && i < r3 - row3InDecimal) {
//                        Utilities.hideAndDisable(b);
//                        i++;
//                    }
//                }
//            } else if (row_result[3] != current_row_count[3]) {
//                int i = 0;
//                for (Button b : row_4) {
//                    if (!b.isDisabled() && i < r4 - row4InDecimal) {
//                        Utilities.hideAndDisable(b);
//                        i++;
//                    }
//                }
//            } else {
//                for (Button b : items) {
//                    if (!b.isDisabled()) {
//                        Utilities.hideAndDisable(b);
//                        break;
//                    }
//                }
//            }
//
//        }
//
//        SoundHandler.play("audio/aiturn.mp3");
//
//
//        if (Utilities.count(items) == 1 && Navigator.computer_turn) {
//
//
//            String[] stats = StatsHandler.getStats(SignInController.getEmailText());
//            int id = Integer.parseInt(stats[0]);
//            int ai_score = Integer.parseInt(stats[3]) + 100;
//            int ai_wins = Integer.parseInt(stats[4]) + 1;
//            StatsHandler.insertAiStats(id, ai_score, ai_wins);
//            scoreAI.setText(String.valueOf(ai_score));
//            winAI.setText(String.valueOf(ai_wins));
//            System.out.println("computer won");
//            Navigator.matchResult(validate, "audio/loss.mp3", "loss!", "fxmlFiles/loss.fxml");
//        }
//
//
//        Navigator.computer_turn = false;
//        validate1.setDisable(false);
//        validate.setDisable(true);
//
//
//    }


    public void computerTurn() {
        Button[] items = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16};

        try {
            ComputerLogic.computerLogic(items,scoreAI,winAI,validate,validate1,scoreP,winP);
        } catch (IOException | SQLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    /*-------------------------------------------------------------------------------------------------*/


}


