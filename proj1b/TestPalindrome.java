import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset. */
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome("BBB"));
        assertTrue(palindrome.isPalindrome("CCCaCCC"));
        assertTrue(palindrome.isPalindrome(""));


        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("aaaaab"));
    }

    /*@Test
    public void testIsPalindrome2() {
        assertTrue(palindrome.isPalindrome2("A"));
        assertTrue(palindrome.isPalindrome2("BBB"));
        assertTrue(palindrome.isPalindrome2("CCCaCCC"));
        assertTrue(palindrome.isPalindrome2(""));


        assertFalse(palindrome.isPalindrome2("Aa"));
        assertFalse(palindrome.isPalindrome2("aaaaab"));
    }*/

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("atwavub", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));

        assertFalse(palindrome.isPalindrome("Ab", offByOne));
        assertFalse(palindrome.isPalindrome("abc", offByOne));
    }

    /*@Test
    public void testIsPalindromeByOne2() {
        assertTrue(palindrome.isPalindromeByOne2("a", offByOne));
        assertTrue(palindrome.isPalindromeByOne2("flake", offByOne));
        assertTrue(palindrome.isPalindromeByOne2("atwavub", offByOne));
        assertTrue(palindrome.isPalindromeByOne2("", offByOne));

        assertFalse(palindrome.isPalindromeByOne2("Ab", offByOne));
        assertFalse(palindrome.isPalindromeByOne2("abc", offByOne));
    }

    @Test
    public void testIsPalindromeByN() {
        assertTrue(palindrome.isPalindromeByN("az", 25));
        assertTrue(palindrome.isPalindromeByN("abc", 2));
        assertTrue(palindrome.isPalindromeByN("flaqa", 5));
        assertTrue(palindrome.isPalindromeByN("", 100));

        assertFalse(palindrome.isPalindromeByN("flapa", 5));
        assertFalse(palindrome.isPalindromeByN("aba", 2));
    }*/
}
