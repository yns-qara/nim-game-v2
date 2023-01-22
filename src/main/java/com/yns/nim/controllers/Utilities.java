package com.yns.nim.controllers;

import javafx.scene.control.Button;

public class Utilities {

    public static int binarySub(int a, int b) {
        String first = String.valueOf(Integer.valueOf(a));
        String second = String.valueOf(Integer.valueOf(b));
        int b1 = Integer.parseInt(first, 2);
        int b2 = Integer.parseInt(second, 2);
        int sum = b1 - b2;
        return Integer.parseInt(Integer.toBinaryString(sum));
    }


    public static int count(Button[] items) {
        int c = 0;
        for (Button b : items) {
            if (!b.isDisabled()) c++;
        }
        return c;
    }


    public static int[] countPerRow(Button[] items) {

        int[] rowSums = {0, 0, 0, 0};


        int i = 0;
        for (Button item : items) {
            if (i == 0) {
                if (!item.isDisabled()) {
                    rowSums[0]++;
                } else {
                    i++;
                    continue;
                }
            } else if (i < 4) {
                if (!item.isDisabled()) {
                    rowSums[1]++;
                } else {
                    i++;
                    continue;
                }
            } else if (i < 9) {
                if (!item.isDisabled()) {
                    rowSums[2]++;
                } else {
                    i++;
                    continue;
                }
            } else {
                if (!item.isDisabled()) {
                    rowSums[3]++;
                } else {
                    i++;
                    continue;
                }

            }
            i++;
        }

        // convert the counts to binary, so we can use them to check for nimSum
        rowSums[0] = Integer.parseInt(Integer.toBinaryString(rowSums[0]));
        rowSums[1] = Integer.parseInt(Integer.toBinaryString(rowSums[1]));
        rowSums[2] = Integer.parseInt(Integer.toBinaryString(rowSums[2]));
        rowSums[3] = Integer.parseInt(Integer.toBinaryString(rowSums[3]));

        return rowSums;
    }

    public static int getCurrentRow(Button b,Button[] items) {
        if (b == items[0]) return 1;
        else if (b == items[1] || b == items[2] || b == items[3]) return 2;
        else if (b == items[4] || b == items[5] || b == items[6] || b == items[7] || b == items[8]) return 3;
        else return 4;
    }



    public static int[] possibleNimSum(int r1, int r2, int r3, int r4) {
 /*takes the current number of undisabled buttons in each row
     and returns the number of buttons in each row that may make a nim sum
     */
        int original_r1 = r1;
        int original_r2 = r2;
        int original_r3 = r3;
        int original_r4 = r4;

        int[] T = {r1, r2, r3, r4};

        int xor_result;

        while (r1 > 0) {
            r1 = Utilities.binarySub(r1, 1);
            xor_result = r1 ^ r2 ^ r3 ^ r4;
            if (xor_result == 0) return new int[]{r1, r2, r3, r4};
        }
        while (r2 > 0) {
            r2 = Utilities.binarySub(r2, 1);
            xor_result = original_r1 ^ r2 ^ r3 ^ r4;
            if (xor_result == 0) return new int[]{original_r1, r2, r3, r4};

        }
        while (r3 > 0) {
            r3 = Utilities.binarySub(r3, 1);
            xor_result = original_r1 ^ original_r2 ^ r3 ^ r4;
            if (xor_result == 0) return new int[]{original_r1, original_r2, r3, r4};
        }
        while (r4 > 0) {
            r4 = Utilities.binarySub(r4, 1);
            xor_result = original_r1 ^ original_r2 ^ original_r3 ^ r4;
            if (xor_result == 0) return new int[]{original_r1, original_r2, original_r3, r4};
        }

        return new int[]{original_r1, original_r2, original_r3, original_r4};
    }

    public static void hideAndDisable(Button b) {
        b.setDisable(true);
        b.setStyle("-fx-opacity:0");
    }


    public static void hideMultiple(Button[] row,int count,int q){
        int i = 0;
        for (Button b : row) {
            if (!b.isDisabled() && i < count - q) {
                Utilities.hideAndDisable(b);
                i++;
            }
        }
    }



}
