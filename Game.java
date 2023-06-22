import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Game {

    // Constant separating country and transportation cards. Country cards
    // are from 0-26 and transportation cards are from 0-27
    private static final int C_LENGTH = 27;

    // Reveals the card chosen by user
    private static void revealCard(CountryCards cc, TransportationCards tc,
                                   int num) {
        String start = "Your chosen card is ";
        if (num < C_LENGTH) {
            System.out.println(start + cc.toString(num));
        }
        else {
            System.out.println(start + tc.toString(num));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        // Creates card objects for the game
        CountryCards cc = new CountryCards();
        TransportationCards tc = new TransportationCards();
        DeckOfCards deck = new DeckOfCards();
        Cardholder holder = new Cardholder();

        // Referenced https://introcs.cs.princeton.edu/java/11cheatsheet/#StdOut
        // to remind ourselves about the StdDraw library
        /*
        StdDraw.setCanvasSize(800, 600);
        StdDraw.picture(.5, .55, "IMAGE.png", .8, .8);
*/

        // Prompts the user to enter p
        System.out.println("Please enter p to see the menu.");
        Scanner reader = new Scanner (System.in);
        // Pauses the game until 'p' is entered
        while (reader.hasNext()) {
            String read = reader.nextLine();
            if (read.charAt(0) == ('p')){
                break;
            }

        }

        // Shows the menu
        // StdDraw.clear();
        // StdDraw.picture(.5, .55, "menuRules.png", .8, .8);

        // Prompts the user to start the game
        System.out.println("Please enter p to start the game.");

        // We used
        // https://www.cs.princeton.edu/courses/archive/spr22/cos126/static/lectures/CS.4.InOut.pdf
        // to remind ourselves about how to use Standard Input and Output

        while (reader.hasNext()) {
            String read = reader.nextLine();
            if (read.charAt(0) == ('p')){
                break;
            }
        }

        /*
        while (!StdIn.isEmpty()) {
            if (StdIn.readChar() == 'p') {
                break;
            }
        }
*/

        // Shows game map
       //  StdDraw.clear();
       // StdDraw.picture(.5, .55, "CaribbeanMap.png", .8, .8);


        // *************** GAME LOOP ******************************

        // Game loop continues until checkValid returns true i.e. all the rules
        // are obeyed in the cardholder
        while (!holder.checkValid(tc, cc)) {
            // Waits for the right key to be pressed
            int chosenNext = 0;
            String key = "a";
            System.out.println("Please choose a card from the deck (p) or discard pile (x).");
            while (reader.hasNext()) {
                key = reader.next();
                if ((key.equals("x") || key.equals("p"))){
                    break;
                }
            }

            // FIRST FEATURE IN ACTION: ALLOWS USER TO CHOOSE CARD FROM DECK OR
            // DISCARD PILE

            // Draws from original pile if 'p' is entered
            if (key.equals("p")) {
                chosenNext = deck.drawFromPile();
                if (chosenNext != 0) { // The original pile is not empty
                    revealCard(cc, tc, chosenNext);
                }

                else { // The original pile is empty
                    System.out.println("The deck is empty. Please choose from discard pile!");

                    // Wait for them to choose from discard pile
                    while (reader.hasNext()) {
                        String nextKey = reader.next();
                        if (nextKey.equals("x")) {
                            break;
                        }
                    }
                    chosenNext = deck.drawFromDiscardPile();
                    revealCard(cc, tc, chosenNext); // Reveals chosen card
                }
            }

            // Draws from discard pile if 'x' is entered
            else if (key.equals("x")) {
                chosenNext = deck.drawFromDiscardPile();
                if (chosenNext != 0) { // If it's not empty
                    revealCard(cc, tc, chosenNext);
                    /*
                    if (deck.isDiscardPileEmpty()) {
                        // if discard pile is empty, redraw map
                        StdDraw.clear();
                        StdDraw.picture(.5, .55, "CaribbeanMap.png", .9, .9);
                    }
                     */
                }

                else {
                    System.out.println("The discard pile is empty. Please choose from the deck!");
                    while (reader.hasNext()) {
                        String nextKey = reader.next();
                        if (nextKey.equals("p")) {
                            break;
                        }
                    }

                    chosenNext = deck.drawFromPile();
                    revealCard(cc, tc, chosenNext);
                }
            }

            // SECOND FEATURE IN ACTION: ALLOWS USER TO PLACE CHOSEN CARD
            // IN CARDHOLDER

            System.out.println("Please type 0-10 to choose a position in the cardholder "
                                   + "to place your card or type"
                                   + " a multiple-digit number to discard your card");

            while (reader.hasNext()) {
                String input = reader.next();
                char c = input.charAt(0);
                if (Character.isDigit(c)) { // Makes sure that a digit is entered
                    // Got help from Preceptor Donna Gabai
                    // Code also cited from https://www.tutorialspoint.com/java/character_isdigit.htm
                    int position = Integer.parseInt(input);
                    if (position >= 0 && position <= 10) { // If chosen position is in cardholder
                        int original = holder
                                .placeInCardholder(chosenNext, position); // Store old card

                        if (original != 0) { // If the position was not empty
                            // place the previous card in discard pile
                            deck.addToDiscard(original);
                        }
                    }
                    else { // if player chose to discard the card
                        deck.addToDiscard(chosenNext);
                    }
                    break;
                }
            }

            // Shows the player what their cardholder looks like
            System.out.println("Your cardholder looks like: \n" + holder.toString(tc, cc));

            // Tells player the card that is on top of discard pile
            System.out.println("The card at the top of your discard pile is "
                                   + deck.topOfDiscard(tc, cc));
        }


        // If checkValid returns true, then player wins the game
        System.out.println("You won 10 Days in the Caribbean!");
        // System.out.picture(.5, .55, "you won the game.jpeg", .8, .8);
        // StdDraw.pause(5000);
        System.exit(0); // End of game
    }


}


