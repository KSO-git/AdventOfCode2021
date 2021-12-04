package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    private static List<String> dataBinaryInput = new ArrayList<>();


    public static void main(String[] args) {
        //getTestDataFromFile();
        getDataFromFile();

        //System.out.println(caluclatePowerconsumption());
        System.out.println(caluclateLifeSupport());

    }

    private static void getDataFromFile() {
        String className = new Throwable().getStackTrace()[1].getClassName().split("\\.")[0];
        try {
            File myObj = new File("data/input" + className + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                dataBinaryInput.add(myReader.nextLine());
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
                dataBinaryInput.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int caluclatePowerconsumption() {
        Map<Integer, List<Integer>> dataMap = createAndPopulatedataMap();
        for (String inputBinaryData : dataBinaryInput) {
            addBinaryInputToDataMap(inputBinaryData, dataMap);
        }
        String gamma = getGamma(dataMap);
        String epsilon = getEpsilon(dataMap);
        int gammaInteger = converBinaryToDecimal(gamma);
        int epsilonInteger = converBinaryToDecimal(epsilon);
        return gammaInteger * epsilonInteger;

    }

    private static int caluclateLifeSupport() {
        return caluclateOxygen() * caluclateCo2();
    }

    private static int caluclateOxygen() {
        Map<Integer, List<Integer>> dataMap = new HashMap<>();
        List<String> currentDataList = dataBinaryInput;
        int counter = 1;
        StringBuilder binarytoCheckOxygen = new StringBuilder();
        do {
            dataMap = createAndPopulatedataMap();
            for (String inputBinaryData : currentDataList) {
                addBinaryInputToDataMap(inputBinaryData, dataMap);
            }
            List<Integer> binaryList = dataMap.get(counter - 1);
            binarytoCheckOxygen.append(binaryList.get(0) > binaryList.get(1) ? "0" : "1");
            String binarytoCheckOxygen2 = binarytoCheckOxygen.toString();
            int counterInLoop = counter;
            currentDataList = currentDataList.stream().filter(s -> s.substring(0, counterInLoop).equals(binarytoCheckOxygen2)).collect(Collectors.toList());
            counter++;


        } while (currentDataList.size() > 1);


        return converBinaryToDecimal(currentDataList.get(0));

    }

    private static int caluclateCo2() {
        Map<Integer, List<Integer>> dataMap = new HashMap<>();
        List<String> currentDataList = dataBinaryInput;
        int counter = 1;
        StringBuilder binarytoCheckCo2 = new StringBuilder();
        do {
            dataMap = createAndPopulatedataMap();
            for (String inputBinaryData : currentDataList) {
                addBinaryInputToDataMap(inputBinaryData, dataMap);
            }
            List<Integer> binaryList = dataMap.get(counter - 1);
            binarytoCheckCo2.append(binaryList.get(0) <= binaryList.get(1) ? "0" : "1");
            String binarytoCheckCo22 = binarytoCheckCo2.toString();
            int counterInLoop = counter;
            currentDataList = currentDataList.stream().filter(s -> s.substring(0, counterInLoop).equals(binarytoCheckCo22)).collect(Collectors.toList());
            counter++;


        } while (currentDataList.size() > 1);


        return converBinaryToDecimal(currentDataList.get(0));

    }

    private static int converBinaryToDecimal(String input) {
        return Integer.parseInt(input, 2);
    }

    private static void addBinaryInputToDataMap(String binaryInput, Map<Integer, List<Integer>> dataMap) {
        for (int i = 0; i < binaryInput.length(); i++) {
            int binanryValue = Integer.parseInt("" + binaryInput.charAt(i));
            dataMap.get(i).set(binanryValue, dataMap.get(i).get(binanryValue) + 1);
        }
    }

    private static Map<Integer, List<Integer>> createAndPopulatedataMap() {
        Map<Integer, List<Integer>> result = new HashMap<>();
        int mapSize = dataBinaryInput.get(0).length();
        for (int i = 0; i < mapSize; i++) {
            List<Integer> emptyList = new ArrayList<>();
            emptyList.add(0);
            emptyList.add(0);
            result.put(i, emptyList);
        }
        return result;
    }

    private static String getGamma(Map<Integer, List<Integer>> dataMap) {
        StringBuilder reuslt = new StringBuilder();
        for (Integer key : dataMap.keySet()) {
            List<Integer> binaryList = dataMap.get(key);
            reuslt.append(binaryList.get(0) > binaryList.get(1) ? "0" : "1");
        }
        return reuslt.toString();
    }

    private static String getEpsilon(Map<Integer, List<Integer>> dataMap) {
        StringBuilder reuslt = new StringBuilder();
        for (Integer key : dataMap.keySet()) {
            List<Integer> binaryList = dataMap.get(key);
            reuslt.append(binaryList.get(0) < binaryList.get(1) ? "0" : "1");
        }
        return reuslt.toString();
    }

}
