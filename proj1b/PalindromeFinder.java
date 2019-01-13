import java.lang.reflect.Array;

/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {

        /**
         * Initializing code by creating multiple lists containing a specified number of palindromes
         * and offByN comparator,
         */
        OffByN[] offByN = new OffByN[26];
        ArrayDeque<String>[] ByNDeque = new ArrayDeque[26];
        for (int i = 0; i < 26; i += 1) {
            offByN[i] = new OffByN(i);
            ByNDeque[i] = new ArrayDeque<>();
        }
        /* */
        int minLength = 4;
        String maxLength = "";
        int maxLengthIndex = 0;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength) {
                for (int i = 0; i < 26; i += 1) {
                    if (palindrome.isPalindromeByN(word, offByN[i])) {
                        ByNDeque[i].addFirst(word);
                        if (word.length() > maxLength.length()) {
                            maxLength = word;
                            maxLengthIndex = i;
                        }
                        break;
                    }
                }
            }
        }

        /**
         * Finds the longest arrayDeque (in order to find N in which there are the most palindromes in English.
         */
        int maxSizeIndex = 0;
        for (int i = 0; i < 26; i += 1) {
            if (ByNDeque[i].size() > ByNDeque[maxSizeIndex].size()) {
                maxSizeIndex = i;
            }
        }

        /**
         * Prints the expected output.
         */
        System.out.println("For N = " + maxSizeIndex + " there are the most palindromes in English");
        System.out.println("The longest offByN palindrome is " + maxLength + " with N = " + maxLengthIndex);
    }
}
