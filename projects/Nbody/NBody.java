/**
 * @author Alfred
 * Simulating the running of planets.
 */
public class NBody {
    /**
     * Read the file and return the number of plantes.
     * @param string ** the name of the file.
     * @return ** an int variable representing the number of planets
     */
    public static int readNumOfPlanets(String string) {
        In in = new In(string);
        int numberOfPlanets = in.readInt();
        return numberOfPlanets;
    }

    /**
     * Read the file and return the radius of the universe.
     * @param string ** the name of the file.
     * @return ** a double variable representing the radius.
     */
    public static double readRadius(String string) {
        In in = new In(string);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /**
     * Read the file and return an array containing the
     * planet objects in the file.
     * @param string ** the name of the file.
     * @return ** an array containing the planet object.
     */
    public static Planet[] readPlanets(String string) {
        In in = new In(string);
        int numberOfPlanets = in.readInt();
        Planet[] allPlanet = new Planet[numberOfPlanets];
        double radius = in.readDouble();
        int i = 0;
        while (i < numberOfPlanets) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            allPlanet[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
            i += 1;
        }
        return allPlanet;
    }

    /**
     * The main method runs all the method in the Nbody class
     * and simulates the process.
     * @param args ** command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Three command line arguments");
            System.exit(1);
        } else {
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String name = args[2];
            Planet[] allPlanet =  NBody.readPlanets(name);
            double radius = NBody.readRadius(name);
            int numberOfPlanets = NBody.readNumOfPlanets(name);
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            for (Planet P: allPlanet) {
                P.draw();
            }
            double time = 0;
            while (time < T) {
                double[] xForces = new double[numberOfPlanets];
                double[] yForces = new double[numberOfPlanets];
                for (int i = 0; i < numberOfPlanets; i++) {
                    xForces[i] = allPlanet[i].calcNetForceExertedByX(allPlanet);
                    yForces[i] = allPlanet[i].calcNetForceExertedByY(allPlanet);
                }
                for (int i = 0; i < numberOfPlanets; i++) {
                    allPlanet[i].update(dt, xForces[i], yForces[i]);
                }
                StdDraw.picture(0, 0 , "images/starfield.jpg");
                for (Planet P: allPlanet) {
                    P.draw();
                }
                StdDraw.show();
                StdDraw.pause(10);
                time += dt;
            }
            StdOut.printf("%d\n", allPlanet.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < allPlanet.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        allPlanet[i].xxPos, allPlanet[i].yyPos,
                        allPlanet[i].xxVel, allPlanet[i].yyVel,
                        allPlanet[i].mass, allPlanet[i].imgFileName);
            }

        }
    }

}
