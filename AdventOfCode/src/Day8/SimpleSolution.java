package Day8;

import java.util.ArrayList;
import java.util.List;

public class SimpleSolution {
    private List<String> cfListPresent = new ArrayList<>();
    private List<String> bdListPresent = new ArrayList<>();

    public SimpleSolution(String input) {
        for(String entry : input.split(" ")) {
            if(!entry.equals("|")) {
                if (entry.length() == 2) {
                    for (char letter : entry.toCharArray()) {
                        this.cfListPresent.add(letter + "");
                        if (bdListPresent.size() > 2) {
                            bdListPresent.remove(letter + "");
                        }
                    }
                } else if (entry.length() == 4) {
                    if(this.bdListPresent.size() !=2) {
                    for (char letter : entry.toCharArray()) {
                            if (this.cfListPresent.isEmpty() || (!this.cfListPresent.contains(letter + ""))) {
                                this.bdListPresent.add(letter + "");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean areBothCFPresent(String input){
        int counterForPresent = 0;
        for(char letter : input.toCharArray()){
            if(this.cfListPresent.contains(letter+"")){
                counterForPresent++;
            }
        }
        return counterForPresent==2;
    }

    public boolean areBothBDPresent(String input){
        int counterForPresent = 0;
        for(char letter : input.toCharArray()){
            if(this.bdListPresent.contains(letter+"")){
                counterForPresent++;
            }
        }
        return counterForPresent==2;
    }

    public int calcualteDigitValue(String s){
        int result = 0;
        boolean cfBothPresent = areBothCFPresent(s);
        boolean bdBothPresent = areBothBDPresent(s);
        int inputSize = s.length();
        switch(inputSize){
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
                if(!cfBothPresent && !bdBothPresent){
                    result = 2;
                }else if(cfBothPresent){
                    result = 3;
                } else {
                    result = 5;
                }
                break;
            case 6:
                if(cfBothPresent && bdBothPresent){
                    result = 9;
                }else if(cfBothPresent){
                    result = 0;
                } else {
                    result = 6;
                }
                break;
        }
        return result;
    }
}
