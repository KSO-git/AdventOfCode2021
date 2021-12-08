package Day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Digit {
    private final static int LENGTH_OF_ONE = 2;
    private final static int LENGTH_OF_FOUR = 4;
    private final static int LENGTH_OF_SEVEN = 3;
    private final static int LENGTH_OF_EIGHT = 7;
    List<String> laneAActive = new ArrayList<>();
    List<String> laneBActive = new ArrayList<>();
    List<String> laneCActive = new ArrayList<>();
    List<String> laneDActive = new ArrayList<>();
    List<String> laneEActive = new ArrayList<>();
    List<String> laneFActive = new ArrayList<>();
    List<String> laneGActive = new ArrayList<>();


    public Digit() {
    }

    public int isOneFourSevenOrEightPresentInOutput(String output) {
        int result = 0;
        String[] outputLights = output.trim().split(" ");
        for (String entry : outputLights) {
            int numberOfLightsLit = entry.length();
            if (numberOfLightsLit == LENGTH_OF_ONE || numberOfLightsLit == LENGTH_OF_FOUR
                    || numberOfLightsLit == LENGTH_OF_SEVEN || numberOfLightsLit == LENGTH_OF_EIGHT) {
                result++;
            }
        }
        return result;
    }

    public void buildpossible(String output) {
        List<String> sortedArray = Arrays.asList(output.split(" ")).stream()
                .filter(string -> !string.equals("|"))
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        List<String> currentlyAssigned = new ArrayList<>();
        for (String entry : sortedArray) {
            int numberOfLightsLit = entry.length();
            if (numberOfLightsLit == LENGTH_OF_ONE) {
                char[] letters = entry.toCharArray();
                for (char charEntry : letters) {
                    if (!currentlyAssigned.contains(charEntry + "")) {
                        laneCActive.add(charEntry + "");
                        laneFActive.add(charEntry + "");
                        currentlyAssigned.add(charEntry + "");
                    }
                }
            } else if (numberOfLightsLit == LENGTH_OF_SEVEN) {
                char[] letters = entry.toCharArray();
                for (char charEntry : letters) {
                    if (!currentlyAssigned.contains(charEntry + "")) {
                        if (!laneCActive.contains(charEntry + "") && laneCActive.isEmpty()) {
                            laneCActive.add(charEntry + "");
                        }
                        if (!laneFActive.contains(charEntry + "") && laneCActive.isEmpty()) {
                            laneFActive.add(charEntry + "");
                        }
                        laneAActive.add(charEntry + "");
                        currentlyAssigned.add(charEntry + "");
                    }
                }
            } else if (numberOfLightsLit == LENGTH_OF_FOUR) {
                char[] letters = entry.toCharArray();
                for (char charEntry : letters) {
                    if (!currentlyAssigned.contains(charEntry + "")) {
                        if (!laneCActive.contains(charEntry + "") && laneCActive.isEmpty()) {
                            laneCActive.add(charEntry + "");
                        }
                        if (!laneFActive.contains(charEntry + "") && laneCActive.isEmpty()) {
                            laneFActive.add(charEntry + "");
                        }
                        laneBActive.add(charEntry + "");
                        laneDActive.add(charEntry + "");
                        currentlyAssigned.add(charEntry + "");
                    }

                }
            }
            else if (numberOfLightsLit == 5) {
                char[] letters = entry.toCharArray();
                List<Character> listToAssign = new ArrayList<>();
                for (char charEntry : letters) {
                    if(laneFActive.contains(charEntry+"")){
                        laneCActive.remove(charEntry+"");
                        laneFActive.remove(laneCActive.get(0));
                    }
                    if (!currentlyAssigned.contains(charEntry + "")) {
                        listToAssign.add(charEntry);
                    }
                }
                if(listToAssign.size() == 1 && laneGActive.isEmpty()){
                    laneGActive.add(listToAssign.get(0)+"");
                    currentlyAssigned.add(listToAssign.get(0)+"");
                }
            }
            else if (numberOfLightsLit == 6) {
                char[] letters = entry.toCharArray();
                List<Character> listToAssign = new ArrayList<>();
                int numberOfBDsPresent = 0;
                String BDCurrentlypresent ="";
                for (char charEntry : letters) {
                    if(laneBActive.contains(charEntry + "") && laneBActive.size()==2){
                        numberOfBDsPresent++;
                        BDCurrentlypresent = charEntry + "";
                    }
                    if (!currentlyAssigned.contains(charEntry + "")) {
                        listToAssign.add(charEntry);
                    }
                }
                if(numberOfBDsPresent == 1){
                    laneDActive.remove(BDCurrentlypresent);
                    laneBActive.remove(laneDActive.get(0));
                }
                if(listToAssign.size() == 1 && laneEActive.isEmpty()){
                    laneEActive.add(listToAssign.get(0)+"");
                    currentlyAssigned.add(listToAssign.get(0)+"");
                }
            }

        }
    }
}
