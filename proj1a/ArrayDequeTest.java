import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ArrayDequeTest {

    @Test
    public void addFirst() {
        ArrayDeque<String> test = new ArrayDeque<>("this");
        test.addFirst("is");
        String[] expected = {null, null, "is", "this", null, null, null, null};
        assertArrayEquals(expected, test.getItems());
    }

    @Test
    public void addFirst1() {
        ArrayDeque<String> test = new ArrayDeque<>("much");
        test.addFirst("very");
        test.addFirst("Vietnam");
        test.addFirst("love");
        test.addFirst("I");
        String[] expected = {"love", "Vietnam", "very", "much", null, null, null, "I"};
        assertArrayEquals(expected, test.getItems());
    }

    @Test
    public void addLast() {
        ArrayDeque<String> test = new ArrayDeque<>("this");
        test.addLast("is");
        String[] expected = {null, null, null, "this", "is", null, null, null};
        assertArrayEquals(expected, test.getItems());
    }

    @Test
    public void addLast1() {
        ArrayDeque<String> test = new ArrayDeque<>("much");
        test.addLast("very");
        test.addLast("Vietnam");
        test.addLast("love");
        test.addLast("I");
        String[] expected = {null, null, null, "much", "very", "Vietnam", "love", "I"};
        assertArrayEquals(expected, test.getItems());
    }

    @Test
    public void isEmpty() {
        ArrayDeque<String> test = new ArrayDeque<>();
        assertTrue(test.isEmpty());
    }

    @Test
    public void isEmpty1() {
        ArrayDeque<String> test = new ArrayDeque<>("Vietnam");
        assertFalse(test.isEmpty());
    }

    @Test
    public void size() {
        ArrayDeque<String> test = new ArrayDeque<>("this");
        test.addLast("is");
        int expected = 2;
        assertEquals(expected, test.size());
    }

    @Test
    public void size1() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addFirst("much");
        test.addFirst("very");
        test.addFirst("Vietnam");
        test.addFirst("love");
        test.addFirst("I");
        int expected = 5;
        assertEquals(expected, test.size());
    }

    @Test
    public void printDeque() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addFirst("much");
        test.addFirst("very");
        test.addFirst("Vietnam");
        test.addFirst("love");
        test.addFirst("I");
        String expected = "I love Vietnam very much \n";
        test.printDeque();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void printDeque1() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addLast("I");
        test.addLast("love");
        test.addLast("Vietnam");
        test.addLast("very");
        test.addLast("much");
        String expected = "I love Vietnam very much \n";
        test.printDeque();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void removeFirst() {
        ArrayDeque<String> test = new ArrayDeque<>("test");
        assertEquals("test", test.removeFirst());
        assertTrue(test.isEmpty());
    }

    @Test
    public void removeFirst1() {
        ArrayDeque<String> test = new ArrayDeque<>("much");
        test.addFirst("very");
        test.addFirst("Vietnam");
        test.addFirst("love");
        test.addFirst("I");

        assertEquals("I", test.removeFirst());

        test.printDeque();
        test.addFirst("you");

        String expected = "love Vietnam very much \n";
        String[] expected2 = {"love", "Vietnam", "very", "much", null, null, null, "you"};

        assertEquals(expected, outContent.toString());
        assertArrayEquals(expected2, test.getItems());
    }

    @Test
    public void removeLast() {
        ArrayDeque<String> test = new ArrayDeque<>("test");
        assertEquals("test", test.removeLast());
        assertTrue(test.isEmpty());
    }

    @Test
    public void removeLast1() {
        ArrayDeque<String> test = new ArrayDeque<>("much");
        test.addFirst("very");
        test.addFirst("Vietnam");
        test.addFirst("love");
        test.addFirst("I");

        assertEquals("much", test.removeLast());

        test.addLast("very much");
        test.printDeque();

        String expected = "I love Vietnam very very much \n";
        String[] expected2 = {"love", "Vietnam", "very", "very much", null, null, null, "I"};

        assertEquals(expected, outContent.toString());
        assertArrayEquals(expected2, test.getItems());
    }

    @Test
    public void get() {
        ArrayDeque<String> test = new ArrayDeque<>("test");
        assertEquals("test", test.get(0));
        assertNull(test.get(1));
        assertNull(test.get(100));
    }

    @Test
    public void get1() {
        ArrayDeque<String> test = new ArrayDeque<>("much");
        test.addFirst("very");
        test.addFirst("Vietnam");
        test.addFirst("love");
        test.addFirst("I");

        assertEquals("I", test.get(0));
        assertEquals("much", test.get(4));
        assertNull(test.get(5));
    }

    @Test
    public void getAll() {
        ArrayDeque<String> test = new ArrayDeque<>("test");
        String[] newArray = new String[1];
        String[] expected = {"test"};
        test.getAll(newArray);

        assertArrayEquals(expected, newArray);

    }

    @Test
    public void getAll1() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addLast("I");
        test.addLast("love");
        test.addLast("Vietnam");
        test.addLast("very");
        test.addLast("much");

        String[] newArray = new String[8];
        String[] expected = {"I", "love", "Vietnam", "very", "much", null, null, null};
        test.getAll(newArray);

        assertArrayEquals(expected, newArray);
    }

    /*@Test
    public void expand() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addLast("I");
        test.addLast("love");
        test.addLast("Vietnam");
        test.addLast("very");
        test.addLast("very");
        test.addLast("very");
        test.addLast("much");

        test.expand();

        String[] newArray = {"I", "love", "Vietnam", "very", "very", "very",
                "much", null, null, null, null, null, null, null, null, null};
        assertArrayEquals(newArray, test.getItems());
        test.addLast("!");
        test.addFirst("and");
        test.addFirst("You");

        test.printDeque();
        String expected = "You and I love Vietnam very very very much ! \n";

        assertEquals(expected, outContent.toString());
    }*/

    /*@Test
    public void shorten() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addLast("I");
        test.addLast("love");
        test.shorten();

        String[] newArray = {"I", "love", null, null};
        assertArrayEquals(newArray, test.getItems());

        test.addLast("Vietnam");

        test.printDeque();
        String expected = "I love Vietnam \n";

        assertEquals(expected, outContent.toString());
    }*/

    @Test
    public void testCheckSize() { // Test resizing ability when expanding the array.
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addLast("I");
        test.addLast("love");
        test.addLast("Vietnam");
        test.addLast("very");
        test.addLast("very");
        test.addLast("very");
        test.addLast("very");
        test.addLast("very"); // Expanding at this stage.
        test.addLast("very");
        test.addLast("much");


        String[] newArray = {"I", "love", "Vietnam", "very", "very", "very",
                "very", "very", "very", "much", null, null, null, null, null, null};
        assertArrayEquals(newArray, test.getItems());
        test.addLast("!");
        test.addFirst("and");
        test.addFirst("You");

        test.printDeque();
        String expected = "You and I love Vietnam very very very very very very much ! \n";

        assertEquals(expected, outContent.toString());
        outContent.reset();

        /**
         * Test shortening the array.
         */

        for (int i = 0; i < 9; i++) {
            test.removeLast();
        }

        String[] newArray1 = {"You", "and", "I", "love", null, null, null, null};
        assertArrayEquals(newArray1, test.getItems());

        test.addFirst("!");
        test.addLast("Vietnam");

        test.printDeque();
        String expected1 = "! You and I love Vietnam \n";

        assertEquals(expected1, outContent.toString());
    }

    @Test
    public void moreTest() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        ArrayDeque.addFirst(0);
         ArrayDeque.removeFirst();
         ArrayDeque.addLast(2);
         ArrayDeque.removeLast();
         ArrayDeque.addLast(4);
         ArrayDeque.removeFirst();
         ArrayDeque.addFirst(6);
         ArrayDeque.addLast(7);
         ArrayDeque.addLast(8);
         ArrayDeque.removeFirst();
         ArrayDeque.removeLast();
         ArrayDeque.addLast(11);
         ArrayDeque.addLast(12);
         ArrayDeque.get(2);
         ArrayDeque.addLast(14);
         ArrayDeque.removeLast();
         ArrayDeque.addFirst(16);
         ArrayDeque.addFirst(17);
         ArrayDeque.addFirst(18);
         ArrayDeque.addLast(19);
         ArrayDeque.removeFirst();
         ArrayDeque.removeFirst();
         assertEquals(16, (long) ArrayDeque.removeFirst());
    }


// **************************************** //
    /**
     * Tests initialization for output.
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
