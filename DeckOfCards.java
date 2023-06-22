import java.io.FileNotFoundException;
import java.util.*;

public class DeckOfCards {

    // Stack of integers for the original pile
    private final Stack<Integer> originalPile = new Stack<Integer>();

    // Stack of integers for the discard pile
    private final Stack<Integer> discardPile = new Stack<Integer>(); // Starts empty

    // Creates a stack of integers that represent a card from 1 to 51
    public DeckOfCards() {
        Cards deck = new Cards();
        int[] array = deck.getArray();
        for (int i = 0; i < 51; i++) {
            originalPile.push(array[i]);
        }
    }

    // Checks if original pile stack is empty
    public boolean isOriginalEmpty() {
        return originalPile.isEmpty();
    }

    // Checks if discard pile stack is empty
    public boolean isDiscardPileEmpty() {
        return discardPile.isEmpty();
    }

    // Draws a card from original pile
    public int drawFromPile() {
        if (!isOriginalEmpty()) {
            int x = originalPile.pop();
            return x;
        }
        else {
            return 0;
        }
    }

    // Adds a card to the discard pile
    public void addToDiscard(int n) {
        discardPile.push(n);
    }

    // Draws a card from the discard pile
    public int drawFromDiscardPile() {
        if (!isDiscardPileEmpty()) {
            return discardPile.pop();
        }
        else {
            System.out.println("Discard Pile is empty!");
            return 0;
        }
    }

    // Prints the card at the top of the discard pile and displays it on screen
    public String topOfDiscard(TransportationCards tc, CountryCards cc) {
        String name = "";
        String color = "";
        StringBuilder string = new StringBuilder();
        if (!discardPile.isEmpty()) {
            int oldCard = discardPile.peek();
            if (oldCard < 27 && oldCard != 0) {
                name = cc.getName(oldCard);
                color = cc.getColor(oldCard);
                // cc.displayCard(oldCard);
            }

            else if (oldCard >= 27 && oldCard < 52) {
                name = tc.getType(oldCard);
                color = tc.getColor(oldCard);
               // tc.displayCard(oldCard);
            }
        }
        else {
            name = " ";
            color = " ";
        }
        string.append(name + "(" + color + ")");
        return string.toString();
    }


    // Tests methods
    public static void main(String[] args) throws FileNotFoundException {

        TransportationCards tc = new TransportationCards();
        CountryCards cc = new CountryCards();
        
        DeckOfCards game = new DeckOfCards();
        int x = game.drawFromPile();

        System.out.println(x);

        /*
        if (cc.isCountry(x)) {
            cc.displayCard(x);
        }
        else {
            tc.displayCard(x);
        }
         */

        // add drawn card to discard pile
        game.addToDiscard(x);
        System.out.println(game.topOfDiscard(tc, cc));

        int y = game.drawFromDiscardPile();
        System.out.println(y);

        System.out.println(game.drawFromDiscardPile());

        System.out.println(game.isDiscardPileEmpty());
        System.out.println(game.isOriginalEmpty());

    }
}
