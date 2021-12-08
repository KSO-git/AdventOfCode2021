package Day8;

public class Display {
    private final static int LENGTH_OF_ONE = 2;
    private final static int LENGTH_OF_FOUR = 4;
    private final static int LENGTH_OF_SEVEN = 3;
    private final static int LENGTH_OF_EIGHT = 7;
    boolean laneAActive = false;
    boolean laneBActive = false;
    boolean laneCActive = false;
    boolean laneDActive = false;
    boolean laneEActive = false;
    boolean laneFActive = false;
    boolean laneGActive = false;
    int numberOfCharackters = 0;


    public Display(Digit digit, String displayed) {
        char[] letters = displayed.toCharArray();
        for (char charEntry : letters) {
            markLaneBasedOnDigitPattern(digit, charEntry+"");
        }
        this.numberOfCharackters = displayed.length();
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

    public int getNumberFromReading() {
        int result = 0;
        switch (this.numberOfCharackters){
            case 2:
                result = 1;
                break;
            case 3:
                result = 7;
                break;
            case 4:
                result = 4;
                break;
            case 7:
                result = 8;
                break;
            case 5:
                if(this.laneBActive){
                    result = 5;
                } else if(this.laneEActive){
                    result = 2;
                }else {
                    result = 3;
                }
                break;
            case 6:
                if(!this.laneDActive){
                    result = 0;
                } else if(this.laneEActive){
                    result = 6;
                }else {
                    result = 9;
                }
                break;
        }

        return result;
    }

    private void markLaneBasedOnDigitPattern(Digit digit, String c) {
        if (digit.laneAActive.contains(c)) {
            this.laneAActive = true;
        } else if (digit.laneBActive.contains(c)) {
            this.laneBActive = true;
        } else if (digit.laneCActive.contains(c)) {
            this.laneCActive = true;
        } else if (digit.laneDActive.contains(c)) {
            this.laneDActive = true;
        } else if (digit.laneEActive.contains(c)) {
            this.laneEActive = true;
        } else if (digit.laneFActive.contains(c)) {
            this.laneFActive = true;
        } else if (digit.laneGActive.contains(c)) {
            this.laneGActive = true;
        }
    }
}
