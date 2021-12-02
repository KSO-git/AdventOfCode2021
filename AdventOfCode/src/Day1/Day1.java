package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    private static List<Integer> sonarDepthReadings = new ArrayList<>();
    private static Map<Integer, Integer> sonarDepthReadingsSumOfThree = new HashMap<>();

    public static void main(String[] args) {
        //getTestDataFromFile();
        getDataFromFile();

        System.out.println(countIcreasingDepthByThreeMeasurment());

    }

    private static void getDataFromFile() {
        try {
            File myObj = new File("data/inputDay1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                sonarDepthReadings.add(Integer.parseInt(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void getTestDataFromFile() {
        try {
            File myObj = new File("data/inputDay1TEST.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                sonarDepthReadings.add(Integer.parseInt(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int countIcreasingDepth(){
        int result = 0;
        for(int i = 1; i < sonarDepthReadings.size(); i++){
            if(sonarDepthReadings.get(i) >  sonarDepthReadings.get(i-1)){
                result++;
            }
        }
        return result;
    }

    private static int countIcreasingDepthByThreeMeasurment(){
        int result = 0;
        int counter = 0;
        for(int i = 0; i < sonarDepthReadings.size() - 2; i++){
            int sumOfthree = sonarDepthReadings.get(i) +
                    sonarDepthReadings.get(i+1) + sonarDepthReadings.get(i+2);
            sonarDepthReadingsSumOfThree.put(counter, sumOfthree);
            counter++;
        }
        int maxNumber = sonarDepthReadingsSumOfThree.keySet().size() - 1;
        for(int i = 1; i <= maxNumber; i++){
            if(sonarDepthReadingsSumOfThree.get(i) >  sonarDepthReadingsSumOfThree.get(i-1)){
                result++;
            }
        }
        return result;
    }
}
