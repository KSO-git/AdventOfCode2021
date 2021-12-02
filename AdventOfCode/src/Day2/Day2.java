package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2 {
    private static List<String> sonarDepthReadings = new ArrayList<>();

    public enum SumbarineCommands{
        FORWARD, UP, DOWN
    }

    public static void main(String[] args) {
        //getTestDataFromFile();
        getDataFromFile();

        //System.out.println(countPosition());
        System.out.println(countPositionWithAim());

    }

    private static void getDataFromFile() {
        String className = new Throwable().getStackTrace()[1].getClassName().split("\\.")[0];
        try {
            File myObj = new File("data/input" + className + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                sonarDepthReadings.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void getTestDataFromFile() {
        String className = new Throwable().getStackTrace()[1].getClassName().split("\\.")[0];
        try {
            File myObj = new File("data/input" + className + "TEST.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                sonarDepthReadings.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int countPosition(){
        int vertical = 0;
        int horizontal = 0;
        for (String sonarDepthReading : sonarDepthReadings) {
            String[] arrOfStr = sonarDepthReading.split(" ");
            SumbarineCommands direction = SumbarineCommands.valueOf(arrOfStr[0].toUpperCase());
            int amount = Integer.parseInt(arrOfStr[1]);
            switch (direction) {
                case FORWARD:
                    vertical += amount;
                    break;
                case UP:
                    horizontal -= amount;
                    break;
                case DOWN:
                    horizontal += amount;
                    break;
            }

        }
        return vertical * horizontal;
    }


    private static int countPositionWithAim(){
        int vertical = 0;
        int horizontal = 0;
        int aim = 0;
        for (String sonarDepthReading : sonarDepthReadings) {
            String[] arrOfStr = sonarDepthReading.split(" ");
            SumbarineCommands direction = SumbarineCommands.valueOf(arrOfStr[0].toUpperCase());
            int amount = Integer.parseInt(arrOfStr[1]);
            switch (direction) {
                case FORWARD:
                    vertical += amount;
                    horizontal += aim * amount;
                    break;
                case UP:
                    aim -= amount;
                    break;
                case DOWN:
                    aim += amount;
                    break;
            }

        }
        return vertical * horizontal;
    }
}
