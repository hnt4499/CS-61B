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
                if (arrays[i][j] == 'a') {
                    tiles[xPosition + i][yPosition + j] = Tileset.FLOWER;
                }
            }
        }
    }

    private static class Position {
        int xPosition;
        int yPosition;

        public Position(int x, int y) {
            xPosition = x;
            yPosition = y;
        }

        public void clone(Position position) {
            position.xPosition = this.xPosition;
            position.yPosition = this.yPosition;
        }

    }

    private static Position addHexagon(TETile[][] tiles, int length, Position position) {
        addHexagon(tiles, length, position.xPosition, position.yPosition);

        position.xPosition = position.xPosition - (length * 2 - 1);
        position.yPosition = position.yPosition + length;

        return position;
    }

    private static boolean checkPosition(TETile[][] tiles, Position position, int length) {
        int x = position.xPosition;
        int y = position.yPosition;

        return !(x < 0 || x > (tiles.length + 2 - 3 * length)|| y < 0 || y > (tiles[0].length - 2 * length));
    }

    /**
     * This method is to update the position for the upper-right new hexagon if that position is not out of bound.
     * Otherwise, it will update the position for the above hexagon.
     */
    private static void updatePosition(TETile[][] tiles, Position position, int length) {
        position.yPosition = position.yPosition + length;

        int tempX = position.xPosition + (length * 2 - 1);
        if (tempX <= (tiles[0].length - 2 * length)) {
            position.xPosition = tempX;
        }
    }

    public static void addMultiple(TETile[][] tiles, int length) {
        int x = tiles.length / 2 + tiles.length % 2 - length - length % 2;
        int y = (tiles[0].length % (length * 2)) / 2;

        Position position = new Position(x, y);
        Position tempPosition = new Position(x, y);

        while (checkPosition(tiles, position, length)) {
            addHexagon(tiles, length, position);
            if (!checkPosition(tiles, position, length)) {
                tempPosition.clone(position);

                updatePosition(tiles, position, length);
                position.clone(tempPosition);
            }
        }
    }

    public static TETile[][] initializeTiles(int width, int height) {
        TETile[][] randomTiles = new TETile[width][height];

        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
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
        addMultiple(randomTiles, 3);

        ter.renderFrame(randomTiles);
    }
}
