package Day4;

import java.util.List;

public class Board {
    private int[][] numberBaord = new int[5][5];
    private int[][] markedFileds = new int[5][5];

    public Board() {
    }

    public Board(List<Integer> entries) {
        int x = 0;
        int y = 0;
        for(Integer number : entries){
            numberBaord[x][y] = number;
            x++;
            if(x > 4){
                y++;
                x=0;
            }
        }
    }

    public void checkABoardForNumber(int number){
        for(int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++) {
                if (numberBaord[x][y] == number) {
                    markedFileds[x][y] = 1;
                }
            }
        }
    }

    public boolean isBingo(){
        for(int y = 0; y < 5; y++){
            int markedNumber = 0;
            for (int x = 0; x < 5; x++) {
                if (markedFileds[x][y] == 1) {
                    markedNumber++;
                }
            }
            if(markedNumber == 5){
                return true;
            }
        }
        for(int x = 0; x < 5; x++){
            int markedNumber = 0;
            for (int y = 0; y < 5; y++) {
                if (markedFileds[x][y] == 1) {
                    markedNumber++;
                }
            }
            if(markedNumber == 5){
                return true;
            }
        }
        return false;
    }

    public int sumOfUnmarkedNumbers(){
        int totalSum = 0;
        for(int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++) {
                if (markedFileds[x][y] != 1) {
                    totalSum += numberBaord[x][y];
                }
            }

        }
        return totalSum;
    }
}
