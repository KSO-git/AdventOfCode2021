package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5 {
    private static int[][] hydroThermalVensMap= new int[1000][1000];
    private static int numberOfLinesIntersecting = 0;


    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

        getDataFromFile(fileName);
        //getDataFromFile(fileNameTest);

        System.out.println(numberOfLinesIntersecting);
        print10by10Matrix();

    }

    private static void print10by10Matrix() {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(hydroThermalVensMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                addVentsToMap(inputLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void addVentsToMap(String inputLine){
        String[] listOfInputs = inputLine.split("->");
        Pair startPoint = new Pair(Integer.parseInt(listOfInputs[0].trim().split(",")[0]), Integer.parseInt(listOfInputs[0].trim().split(",")[1]));
        Pair endPoint = new Pair(Integer.parseInt(listOfInputs[1].trim().split(",")[0]), Integer.parseInt(listOfInputs[1].trim().split(",")[1]));

        if(startPoint.arePoinOnTheSameStraightsX(endPoint)){
            int eksValue = startPoint.getX();
            int startOfVentValueY = Math.min(startPoint.getY(), endPoint.getY());
            int endOfVentValueY = Math.max(startPoint.getY(), endPoint.getY());
            for(int i = startOfVentValueY; i <= endOfVentValueY; i++){
                int currentValue = hydroThermalVensMap[i][eksValue];
                if(currentValue == 1){
                    numberOfLinesIntersecting++;
                }
                currentValue++;

                hydroThermalVensMap[i][eksValue] = currentValue;
            }
        } else if(startPoint.arePoinOnTheSameStraightsY(endPoint)){
            int wayValue = startPoint.getY();
            int startOfVentValueX = Math.min(startPoint.getX(), endPoint.getX());
            int endOfVentValueX = Math.max(startPoint.getX(), endPoint.getX());
            for(int i = startOfVentValueX; i <= endOfVentValueX; i++){
                int currentValue = hydroThermalVensMap[wayValue][i];
                if(currentValue == 1){
                    numberOfLinesIntersecting++;
                }
                currentValue++;
                hydroThermalVensMap[wayValue][i] = currentValue;
            }
        } else if(startPoint.areDiagonal(endPoint)){
            int absOfStart = Math.abs(startPoint.getX()- endPoint.getX());
            int absOfEnd = Math.abs(startPoint.getY() - endPoint.getY());
            if(startPoint.getY() == endPoint.getX()){
                for(int i = 0 ; i <= Math.abs(startPoint.getX() - startPoint.getY()); i++){
                    int min = Math.min(startPoint.getX(), startPoint.getY());
                    int max = Math.max(startPoint.getX(), startPoint.getY());
                    int currentValue = hydroThermalVensMap[max-i][min+i];
                    if(currentValue == 1){
                        numberOfLinesIntersecting++;
                    }
                    currentValue++;
                    hydroThermalVensMap[max-i][min+i] = currentValue;

                }
            } else if(absOfStart == absOfEnd){
                int numberOfSteps = Math.abs(startPoint.getX() - endPoint.getX());
                int modyfierOfX = startPoint.getX() - endPoint.getX() > 0 ? -1 : 1;
                int modyfierOfY = startPoint.getY() - endPoint.getY() > 0 ? -1 : 1;
                for(int i = 0; i <= numberOfSteps; i++){
                    int currentValue = hydroThermalVensMap[startPoint.getY()+(i*modyfierOfY)][startPoint.getX()+(i*modyfierOfX)];
                    if(currentValue == 1){
                        numberOfLinesIntersecting++;
                    }
                    currentValue++;
                    hydroThermalVensMap[startPoint.getY()+(i*modyfierOfY)][startPoint.getX()+(i*modyfierOfX)]= currentValue;
                }

            }
        }
    }

}
