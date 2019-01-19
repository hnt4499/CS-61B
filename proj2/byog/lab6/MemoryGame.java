package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private Font font;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        font = new Font("Lucida Sans Regular", Font.PLAIN, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        char[] chars = new char[n];
        for (int i = 0; i < n; i += 1) {
            chars[i] = CHARACTERS[rand.nextInt(26)];
        }
        return new String(chars);
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Lucida Sans Regular", Font.PLAIN, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);

        int x = width / 2 + width % 2;
        int y = height / 2 + width % 2;

        StdDraw.text(x, y, s);
        StdDraw.show();
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {
        char[] chars = letters.toCharArray();
        for (char c: chars) {
            /* Draws each letter on the screen and keeps it for 1 second. */
            drawFrame(Character.toString(c));
            StdDraw.pause(1000);
            /* Stops for 0.5 second. */
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        int count = 0;
        char[] chars = new char[n];
        char[] accomplished;

        /* Clears the cache */
        while (StdDraw.hasNextKeyTyped()) {
            StdDraw.nextKeyTyped();
        }

        while (count < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char input = StdDraw.nextKeyTyped();
                chars[count] = input;
                count += 1;

                /* Prints what the player typed */
                accomplished = new char[count];
                System.arraycopy(chars, 0, accomplished, 0, count);
                drawFrame(new String(accomplished));
            }
        }
        /* Stops the frame for a second so the player could see what he accomplished. */
        StdDraw.pause(1000);

        return new String(chars);
    }

    public void startGame() {
        round = 1;
        gameOver = false;

        while (!gameOver) {
            drawFrame("Round " + round);
            StdDraw.pause(1000);

            String expected = generateRandomString(round);
            flashSequence(expected);
            String found = solicitNCharsInput(round);
            if (expected.equals(found)) {
                round += 1;
            }
            if (round > 5) {
                gameOver = true;
            }
        }

        //TODO: Establish Game loop
    }

}
