package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day9 {
    private static int[][] heightReadings;
    private static int[][] basinMap;
    private static int columns;
    private static int rows = 100;
    private static List<Integer> lowPoints = new ArrayList<>();
    private static Map<PointCoordinates, Integer> lowPointsMap = new HashMap<PointCoordinates, Integer>();
    private static List<PointCoordinates> basinList = new ArrayList<PointCoordinates>();


    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

        getDataFromFile(fileName);
//        getDataFromFile(fileNameTest);

        findLocalLowPoints();
        countBasins();
        System.out.println(sumLowPoints());
        System.out.println(multiplyBasins());

    }

    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                columns = inputLine.length();
                if (i == 0) {
                    heightReadings = new int[rows][columns];
                }
                for (int j = 0; j < columns; j++) {
                    heightReadings[i][j] = Integer.parseInt(inputLine.charAt(j) + "");
                }
                i++;
                basinMap = new int[rows][columns];
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void findLocalLowPoints() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int checkedValue = heightReadings[i][j];
                if (j == 0) {
                    if (i == 0) {
                        int oneDown = heightReadings[i + 1][j];
                        int oneRight = heightReadings[i][j + 1];
                        if (checkedValue < oneDown && checkedValue < oneRight) {
                            lowPoints.add(checkedValue);
                            lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                        }
                    } else if (i == rows - 1) {
                        int oneUp = heightReadings[i - 1][j];
                        int oneRight = heightReadings[i][j + 1];
                        if (checkedValue < oneUp && checkedValue < oneRight) {
                            lowPoints.add(checkedValue);
                            lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                        }
                    } else {
                        int oneDown = heightReadings[i + 1][j];
                        int oneRight = heightReadings[i][j + 1];
                        int oneUp = heightReadings[i - 1][j];
                        if (checkedValue < oneDown && checkedValue < oneRight && checkedValue < oneUp) {
                            lowPoints.add(checkedValue);
                            lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                        }
                    }
                } else if (j == columns - 1) {
                    if (i == 0) {
                        int oneDown = heightReadings[i + 1][j];
                        int oneLeft = heightReadings[i][j - 1];
                        if (checkedValue < oneDown && checkedValue < oneLeft) {
                            lowPoints.add(checkedValue);
                            lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                        }
                    } else if (i == rows - 1) {
                        int oneUp = heightReadings[i - 1][j];
                        int oneLeft = heightReadings[i][j - 1];
                        if (checkedValue < oneUp && checkedValue < oneLeft) {
                            lowPoints.add(checkedValue);
                            lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                        }
                    } else {
                        int oneDown = heightReadings[i + 1][j];
                        int oneLeft = heightReadings[i][j - 1];
                        int oneUp = heightReadings[i - 1][j];
                        if (checkedValue < oneDown && checkedValue < oneLeft && checkedValue < oneUp) {
                            lowPoints.add(checkedValue);
                            lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                        }
                    }
                } else if (i == 0) {

                    int oneDown = heightReadings[i + 1][j];
                    int oneLeft = heightReadings[i][j - 1];
                    int oneRight = heightReadings[i][j + 1];
                    if (checkedValue < oneDown && checkedValue < oneLeft && checkedValue < oneRight) {
                        lowPoints.add(checkedValue);
                        lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                    }

                } else if (i == rows - 1) {
                    int oneUp = heightReadings[i - 1][j];
                    int oneLeft = heightReadings[i][j - 1];
                    int oneRight = heightReadings[i][j + 1];
                    if (checkedValue < oneUp && checkedValue < oneLeft && checkedValue < oneRight) {
                        lowPoints.add(checkedValue);
                        lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                    }

                } else {
                    if (isLowPoint(i, j)) {
                        lowPoints.add(checkedValue);
                        lowPointsMap.put(new PointCoordinates(i, j), checkedValue);
                    }
                }
            }
        }
    }

    private static boolean isLowPoint(int row, int col) {
        int checkedValue = heightReadings[row][col];
        int oneUp = heightReadings[row + 1][col];
        int oneDown = heightReadings[row - 1][col];
        int oneRight = heightReadings[row][col + 1];
        int oneLeft = heightReadings[row][col - 1];

        return (checkedValue < oneUp && checkedValue < oneDown && checkedValue < oneRight && checkedValue < oneLeft);

    }

    private static int sumLowPoints() {
        int sum = 0;
        for (Integer entry : lowPoints) {
            sum += (entry + 1);
        }
        return sum;
    }

    private static void countBasins() {
        for (PointCoordinates point : lowPointsMap.keySet()) {
            buildBasinMap(point.getRow(), point.getCol());
            point.setBasins(countBasinsAndClean());
        }
    }

    private static void buildBasinMap(int row, int col) {
        int checkedValue = heightReadings[row][col];
        basinMap[row][col] = 1;
        int basin = 1;
        if (row != rows - 1 && heightReadings[row + 1][col] != 9 && heightReadings[row + 1][col] > checkedValue) {
            buildBasinMap(row + 1, col);
        }
        if (row != 0 && heightReadings[row - 1][col] != 9 && heightReadings[row - 1][col] > checkedValue) {
            buildBasinMap(row - 1, col);
        }
        if (col != columns - 1 && heightReadings[row][col + 1] != 9 && heightReadings[row][col + 1] > checkedValue) {
            buildBasinMap(row, col + 1);
        }
        if (col != 0 && heightReadings[row][col - 1] != 9 && heightReadings[row][col - 1] > checkedValue) {
            buildBasinMap(row, col - 1);
        }
    }

    private static int countBasinsAndClean() {
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (basinMap[i][j] == 1) {
                    result++;
                    basinMap[i][j] = 0;
                }
            }
        }
        return result;
    }

    private static int multiplyBasins() {
        List<PointCoordinates> point = lowPointsMap.keySet().stream().sorted(Comparator.comparingInt(PointCoordinates::getBasins).reversed()).collect(Collectors.toList());
        return point.get(0).getBasins() * point.get(1).getBasins() * point.get(2).getBasins();
    }

}
