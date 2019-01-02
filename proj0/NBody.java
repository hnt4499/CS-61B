public class NBody {

    /**
     * Reads the radius of the universe from the given text file.
     */
    public static double readRadius(String fileLocation) {
        In in = new In(fileLocation);
        in.readInt();
        return in.readDouble();
    }

    /**
     * Reads and returns an array of Planets corresponding to the given text file
     */
    public static Planet[] readPlanets(String fileLocation) {
        In in = new In(fileLocation);
        int numberOfPlanets = in.readInt();
        // Initialized an array.
        Planet[] planets = new Planet[numberOfPlanets];

        in.readDouble();
        for (int i = 0; i < numberOfPlanets; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        /* Reads data from the command line */
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String filename = args[2];

        /* Reads in the planets and the radius */
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        /* Sets up a time value and two arrays of forces */
        //double time = 0;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];


        /* Initialize the canvas */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();

        /* Starts drawing */
        for (double time = 0; time < T; time += dT) {
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dT, xForces[i], yForces[i]);
                planets[i].draw();
            }

            /* Shows the canvas */
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
































