package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day10 {

    private static List<Character> openBrackets = new ArrayList<>();
    private static List<Character> notMatchingBrackets = new ArrayList<>();
    private static List<Integer> openingChars = Arrays.asList(40, 60, 91, 123);
    private static final Map<Integer, Integer> pointMap = new HashMap<>();
    private static final Map<Integer, Integer> pointMapPart2 = new HashMap<>();
    private static final List<String> incompleatelines = new ArrayList<>();
    private static final List<Long> scores = new ArrayList<>();

    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

        getDataFromFile(fileName);
//        getDataFromFile(fileNameTest);

        fillPointMap();
        fillPointMapPart2();
        System.out.println(sumNOnMatchingBrackets());
        System.out.println(calcualteAllclosingBrackets());
        System.out.println("Middle Value is: " + findMiddleValue());


    }

    private static long sumNOnMatchingBrackets() {
        long sum = 0;
        for (Character entry : notMatchingBrackets) {
            sum += pointMap.get(entry + 0);
        }
        return sum;
    }

    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                boolean notMatchingBracketFound = false;
                for (char entry : inputLine.toCharArray()) {
                    if (!notMatchingBracketFound) {
                        if (openingChars.contains(entry + 0)) {
                            openBrackets.add(entry);
                        } else {
                            if (entry == 41) {
                                if (openBrackets.get(openBrackets.size() - 1) == 40) {
                                    openBrackets.remove(openBrackets.size() - 1);
                                } else {
                                    notMatchingBrackets.add(entry);
                                    notMatchingBracketFound = true;
                                }
                            } else {
                                if (openBrackets.get(openBrackets.size() - 1) == entry - 2) {
                                    openBrackets.remove(openBrackets.size() - 1);
                                } else {
                                    notMatchingBrackets.add(entry);
                                    notMatchingBracketFound = true;
                                }
                            }
                        }
                    }
                }
                if (!notMatchingBracketFound) {
                    incompleatelines.add(inputLine);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void fillPointMap() {
        pointMap.put(41, 3);
        pointMap.put(93, 57);
        pointMap.put(125, 1197);
        pointMap.put(62, 25137);
    }

    private static void fillPointMapPart2() {
        pointMapPart2.put(41, 1);
        pointMapPart2.put(93, 2);
        pointMapPart2.put(125, 3);
        pointMapPart2.put(62, 4);
    }

    private static String findAllClosings(String inputLine) {
        String result = "";
        List<Character> openBracketsLocal = new ArrayList<>();
        for (char entry : inputLine.toCharArray()) {
            if (openingChars.contains(entry + 0)) {
                openBracketsLocal.add(entry);
            } else {
                if (entry == 41) {
                    if (openBracketsLocal.get(openBracketsLocal.size() - 1) != 40) {
                        result += openBracketsLocal.get(openBracketsLocal.size() - 1) + 1;
                    }
                    openBracketsLocal.remove(openBracketsLocal.size() - 1);
                } else {
                    if (openBracketsLocal.get(openBracketsLocal.size() - 1) != entry - 2) {

                        result += openBracketsLocal.get(openBracketsLocal.size() - 1) + 2;
                    }
                    openBracketsLocal.remove(openBracketsLocal.size() - 1);
                }

            }
        }
        for (int i = openBracketsLocal.size() - 1; i >= 0; i--) {
            if (openBracketsLocal.get(i) == 40) {
                result += (char) (openBracketsLocal.get(i) + 1);
            } else {
                result += (char) (openBracketsLocal.get(i) + 2);
            }
        }
        return result;
    }

    private static long calcualteAllclosingBrackets() {
        long totalSum = 0;
        for (String entry : incompleatelines) {
            String closingBrackets = findAllClosings(entry);
            scores.add(calcualteSumForAllclosingBrackets(closingBrackets));
            totalSum += calcualteSumForAllclosingBrackets(closingBrackets);

        }
        return totalSum;
    }

    private static long calcualteSumForAllclosingBrackets(String input) {
        long sum = 0;
        for (Character entry : input.toCharArray()) {
            sum *= 5;
            sum += pointMapPart2.get(entry + 0);
        }
        return sum;
    }

    private static long findMiddleValue() {
        List<Long> localSortedMiddleValueList = scores.stream().sorted().collect(Collectors.toList());
        return localSortedMiddleValueList.get((localSortedMiddleValueList.size() / 2));
    }


}
