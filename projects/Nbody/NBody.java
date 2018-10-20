public class NBody{

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

            //StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, "image/starfield.jpg");

            for(Planet P: allPlanet) {
                P.draw();
            }

        }
    }

}