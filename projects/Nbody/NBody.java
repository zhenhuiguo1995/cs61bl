public class NBody{

    public static int readNumOfPlanets(String string){
        In in = new In(string);
        int numberOfPlanets = in.readInt();
        return numberOfPlanets;
    }

    public static double readRadius(String string){
        In in = new In(string);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

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
            //System.out.println( xxPos + " " + yyPos + " " + xxVel +" " + yyVel + " " + mass + " " + name);
            allPlanet[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
            i += 1;
        }
        return allPlanet;
    }



    public static void main(String[] args){
        if (args.length != 3){
            System.out.println("Please given exactly three command line arguments");
            System.exit(1);
        } else {
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String name = args[2];
            Planet[] allPlanet =  NBody.readPlanets(name);
            double radius = NBody.readRadius(name);
            int numerOfPlanets = NBody.readNumOfPlanets(name);

            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            //StdDraw.picture(0, 0, "images/starfield.jpg");

            for(Planet P: allPlanet) {
                P.draw();
            }

            double time = 0;
            while(time < T){
                double[] xForces = new double[numerOfPlanets];
                double[] yForces = new double[numerOfPlanets];
                for(int i= 0 ; i < numerOfPlanets; i ++){
                    xForces[i] = allPlanet[i].calcNetForceExertedByX(allPlanet);
                    yForces[i] = allPlanet[i].calcNetForceExertedByY(allPlanet);
                }
                for(int i = 0 ; i < numerOfPlanets; i ++){
                    allPlanet[i].update(dt, xForces[i], yForces[i]);
                }
                StdDraw.picture(0, 0 ,"images/starfield.jpg");
                for(Planet P: allPlanet){
                    P.draw();
                }
                StdDraw.show();
                StdDraw.pause(10);
                time += dt;
            }

            StdOut.printf("%d\n", allPlanet.length);
            StdOut.printf("%.2e\n", radius);
            for(int i = 0; i < allPlanet.length; i ++){
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        allPlanet[i].xxPos, allPlanet[i].yyPos, allPlanet[i].xxVel,
                        allPlanet[i].yyVel, allPlanet[i].mass, allPlanet[i].imgFileName);
            }

        }
    }

}