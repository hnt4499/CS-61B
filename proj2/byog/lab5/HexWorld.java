package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static char[][] createHexagon(int height, int width, int length) {

        char[][] arrays = new char[width][height];

        for (int i = 0; i < length; i += 1) {
            String string = " ".repeat(length - i - 1) + "a".repeat(2 + 2 * i)
                    + " ".repeat(length - i - 1);
            arrays[i] = string.toCharArray();
        }

        for (int i = length; i < length * 2 - 1; i += 1) {
            arrays[i] = arrays[length - 1];
        }

        for (int i = 0; i < width / 2; i += 1) {
            arrays[width - i - 1] = arrays[i];
        }
        return arrays;
    }

    public static void addHexagon(TETile[][] tiles, int length, int xPosition, int yPosition) {
        int height = length * 2;
        int width = length * 3 - 2;

        char[][] arrays = createHexagon(height, width, length);

        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                if (arrays[i][j] == ' ') {
                    tiles[xPosition + i][yPosition + j] = Tileset.NOTHING;
                } else {
                    tiles[xPosition + i][yPosition + j] = Tileset.FLOWER;
                }
            }
        }
    }

    public static TETile[][] initializeTiles(int width, int height) {
        TETile[][] randomTiles = new TETile[width][height];

        for (int x = 0; x < 50; x += 1) {
            for (int y = 0; y < 50; y += 1) {
                randomTiles[x][y] = Tileset.NOTHING;
            }
        }

        return randomTiles;
    }

    public static void main(String[] args) {
        int height = 50, width = 50;
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        TETile[][] randomTiles = initializeTiles(width, height);
        addHexagon(randomTiles, 4, 10, 10);

        ter.renderFrame(randomTiles);
    }
}
