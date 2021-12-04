package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
    private static List<Integer> bingoNumbers = new ArrayList<>();
    private static List<Board> boardsList = new ArrayList<>();


    public static void main(String[] args) {

        String className = new Throwable().getStackTrace()[0].getClassName().split("\\.")[0];
        String fileName = "data/input" + className + ".txt";
        String fileNameTest = "data/input" + className + "TEST.txt";

        getDataFromFile(fileName);
        //getDataFromFile(fileNameTest);

        System.out.println(addBingoValues());
        System.out.println(addBingoValuesForLast());

    }

    private static void getDataFromFile(String fileLocation) {
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            int lineNumber = 0;
            List<Integer> listForBoardCreation = new ArrayList<>();
            while (myReader.hasNextLine()) {
                if (lineNumber == 0) {
                    String[] bingoValues = myReader.nextLine().split(",");
                    for (String bingoValue : bingoValues) {
                        bingoNumbers.add(Integer.parseInt(bingoValue));
                    }
                } else {
                    String currentLine = myReader.nextLine().trim().replaceAll(" +", " ");
                    if (lineNumber != 1) {
                        if (currentLine.isEmpty()) {
                            boardsList.add(new Board(listForBoardCreation));
                            listForBoardCreation = new ArrayList<>();
                        } else {
                            String[] bingoValues = currentLine.split(" ");
                            for (String bingoValue : bingoValues) {
                                listForBoardCreation.add(Integer.parseInt(bingoValue));
                            }
                        }
                    }
                }
                lineNumber++;
            }
            boardsList.add(new Board(listForBoardCreation));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int addBingoValues() {
        for (Integer bingoCurrentNumber : bingoNumbers) {
            for (Board currentBoard : boardsList) {
                currentBoard.checkABoardForNumber(bingoCurrentNumber);
                if (currentBoard.isBingo()) {
                    return currentBoard.sumOfUnmarkedNumbers() * bingoCurrentNumber;
                }
            }
        }
        return 0;
    }

    private static int addBingoValuesForLast() {
        List<Board> boardsThatWon = new ArrayList<Board>();
        int lastNumberThatWon = 0;
        for (Integer bingoCurrentNumber : bingoNumbers) {
            for (Board currentBoard : boardsList) {
                currentBoard.checkABoardForNumber(bingoCurrentNumber);
                if (currentBoard.isBingo()) {
                    if (!boardsThatWon.contains(currentBoard)) {
                        boardsThatWon.add(currentBoard);
                        lastNumberThatWon = bingoCurrentNumber;
                    }
                }
                if (boardsThatWon.size() == boardsList.size()) {
                    return currentBoard.sumOfUnmarkedNumbers() * bingoCurrentNumber;
                }
            }
        }
        return boardsThatWon.get(boardsThatWon.size() - 1).sumOfUnmarkedNumbers() * lastNumberThatWon;
    }

}
