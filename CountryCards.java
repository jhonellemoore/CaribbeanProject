public class CountryCards {

    // Array of country card objects
    private CountryCard[] countries;

    // Creates a private country card object
    private class CountryCard {
        private String name;
        private String color;
        private String[] neighbors;
        private String fileName;

    }

    // Populates the countrycards array using the specified text file
    public CountryCards() {
        In countryFile = new In("CaribCountries/Countries");
        int n = countryFile.readInt();
        countries = new CountryCard[n];

        // Populate countries array with info for each country card
        for (int i = 0; i < n; i++) {  // Each index represents a countrycard
            countries[i] = new CountryCard();
            countries[i].name = countryFile.readString();
            countries[i].color = countryFile.readString();
            countries[i].fileName = countryFile.readString();
            int numberOfNeighbors = countryFile.readInt();
            countries[i].neighbors = new String[numberOfNeighbors];

            for (int j = 0; j < numberOfNeighbors; j++) {
                countries[i].neighbors[j] = countryFile.readString();
            }
        }
    }

    // Checks if (n-1) corresponds to a country card
    public boolean isCountry(int n) {
        return (n - 1) >= 0 && ((n - 1) < countries.length);
    }

    // Displays a picture of the country card that is at index (n-1)
    public void displayCard(int n) {
        if (isCountry(n))
            StdDraw.picture(.2, .35, countries[n - 1].fileName, .3, .35);
    }

    // Returns the name of the country at index (n-1)
    public String getName(int n) {
        return countries[n - 1].name;
    }

    // Returns color of the country at index (n-1)
    public String getColor(int n) {
        return countries[n - 1].color;
    }

    // Returns neighbors of country at index (n-1)
    public String[] getNeighbors(int n) {
        return countries[n - 1].neighbors;
    }

    // Returns file name of country at index (n - 1)
    public String getFile(int n) {
        return countries[n - 1].fileName;
    }

    // Returns string representation of the country card
    public String toString(int n) {
        StringBuilder sentence = new StringBuilder();
        sentence.append(getName(n) + "(" + getColor(n) + ")" + " with neighbors");

        // Prints in the form CountryName with neighbors ______, ______,... and ________
        for (int i = 0; i < getNeighbors(n).length; i++) {
            if (i != getNeighbors(n).length - 1) // If not last element
                sentence.append(" " + getNeighbors(n)[i] + ",");
            else if (getNeighbors(n).length != 1) {
                sentence.append(" and " + getNeighbors(n)[i] + ".");
            }
            else {
                sentence.append(" " + getNeighbors(n)[i] + ".");
            }
        }
        return sentence.toString();
    }

    // Tests methods
    public static void main(String[] args) {
        CountryCards Test = new CountryCards();
        StdDraw.picture(.5, .5, Test.countries[1].fileName, .4, .4);
        StdOut.println(Test.getName(3));
        StdOut.println(Test.getColor(1));
        StdOut.println(Test.getNeighbors(2)[1]);
        StdOut.println(Test.getFile(6));
        StdOut.println(Test.toString(6));

        Test.displayCard(6);
        Test.isCountry(6);
    }
}
