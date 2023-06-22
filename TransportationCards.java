import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TransportationCards {

    // Array of transportation card objects
    private TransportationCard[] transport;

    // Creates a prive transportation card object
    private class TransportationCard {
        private String type;
        private String color;
        private String fileName;

    }

    // Constructor for transportationcards
    public TransportationCards() throws FileNotFoundException {
        File transportationFile = new File("/Users/jmoore/CaribbeanProject/Transportation/Transport");
        Scanner transportationList = new Scanner(transportationFile);
        int m = transportationList.nextInt();
        int n = m + 26;
        transport = new TransportationCard[n];

        for (int i = 26; i < n; i++) {
            transport[i] = new TransportationCard();
            transport[i].type = transportationList.next();
            transport[i].color = transportationList.next();
            transport[i].fileName = transportationList.next();
        }
    }

    // Returns type of card (Boat or Airplane)
    public String getType(int x) {
        if (x == 0) {
            return " ";
        }
        else {
            return transport[x - 1].type;
        }
    }

    // Returns true if card is a boat
    public boolean isBoat(int x) {
        return (transport[x - 1].type.equals("Boat"));
    }

    // Displays the card to the user
    /* public void displayCard(int x) {
        if (isBoat(x) || (isAirplane(x)))
            StdDraw.picture(.2, .35, transport[x - 1].fileName, .3, .35);
    }
     */

    // Returns true if card is an airplane
    public boolean isAirplane(int x) {
        return (transport[x - 1].type.equals("Airplane"));
    }

    // Returns the color of the card
    public String getColor(int x) {
        if (x == 0) {
            return " ";
        }
        else {
            return transport[x - 1].color;
        }
    }

    // Returns file name for card
    public String getFile(int x) {
        if (x == 0) {
            return " ";
        }
        else {
            return transport[x - 1].fileName;
        }
    }

    // Returns string representation of the transportation card
    public String toString(int n) {
        StringBuilder sentence = new StringBuilder();
        sentence.append(getType(n) + "(" + getColor(n) + ")");
        return sentence.toString();
    }


    // Tests methods created
    public static void main(String[] args) throws FileNotFoundException {
        TransportationCards test = new TransportationCards();
        // StdDraw.picture(.5, .5, test.transport[28].fileName, .4, .4);
        System.out.println(test.getColor(43));
        System.out.println(test.getType(27));
        System.out.println(test.isBoat(27));
        System.out.println(test.isAirplane(31));
        System.out.println(test.getFile(31));

        System.out.println(test.toString(27));

        for (int i = 27; i < 52; i++) {
            System.out.println(test.isAirplane(i) + " " + test.getColor(i) + " " + i);
        }


        // test.displayCard(31);
    }
}
