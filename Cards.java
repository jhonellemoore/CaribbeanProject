public class Cards {

    // Integer array to mix transportation cards and country cards
    private int[] array;

    // Constructor shuffles numbers from 1 - 51 and returns new shuffled array
    public Cards() {
        array = new int[51];

        for (int i = 1; i <= 51; i++) {
            array[i - 1] = i;
        }
        StdRandom.shuffle(array);
    }

    // Returns the shuffled array
    public int[] getArray() {
        return array;
    }

    // Tests the methods created
    public static void main(String[] args) {
        Cards deck = new Cards();

        for (int i = 0; i < 51; i++) {
            StdOut.println(deck.getArray()[i]);
        }
    }
}
