import java.util.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    // Integer array to mix transportation cards and country cards
    private final int[] array = new int [51];

    // Constructor shuffles numbers from 1 to 51 and returns new shuffled array
    public Cards() {
        ArrayList <Integer> list = new ArrayList<>();

        for (int i = 1; i <= 51; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
    }

    // Returns the shuffled array
    public int[] getArray() {
        return array;
    }

    // Main to test card deck
    public static void main(String[] args) {
        Cards deck = new Cards();
        for (int i = 0; i < 51; i++) {
            System.out.println(deck.getArray()[i]);
        }
    }
}
