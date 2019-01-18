package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    
    private TETile[][] finalWorldFrame;
    private Position player; // This represents the position of the player.

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        String inputArray = input.toLowerCase();

        if (inputArray.charAt(0) == 'n') {
            int endOfSeed = inputArray.indexOf('s');
            finalWorldFrame = initializeGame(input.substring(1, endOfSeed));

            char[] moves = inputArray.substring(endOfSeed + 1).toCharArray();
            move(moves);
        }
        return finalWorldFrame;
    }

    private TETile[][] initializeGame(String string) {
        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        /* Generates random object which takes input as seed. */
        Random random = new Random(Long.parseLong(string));

        /* Generates the first (initializing) 'connecting door' and the player position. */
        Position[] position = new Position[1];
        position[0] = new Position(5, 15, 0);
        player = new Position(6, 15, 0);
        /* Draws onto the final World Frame */
        drawRoom(random, finalWorldFrame, position);
        /* Ensures that the 'initializing connecting door is filled by the wall */
        finalWorldFrame[5][15] = Tileset.WALL;
        finalWorldFrame[6][15] = Tileset.PLAYER;

        return finalWorldFrame;
    }

    private void move(char[] input) {
        for (char c : input) {
            if (c == 'w') {
                move(3);
            } else if (c == 'a') {
                move(2);
            } else if (c == 's') {
                move(1);
            } else if (c == 'd') {
                move(0);
            } else if (c == ':'){
                // save the game
                return;
            }
        }
    }
    
    private void move(int direction) {
        int x = player.x;
        int y = player.y;
        if (direction == 0) {
            if (finalWorldFrame[x + 1][y].equals(Tileset.FLOOR)) {
                finalWorldFrame[x + 1][y] = Tileset.PLAYER;
                finalWorldFrame[x][y] = Tileset.FLOOR;
                player.x = x + 1;
            }
        } else if (direction == 1) {
            if (finalWorldFrame[x][y - 1].equals(Tileset.FLOOR)) {
                finalWorldFrame[x][y - 1] = Tileset.PLAYER;
                finalWorldFrame[x][y] = Tileset.FLOOR;
                player.y = y - 1;
            }
        } else if(direction == 2) {
            if (finalWorldFrame[x - 1][y].equals(Tileset.FLOOR)) {
                finalWorldFrame[x - 1][y] = Tileset.PLAYER;
                finalWorldFrame[x][y] = Tileset.FLOOR;
                player.x = x - 1;
            }
        } else if (direction == 3) {
            if (finalWorldFrame[x][y + 1].equals(Tileset.FLOOR)) {
                finalWorldFrame[x][y + 1] = Tileset.PLAYER;
                finalWorldFrame[x][y] = Tileset.FLOOR;
                player.y = y + 1;
            }
        }
    }

    private class Position {
        private int x;
        private int y;
        private int direction;

        public Position(int a, int b, int d) {
            x = a;
            y = b;
            direction = d;
        }

        private Position changeDirection() {
            int newDirection = (direction + 2) % 4;
            return new Position(x, y, newDirection);
        }
    }

    private class Room {
        private int width;
        private int height;
        private Random random;

        private Position[] positions; // position of all the to-be-generated doors.

        private char[][] room; // an array that represents the room.

        private Position connectingDoor; // an object that represents the exact index of the connecting door.

        public Room(Random r, Position p) {
            random = r;
            width = 3 + random.nextInt(3);
            height = 3 + random.nextInt(3);
            connectingDoor = p.changeDirection();

            room = createRoom();
            positions = generateDoors();
        }

        private Position generateDoorsHelper(int direction) {
            int doorPosition;

            // direction 0, with higher priority of generating a new door..
            if (direction == 0) {
                doorPosition = random.nextInt((height - 2) * 2) + 1;
                if (doorPosition < height - 1) {
                    //room[width - 1][doorPosition] = '.';
                    return new Position(width - 1, doorPosition, 0);
                }
            }

            // direction 1.
            if (direction == 1) {
                doorPosition = random.nextInt((width - 2) * 2) + 1;
                if (doorPosition < width - 1) {
                    //room[doorPosition][0] = '.';
                    return new Position(doorPosition, 0, 1);
                }
            }

            // direction 2.
            if (direction == 2) {
                doorPosition = random.nextInt((height - 2) * 2) + 1;
                if (doorPosition < height - 1) {
                    //room[0][doorPosition] = '.';
                    return new Position(0, doorPosition, 2);
                }
            }


            // direction 3.
            if (direction == 3) {
                doorPosition = random.nextInt((width - 2) * 2) + 1;
                if (doorPosition < width - 1) {
                    //room[doorPosition][height - 1] = '.';
                    return new Position(doorPosition, height - 1, 3);
                }
            }
            return null;
        }

        private int countDoors(Position[] doorsPosition) {
            int count = 0;
            for (Position doorPosition : doorsPosition) {
                if (doorPosition != null) {
                    count += 1;
                }
            }
            return count;
        }

        private void drawDoors(Position[] positions) {
            for (Position position : positions) {
                if (position != null) {
                    room[position.x][position.y] = '.';
                }
            }
        }

        private Position[] generateDoors() {
            Position[] doorsPosition = new Position[4];

            int mustHaveDoorDirection = connectingDoor.direction;

            while (doorsPosition[mustHaveDoorDirection] == null) {
                doorsPosition[mustHaveDoorDirection] = generateDoorsHelper(mustHaveDoorDirection);
            }

            while (countDoors(doorsPosition) < 3) {
                for (int i = 0; i < 4; i += 1) {
                    if (i != mustHaveDoorDirection) {
                        doorsPosition[i] = generateDoorsHelper(i);
                    }
                }
            }
            drawDoors(doorsPosition);

            return doorsPosition;
        }

        private char[][] createRoom() {

            char[][] arrays = new char[width][height];

            char[] chars = "#".repeat(height).toCharArray();
            arrays[0] = chars;
            arrays[width - 1] = chars.clone();

            for (int i = 1; i < width - 1; i += 1) {
                String string = "#" + ".".repeat(height - 2) + "#";
                arrays[i] = string.toCharArray();
            }

            return arrays;
        }

        private boolean isValidRoom(TETile[][] tiles, int x, int y) {
            if (x < 0 || x + width > tiles.length || y < 0 || y + height > tiles[0].length) {
                return false;
            }
            int numberOfFloors = 0;
            for (int i = 0; i < width; i += 1) {
                for (int j = 0; j < height; j += 1) {
                    if (tiles[x + i][y + j].equals(Tileset.FLOOR)) {
                        numberOfFloors += 1;
                    }
                }
            }
            return numberOfFloors < 2;
        }

        private void drawRoom(TETile[][] tiles) {
            int direction = connectingDoor.direction;
            Position connectingDoorPosition = positions[direction];

            int xCoordinate = connectingDoor.x - connectingDoorPosition.x;
            int yCoordinate = connectingDoor.y - connectingDoorPosition.y;

            if (isValidRoom(tiles, xCoordinate, yCoordinate)) {
                for (int i = 0; i < width; i += 1) {
                    for (int j = 0; j < height; j += 1) {
                        if (room[i][j] == '#') {
                            tiles[xCoordinate + i][yCoordinate + j] = Tileset.WALL;
                        } else if (room[i][j] == '.') {
                            tiles[xCoordinate + i][yCoordinate + j] = Tileset.FLOOR;
                        }
                    }
                }
                updateDoorsPosition(xCoordinate, yCoordinate);
            } else {
                tiles[connectingDoor.x][connectingDoor.y] = Tileset.WALL;
                positions = null;
            }
        }

        private void updateDoorsPosition(int xCoordinate, int yCoordinate) {
            positions[connectingDoor.direction] = null;
            for (Position position : positions) {
                if (position != null) {
                    position.x = position.x + xCoordinate;
                    position.y = position.y + yCoordinate;
                }
            }
        }
    }
    /////// End of Room class. ////////

    private void drawRoom(Random random, TETile[][] tiles, Position[] positions) {
        if (positions == null) {
            return;
        }
        for (Position position : positions) {
            if (position != null) {
                Position[] newDoors = drawRoom(random, tiles, position);
                drawRoom(random, tiles, newDoors);
            }
        }
    }

    /**
     * Generates a new room and returns its doors position.
     * The case in which room.positions is null is when the next room cannot be generated
     * because of the lack of space. If so, try 5 times until finding the satisfied room.
     * If after 5 times, no new room can be generated, it should be an ending.
     *
     * @param tiles    for drawing purposes.
     * @param position of the connecting door.
     * @return an array of position of the newly generated door(s).
     */
    private Position[] drawRoom(Random random, TETile[][] tiles, Position position) {
        Room room = new Room(random, position);
        room.drawRoom(tiles);
        for (int i = 0; i < 5; i += 1) {
            if (room.positions != null) {
                break;
            }
            room = new Room(random, position);
            room.drawRoom(tiles);
        }
        return room.positions;
    }

    private static TETile[][] initializeTiles(int width, int height) {
        TETile[][] randomTiles = new TETile[width][height];

        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                randomTiles[x][y] = Tileset.NOTHING;
            }
        }

        return randomTiles;
    }

    public static void main(String[] args) {
        int height = 30, width = 80;
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        TETile[][] randomTiles = initializeTiles(width, height);

        Random random = new Random();
        Game game = new Game();

        Position[] position = new Position[1];
        position[0] = game.new Position(5, 25, 0);

        game.drawRoom(random, randomTiles, position);
        ter.renderFrame(randomTiles);

        System.out.println(" ");
    }
}
