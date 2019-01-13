import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset. */
    static Palindrome palindrome = new Palindrome();

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

    @Test
    public void testIsPalindrome2() {
        assertTrue(palindrome.isPalindrome2("A"));
        assertTrue(palindrome.isPalindrome2("BBB"));
        assertTrue(palindrome.isPalindrome2("CCCaCCC"));
        assertTrue(palindrome.isPalindrome2(""));


        assertFalse(palindrome.isPalindrome2("Aa"));
        assertFalse(palindrome.isPalindrome2("aaaaab"));
    }
}
