package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    private static List<Byte> stateOfTheFishes = new ArrayList<>();
    private static FishBuckets fishBucket;


    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

        getDataFromFile(fileName);
        //getDataFromFile(fileNameTest);


        System.out.println(calculateFishesUsingBucket(256));

    }

    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                String[] initialInputs = inputLine.split(",");
                int numberOf0DayOld = 0;
                int numberOf1DayOld = 0;
                int numberOf2DayOld = 0;
                int numberOf3DayOld = 0;
                int numberOf4DayOld = 0;
                int numberOf5DayOld = 0;
                int numberOf6DayOld = 0;
                int numberOf7DayOld = 0;
                int numberOf8DayOld = 0;
                for(String entry : initialInputs){
                    //stateOfTheFishes.add(Integer.parseInt(entry));
                    int currentAge = Integer.parseInt(entry);
                    switch (currentAge){
                        case 0:
                            numberOf0DayOld++;
                            break;
                        case 1:
                            numberOf1DayOld++;
                            break;
                        case 2:
                            numberOf2DayOld++;
                            break;
                        case 3:
                            numberOf3DayOld++;
                            break;
                        case 4:
                            numberOf4DayOld++;
                            break;
                        case 5:
                            numberOf5DayOld++;
                            break;
                        case 6:
                            numberOf6DayOld++;
                            break;
                    }
                    fishBucket = new FishBuckets(numberOf0DayOld,numberOf1DayOld,numberOf2DayOld,numberOf3DayOld,numberOf4DayOld,numberOf5DayOld,numberOf6DayOld,numberOf7DayOld,numberOf8DayOld);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int simulateDays(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            int numberOfFishesToAdd = 0;
            for (int x = 0; x < stateOfTheFishes.size() ; x++) {
                if(stateOfTheFishes.get(x) == 0){
                    stateOfTheFishes.remove(x);
                    stateOfTheFishes.add(x, (byte)6);
                    numberOfFishesToAdd++;
                } else {
                    int currentValue = stateOfTheFishes.get(x);
                    stateOfTheFishes.remove(x);
                    stateOfTheFishes.add(x, (byte) (currentValue - 1));
                }
            }
            for (int j = 0; j < numberOfFishesToAdd; j++) {
                stateOfTheFishes.add((byte)8);
            }
            System.out.println("Day: " + i + " Ilośc rybek"+stateOfTheFishes.size());
        }
        return stateOfTheFishes.size();
    }

    private static long calculateFishesUsingBucket(int days){
        for(int i  = 0; i< days; i++){
            fishBucket.pass1Day();
        }
        return fishBucket.sumAllUp();
    }

}
