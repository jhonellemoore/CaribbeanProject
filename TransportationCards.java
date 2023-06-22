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
    public TransportationCards() {
        In transportationFile = new In("Transportation/Transport");
        int m = transportationFile.readInt();
        int n = m + 26;
        transport = new TransportationCard[n];

        for (int i = 26; i < n; i++) {
            transport[i] = new TransportationCard();
            transport[i].type = transportationFile.readString();
            transport[i].color = transportationFile.readString();
            transport[i].fileName = transportationFile.readString();
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
    public void displayCard(int x) {
        if (isBoat(x) || (isAirplane(x)))
            StdDraw.picture(.2, .35, transport[x - 1].fileName, .3, .35);
    }

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
    public static void main(String[] args) {
        TransportationCards test = new TransportationCards();
        StdDraw.picture(.5, .5, test.transport[28].fileName, .4, .4);
        StdOut.println(test.getColor(43));
        StdOut.println(test.getType(27));
        StdOut.println(test.isBoat(27));
        StdOut.println(test.isAirplane(31));
        StdOut.println(test.getFile(31));

        StdOut.println(test.toString(27));

        for (int i = 27; i < 52; i++) {
            StdOut.println(test.isAirplane(i) + " " + test.getColor(i) + " " + i);
        }


        test.displayCard(31);
    }
}
