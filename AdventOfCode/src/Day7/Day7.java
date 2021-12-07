package Day7;

import Day6.FishBuckets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day7 {
    private static List<Integer> crabPositions = new ArrayList<>();
    private static FishBuckets fishBucket;


    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

        getDataFromFile(fileName);
//        getDataFromFile(fileNameTest);


//        System.out.println(findLowestPossible());
        System.out.println(findLowestPossibleWithProgressiveSteps());

    }

    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                String[] initialInputs = inputLine.split(",");

                for (String entry : initialInputs) {
                    crabPositions.add(Integer.parseInt(entry));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static long findLowestPossible(){
        int maxValue = Collections.max(crabPositions);
        long currentMinimumSum = 99999999999L;
        int currentXNumbers = 0 ;
        int currentFreeValue = 0;
        int closestCommonValue = 0;
        for(int i = 0; i < maxValue; i++){
            currentXNumbers = 0 ;
            currentFreeValue = 0;
            for(int entry : crabPositions){
                if( i > entry){
                    currentXNumbers--;
                    currentFreeValue -= entry;
                } else {
                    currentXNumbers++;
                    currentFreeValue += entry;
                }
            }
            long localMin = currentFreeValue - (currentXNumbers * i);
            if(localMin < currentMinimumSum){
                currentMinimumSum = localMin;
                closestCommonValue = i;
            }
        }
        System.out.println("Closest common value: " + closestCommonValue);
        return currentMinimumSum;
    }

    private static long calculateProgressiveSteps(int steps){
        if(steps == 0){
            return 0;
        }
        return steps + calculateProgressiveSteps(steps-1);
    }

    private static long findLowestPossibleWithProgressiveSteps(){
        int maxValue = Collections.max(crabPositions);
        long currentMinimumSum = 99999999999L;
        int currentFreeValue = 0;
        int closestCommonValue = 0;
        for(int i = 0; i < maxValue; i++){
            currentFreeValue = 0;
            for(int entry : crabPositions){
                if( i > entry){
                    currentFreeValue += calculateProgressiveSteps(i-entry);
                } else {
                    currentFreeValue += calculateProgressiveSteps(entry-i);
                }
            }
            long localMin = currentFreeValue;
            if(localMin < currentMinimumSum){
                currentMinimumSum = localMin;
                closestCommonValue = i;
            }
        }
        System.out.println("Closest common value: " + closestCommonValue);
        return currentMinimumSum;
    }

}
