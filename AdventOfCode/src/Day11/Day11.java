package Day11;

import Day5.Pair;
import Day9.PointCoordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 {
    private static int[][] octopusGrid = new int[10][10];
    private static int numberOfFlashes = 0;
    private static int loopNumber = 0;


    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

        getDataFromFile(fileName);
//        getDataFromFile(fileNameTest);

        findLocalLowPoints(100);

        System.out.println("Ilosc Flashy: " + numberOfFlashes);
        System.out.println("Ilosc Loopów: " + loopNumber);

    }

    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                for (int j = 0; j < inputLine.length(); j++) {
                    octopusGrid[i][j] = Integer.parseInt(inputLine.charAt(j) + "");
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void findLocalLowPoints(int numberOFSteps) {
        boolean areAllFlashy = false;
        int numerOfLoopsTotal = 0;
        do {
            boolean wereThereAnyBoosts;
            int numerOfLoops = 0;
            do {
                wereThereAnyBoosts = false;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (numerOfLoops == 0) {
                            octopusGrid[i][j] = octopusGrid[i][j] + 1;
                        }
                        int checkedValue = octopusGrid[i][j];
                        if (checkedValue > 9 && checkedValue < 99) {
                            wereThereAnyBoosts = true;
                            octopusGrid[i][j]=99;
                            if (j == 0) {
                                if (i == 0) {
                                    octopusGrid[i + 1][j] += 1;
                                    octopusGrid[i][j + 1] += 1;
                                    octopusGrid[i + 1][j + 1] += 1;
                                } else if (i == 9) {
                                    octopusGrid[i - 1][j] += 1;
                                    octopusGrid[i][j + 1] += 1;
                                    octopusGrid[i - 1][j + 1] += 1;
                                } else {
                                    octopusGrid[i + 1][j] += 1;
                                    octopusGrid[i][j + 1] += 1;
                                    octopusGrid[i + 1][j + 1] += 1;
                                    octopusGrid[i - 1][j + 1] += 1;
                                    octopusGrid[i - 1][j] += 1;
                                }
                            } else if (j == 9) {
                                if (i == 0) {
                                    octopusGrid[i + 1][j] += 1;
                                    octopusGrid[i][j - 1] += 1;
                                    octopusGrid[i + 1][j - 1] += 1;
                                } else if (i == 9) {
                                    octopusGrid[i - 1][j] += 1;
                                    octopusGrid[i][j - 1] += 1;
                                    octopusGrid[i - 1][j - 1] += 1;
                                } else {
                                    octopusGrid[i + 1][j] += 1;
                                    octopusGrid[i][j - 1] += 1;
                                    octopusGrid[i - 1][j] += 1;
                                    octopusGrid[i + 1][j - 1] += 1;
                                    octopusGrid[i - 1][j - 1] += 1;
                                }
                            } else if (i == 0) {

                                octopusGrid[i + 1][j] += 1;
                                octopusGrid[i][j - 1] += 1;
                                octopusGrid[i][j + 1] += 1;
                                octopusGrid[i + 1][j - 1] += 1;
                                octopusGrid[i + 1][j + 1] += 1;

                            } else if (i == 9) {
                                octopusGrid[i - 1][j] += 1;
                                octopusGrid[i][j - 1] += 1;
                                octopusGrid[i][j + 1] += 1;
                                octopusGrid[i - 1][j + 1] += 1;
                                octopusGrid[i - 1][j - 1] += 1;

                            } else {

                                octopusGrid[i - 1][j] += 1;
                                octopusGrid[i + 1][j] += 1;
                                octopusGrid[i][j - 1] += 1;
                                octopusGrid[i][j + 1] += 1;
                                octopusGrid[i - 1][j + 1] += 1;
                                octopusGrid[i - 1][j - 1] += 1;
                                octopusGrid[i + 1][j + 1] += 1;
                                octopusGrid[i + 1][j - 1] += 1;
                            }
                        }
                    }
                }
                numerOfLoops++;
            }
            while (wereThereAnyBoosts);
            numerOfLoopsTotal++;
            areAllFlashy = areAllFlashyAndClean();
        } while (!areAllFlashy);
        loopNumber = numerOfLoopsTotal;
    }

    private static void cleanBoardAndCount() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(octopusGrid[i][j] >=99) {
                    octopusGrid[i][j]=0;
                    numberOfFlashes++;
                }
            }
        }
    }

    private static boolean areAllFlashyAndClean() {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(octopusGrid[i][j] >=99) {
                    octopusGrid[i][j]=0;
                    numberOfFlashes++;
                    result++;
                }
            }
        }
        return result == 100;
    }

    private static boolean isLowPoint(int row, int col) {
        int checkedValue = octopusGrid[row][col];
        int oneUp = octopusGrid[row + 1][col];
        int oneDown = octopusGrid[row - 1][col];
        int oneRight = octopusGrid[row][col + 1];
        int oneLeft = octopusGrid[row][col - 1];

        return (checkedValue < oneUp && checkedValue < oneDown && checkedValue < oneRight && checkedValue < oneLeft);

    }

}
