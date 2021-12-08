package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8 {
    private static int numberOfOneFourSevenEight = 0;
    private static int sumForPart2 = 0;
    private static List<Digit> mainDisplay = new ArrayList<Digit>();


    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

//        getDataFromFile(fileName);
//        getDataFromFile(fileNameTest);

//        getDataFromFilePart2(fileNameTest);
//        getDataFromFilePart2(fileName);
        getDataFromFileForSimplerSolution(fileNameTest);
//        getDataFromFileForSimplerSolution(fileName);
        System.out.println(sumForPart2);

    }

    //Brute Solution
    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                String[] initialInputs = inputLine.split(" \\| ");
                Digit currentDigit = new Digit();
                int powerOf10 = 3;
                int tempSum = 0;
                currentDigit.buildpossible(inputLine);
                for (String entry : initialInputs[1].split(" ")) {
                    Display display = new Display(currentDigit, entry);
                    tempSum += (display.getNumberFromReading() * Math.pow(10, powerOf10));
                    powerOf10--;
                }
                System.out.println("TempSumFor that row: " + tempSum);
                sumForPart2 += tempSum;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Nice Solution
    private static void getDataFromFileForSimplerSolution(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                String[] initialInputs = inputLine.split(" \\| ");
                SimpleSolution simple = new SimpleSolution(inputLine);
                int powerOf10 = 3;
                int tempSum = 0;
                for (String entry : initialInputs[1].split(" ")) {
                    int digit = simple.calcualteDigitValue(entry);
                    tempSum += (digit * Math.pow(10, powerOf10));
                    powerOf10--;
                }

                System.out.println("TempSumFor that row: " + tempSum);
                sumForPart2 += tempSum;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
