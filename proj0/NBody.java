public class NBody {

    /** Reads the radius of the universe from the given text file. */
    public static double readRadius(String fileLocation) {
        In in = new In(fileLocation);
        in.readInt();
        return in.readDouble();
    }

    /** Reads and returns an array of Planets corresponding to the given text file */
    public static Planet[] readPlanets(String fileLocation) {
        In in = new In(fileLocation);
        int numberOfPlanets = in.readInt();
        // Initialized an array.
        Planet[] planets = new Planet[numberOfPlanets];

        in.readDouble();
        for (int i = 0; i < numberOfPlanets; i ++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
        }
        return planets;
    }
}
