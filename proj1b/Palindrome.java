public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        for (int i = 0; i < length / 2; i += 1) {
            if (word.charAt(i) != word.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome2Helper(Deque<Character> deque) {
        if (deque.size() < 2) {
            return true;
        }
        if (deque.removeFirst() == deque.removeLast()) {
            return isPalindrome2Helper(deque);
        } else {
            return false;
        }
    }

    public boolean isPalindrome2(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome2Helper(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        for (int i = 0; i < length / 2; i += 1) {
            if (!cc.equalChars(word.charAt(i), word.charAt(length - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindromeByOne2Helper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() < 2) {
            return true;
        }
        if (cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return isPalindromeByOne2Helper(deque, cc);
        } else {
            return false;
        }
    }

    private boolean isPalindromeByOne2(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeByOne2Helper(deque, cc);
    }

    private boolean isPalindromeByN(String word, int n) {
        return this.isPalindromeByN(word, new OffByN(n));
    }

    private boolean isPalindromeByN(String word, OffByN offByN) {
        int length = word.length();
        for (int i = 0; i < length / 2; i += 1) {
            if (!offByN.equalChars(word.charAt(i), word.charAt(length - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
