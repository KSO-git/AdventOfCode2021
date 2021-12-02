package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    private static List<Integer> sonarDepthReadings = new ArrayList<>();

    public static void main(String[] args) {
        getTestDataFromFile();

        System.out.println(countIcreasingDepth());

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
}
