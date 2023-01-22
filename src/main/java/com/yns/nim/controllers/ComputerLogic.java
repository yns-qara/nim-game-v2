package com.yns.nim.controllers;

import com.yns.nim.SoundHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static com.yns.nim.controllers.Utilities.hideMultiple;

public class ComputerLogic {




    public static void computerLogic(Button[] items, Label scoreAI, Label winAI, Button validate, Button validate1,Label scoreP,Label winP) throws IOException, SQLException, URISyntaxException {

        Button[] row_1 = {items[0]};
        Button[] row_2 = {items[1], items[2], items[3]};
        Button[] row_3 = {items[4], items[5], items[6], items[7], items[8]};
        Button[] row_4 = {items[9], items[10], items[11], items[12], items[13], items[13], items[14]};
        int[] row_counter = {0, 0, 0, 0};
        int[] row_result;
        int[] current_row_count;

        if (!row_1[0].isDisabled()) row_counter[0]++;
        for (Button b : row_2) {
            if (!b.isDisabled()) row_counter[1]++;
        }
        for (Button b : row_3) {
            if (!b.isDisabled()) row_counter[2]++;
        }
        for (Button b : row_4) {
            if (!b.isDisabled()) row_counter[3]++;
        }

        int r1_len = Integer.parseInt(Integer.toBinaryString(row_counter[0]));
        int r2_len = Integer.parseInt(Integer.toBinaryString(row_counter[1]));
        int r3_len = Integer.parseInt(Integer.toBinaryString(row_counter[2]));
        int r4_len = Integer.parseInt(Integer.toBinaryString(row_counter[3]));


        boolean first_move = true;
        for (Button b : items) {
            if (b.isDisabled()) {
                first_move = false;
                break;
            }
        }


        int[] c = Utilities.countPerRow(items);
        int c0 = Integer.parseInt(String.valueOf(c[0]), 2);
        int c1 = Integer.parseInt(String.valueOf(c[1]), 2);
        int c2 = Integer.parseInt(String.valueOf(c[2]), 2);
        int c3 = Integer.parseInt(String.valueOf(c[3]), 2);

        Navigator.computer_turn = true;
        if (Navigator.computer_turn && first_move) {


            double random_row = Math.floor((Math.random() * 4) + 1);
            if (random_row == 1)
                Utilities.hideAndDisable(row_1[0]);
            else if (random_row == 2)
                Utilities.hideAndDisable(row_2[0]);
            else if (random_row == 3)
                Utilities.hideAndDisable(row_3[0]);
            else if (random_row == 4)
                Utilities.hideAndDisable(row_4[0]);

        }
        /*-- start of particular cases , when we have 3 rows in wich two of them have only one button and the other is greater
         than 1.
         --*/
        else if ((c[0] == 0 && c[1] > 1 && c[2] == 1 && c[3] == 1) || (c[0] == 1 && c[1] > 1 && c[2] == 0 && c[3] == 1) || (c[0] == 1 && c[1] > 1 && c[2] == 1 && c[3] == 0)) {
            hideMultiple(row_2,c1,1);
        } else if ((c[0] == 0 && c[1] == 1 && c[2] > 1 && c[3] == 1) || (c[0] == 1 && c[1] == 0 && c[2] > 1 && c[3] == 1) || (c[0] == 1 && c[1] == 1 && c[2] > 1 && c[3] == 0)) {
            hideMultiple(row_3,c2,1);
        } else if ((c[0] == 0 && c[1] == 1 && c[2] == 1 && c[3] > 1) || (c[0] == 1 && c[1] == 0 && c[2] == 1 && c[3] > 1) || (c[0] == 1 && c[1] == 1 && c[2] == 0 && c[3] > 1)) {
            hideMultiple(row_4,c3,1);
        }

        /*-- start of the particular case 2 : where we have 3 items left and it's the computer turn --*/
        else if (c[0] + c[1] + c[2] + c[3] == 11 && (c[0] == 10 || c[1] == 10 || c[2] == 10 || c[3] == 10)) {
            if (c[1] == 10) {
                for (Button b : row_2) {
                    if (!b.isDisabled()) Utilities.hideAndDisable(b);
                }
            } else if (c[2] == 10) {
                for (Button b : row_3) {
                    if (!b.isDisabled()) Utilities.hideAndDisable(b);
                }
            } else if (c[3] == 10) {
                for (Button b : row_4) {
                    if (!b.isDisabled()) Utilities.hideAndDisable(b);
                }
            }
        } else if ((c[1] > 1 && c[0] == 0 && c[2] == 0 && c[3] == 0) || (c[2] > 1 && c[0] == 0 && c[1] == 0 && c[3] == 0) || (c[3] > 1 && c[0] == 0 && c[2] == 0 && c[1] == 0)) {
            if (c[1] > 1) {
                hideMultiple(row_2,c1,1);
            } else if (c[2] > 1) {
                hideMultiple(row_3,c2,1);
            } else {
                hideMultiple(row_4,c3,1);
            }
        }
        /* --
         Particular case 3 : if there is two rows and one of them has only one item
         -- */
        else if ((c1 > 1 && c0 == 0 && c2 == 0 && c3 == 1) || (c1 > 1 && c0 == 0 && c2 == 1 && c3 == 0) || (c1 > 1 && c0 == 1 && c2 == 0 && c3 == 0)) {
            for (Button b : row_2) {
                if (!b.isDisabled()) Utilities.hideAndDisable(b);
            }
        } else if ((c2 > 1 && c0 == 0 && c1 == 0 && c3 == 1) || (c2 > 1 && c0 == 0 && c1 == 1 && c3 == 0) || (c2 > 1 && c0 == 1 && c1 == 0 && c3 == 0)) {
            for (Button b : row_3) {
                if (!b.isDisabled()) Utilities.hideAndDisable(b);
            }
        } else if ((c3 > 1 && c0 == 0 && c1 == 0 && c2 == 1) || (c3 > 1 && c0 == 0 && c1 == 1 && c2 == 0) || (c3 > 1 && c0 == 1 && c1 == 0 && c2 == 0)) {
            for (Button b : row_4) {
                if (!b.isDisabled()) Utilities.hideAndDisable(b);
            }
        }
        /* -- end of particular case 3 -- */
        /* -- start of p c 4 ... when 3 rows have one item and the other is full*/

        else if ((c0 == 1 && c1 == 1 && c2 == 1)) {
            for (Button b : row_4) {
                if (!b.isDisabled()) Utilities.hideAndDisable(b);
            }
        } else if ((c0 == 1 && c1 == 1 && c3 == 1)) {
            for (Button b : row_3) {
                if (!b.isDisabled()) Utilities.hideAndDisable(b);
            }
        } else if ((c0 == 1 && c2 == 1 && c3 == 1)) {
            for (Button b : row_2) {
                if (!b.isDisabled()) Utilities.hideAndDisable(b);
            }
        }

        /* -- end of p c 4 --*/
        else {


            current_row_count = Utilities.countPerRow(items);
            row_result = Utilities.possibleNimSum(r1_len, r2_len, r3_len, r4_len);

            int row2InDecimal = Integer.parseInt(String.valueOf(row_result[1]), 2);
            int row3InDecimal = Integer.parseInt(String.valueOf(row_result[2]), 2);
            int row4InDecimal = Integer.parseInt(String.valueOf(row_result[3]), 2);

            int r2 = Integer.parseInt(String.valueOf(current_row_count[1]), 2);
            int r3 = Integer.parseInt(String.valueOf(current_row_count[2]), 2);
            int r4 = Integer.parseInt(String.valueOf(current_row_count[3]), 2);



            /* -- disabling the buttons here : -- */
            if (row_result[0] != current_row_count[0]) {
                Utilities.hideAndDisable(row_1[0]);
            } else if (row_result[1] != current_row_count[1]) {
                hideMultiple(row_2,r2,row2InDecimal);
            } else if (row_result[2] != current_row_count[2]) {
                hideMultiple(row_3,r3,row3InDecimal);
            } else if (row_result[3] != current_row_count[3]) {
                hideMultiple(row_4,r4,row4InDecimal);
            } else {
                for (Button b : items) {
                    if (!b.isDisabled()) {
                        Utilities.hideAndDisable(b);
                        break;
                    }
                }
            }

        }

        SoundHandler.play("audio/aiturn.mp3");


        if (Utilities.count(items) == 1 && Navigator.computer_turn) {
            Navigator.matchResult(validate, "audio/loss.mp3", "loss!", "fxmlFiles/loss.fxml",scoreAI,winAI,scoreP,winP);
        }


        Navigator.computer_turn = false;
        validate1.setDisable(false);
        validate.setDisable(true);


    }



}
