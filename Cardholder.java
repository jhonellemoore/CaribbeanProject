import java.io.FileNotFoundException;

public class Cardholder {

    // Size of cardholder array
    public static final int LENGTH = 11;

    // Cardholder array
    private final int[] cardHolder;

    // Creates a cardholder object which is an array of size 11
    public Cardholder() {
        cardHolder = new int[LENGTH];
    }

    // Returns true if cardholder is full
    public boolean cardholderFull() {
        for (int i = 0; i < LENGTH; i++) {
            if (cardHolder[i] == 0) {
                return false;
            }
        }
        return true;
    }

    // Places a card (n) in the cardholder at position x
    // and returns the index of the previous card
    // If position is empty, it returns zero
    public int placeInCardholder(int n, int x) { //
        int store = cardHolder[x];
        cardHolder[x] = n;
        return store;
    }

    // Checks if there's a card at position x in the cardholder
    public boolean hasCard(int x) {
        return (cardHolder[x] != 0);
    }

    // Checks if card is an airplane card and is between two countries of the same color
    private boolean canFly(TransportationCards tc, CountryCards cc, int pos) {
        int n = cardHolder[pos];
        if (!tc.isAirplane(n)) {
            return false;
        }
        String planeColor = tc.getColor(n);
        int left = cardHolder[pos - 1]; // Card to the left of card
        if (!cc.isCountry(left)) {
            return false;
        }
        String leftColor = cc.getColor(left);
        if (!leftColor.equals(planeColor)) {
            return false;
        }

        int right = cardHolder[pos + 1]; // Card to the right of card
        if (!cc.isCountry(right)) {
            return false;
        }
        String rightColor = cc.getColor(right);
        if (!rightColor.equals(planeColor)) {
            return false;
        }
        return true;
    }


    // Returns string representation of the cardholder with each position
    // on a new line
    public String toString(TransportationCards tc, CountryCards cc) {
        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            sentence.append("Cardholder " + i + ": ");
            if (cc.isCountry(cardHolder[i])) {
                sentence.append(cc.getName(cardHolder[i]) + " (" +
                                        cc.getColor(cardHolder[i]) + ")\n");

            }

            else {
                sentence.append(tc.getType(cardHolder[i]) + " (" +
                                        tc.getColor(cardHolder[i]) + ")\n");
            }

        }
        return sentence.toString();
    }


    // Checks if card is a boat card and is between two adjacent countries
    private boolean canCross(TransportationCards tc, CountryCards cc, int pos) {
        int n = cardHolder[pos];
        if (!tc.isBoat(n)) {
            return false;
        }
        int left = cardHolder[pos - 1];
        if (!cc.isCountry(left)) {
            return false;
        }
        String[] neighbors = cc.getNeighbors(left); // Gets neighbors of country
        int right = cardHolder[pos + 1];
        if (!cc.isCountry(right)) {
            return false;
        }

        String rightName = cc.getName(right);
        // Checks if boat is between neighbors
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i].equals(rightName)) {
                return true;
            }
        }

        return false;
    }


    // Checks if the cards in the cardholder start and end with a country
    // and obey the game rules
    public boolean checkValid(TransportationCards tc, CountryCards cc) {
        if (!cardholderFull()) { // Person wins when cardholder is full
            return false;
        }

        // Ensures that transportation cards are at odd numbered positions
        for (int i = 1; i < 10; i += 2) {
            boolean good = (canCross(tc, cc, i));
            if (!(good || (canFly(tc, cc, i)))) { // if it passes neither
                return false;
            }
        }

        return true;
    }

    // Main method tests the above functions
    public static void main(String[] args) throws FileNotFoundException {
        Cardholder cardholder = new Cardholder();
        TransportationCards tc = new TransportationCards();
        CountryCards cc = new CountryCards();

        // PR Plane Cuba Boat Jamaica Boat Haiti Plane Trinidad Boat
        cardholder.placeInCardholder(8, 0); // Puerto Rico
        cardholder.placeInCardholder(3, 2); // Cuba
        cardholder.placeInCardholder(46, 1); // Plane
        cardholder.placeInCardholder(2, 4); // Jamaica
        cardholder.placeInCardholder(32, 3); // Boat
        cardholder.placeInCardholder(35, 5); // Boat
        cardholder.placeInCardholder(4, 6); // Haiti
        cardholder.placeInCardholder(23, 8); // Trinidad
        cardholder.placeInCardholder(51, 7); // Plane
        cardholder.placeInCardholder(29, 9); // Boat
        System.out.println(cardholder.checkValid(tc, cc));
        cardholder.placeInCardholder(22, 10); // Barbados
        System.out.println(cardholder.checkValid(tc, cc));
        System.out.println(cardholder.toString(tc, cc));

        System.out.println(cardholder.hasCard(1));
        System.out.println(cardholder.canCross(tc, cc, 1));
        System.out.println(cardholder.canFly(tc, cc, 1));

    }
}
