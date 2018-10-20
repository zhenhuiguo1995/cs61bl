/**
 * @author Alfred
 * Simulates a planet by calculating the gravational force
 * and velocity.
 */
public class Planet {
    /** The x coordinate of the planet object.*/
    public double xxPos;
    /** The y coordinate of the planet object.*/
    public double yyPos;
    /** The x velocity of the planet object.*/
    public double xxVel;
    /** The y velocity of the planet object.*/
    public double yyVel;
    /** The mass of the planet object.*/
    public double mass;
    /** The name of the planet object.*/
    public String imgFileName;
    /** The gravitational constant in physics.*/
    public static final double G = 6.67e-11;

    /**
     * A planet Constructor.
     * @param xP ** the x coordinates.
     * @param yP ** the y coordinates.
     * @param xV ** the velocity at the x axis.
     * @param yV ** the velocity at the  axis.
     * @param m ** the mass of the planet object.
     * @param img ** the images of the planet object
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /**
     * A planet constructor.
     * @param P ** A planet object.
     */
    public Planet(Planet P) {
        this.xxPos = P.xxPos;
        this.yyPos = P.yyPos;
        this.xxVel = P.xxVel;
        this.yyVel = P.yyVel;
        this.mass = P.mass;
        this.imgFileName = P.imgFileName;
    }

    /**
     * Calculates the distance between two planets objects.
     * @param P ** A planet object.
     * @return ** a double value representing the distance.
     */
    public double calcDistance(Planet P) {
        double dist;
        double xdist = this.xxPos - P.xxPos;
        double ydist = this.yyPos - P.yyPos;
        dist = Math.sqrt(xdist * xdist + ydist * ydist);
        return dist;
    }

    /**
     * Calculates the gravity between two planet objects.
     * @param P ** a planet object.
     * @return  ** a double value representing the gravity.
     */
    public double calcForceExertedBy(Planet P) {
        double dist = this.calcDistance(P);
        double force = G * this.mass * P.mass / (dist * dist);
        return force;
    }

    /**
     * Calculates the gravity between two planet objects
     * in the x axis.
     * @param P ** a planet object.
     * @return ** a double value representing the force
     */
    public double calcForceExertedByX(Planet P) {
        double xDist = P.xxPos - this.xxPos;
        double xForce = calcForceExertedBy(P) * xDist / calcDistance(P);
        return xForce;
    }

    /**
     * Calculates the gravity between two planet objects.
     * in the y axis
     * @param P ** a planet object.
     * @return ** a double value representing the force.
     */
    public double calcForceExertedByY(Planet P) {
        double yDist = P.yyPos - this.yyPos;
        double yForce = calcForceExertedBy(P) * yDist / calcDistance(P);
        return yForce;
    }

    /**
     * Calculates the net force in the x axis
     * between a series of planet objects.
     * @param allPlanets ** an array of planet objects.
     * @return ** a double value representing the net force
     * in the x axis.
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double xNetForce = 0;
        for (Planet P: allPlanets) {
            if (!this.equals(P)) {
                xNetForce += this.calcForceExertedByX(P);
            }
        }
        return xNetForce;
    }

    /**
     * Calculates the net force in the y axis
     * between a series of planet objects.
     * @param allPlanets * an array of planet objects.
     * @return ** a double value representing the net force
     * in the y axis
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double yNetForce = 0;
        for (Planet P: allPlanets) {
            if (!this.equals(P)) {
                yNetForce += this.calcForceExertedByY(P);
            }
        }
        return yNetForce;
    }

    /**
     * Updates the position and velocity of a planet object.
     * @param dt ** the time interval.
     * @param fX ** the net forces at the x axis.
     * @param fY ** the net forces at the y axis.
     */
    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /**
     * Draw the planet object at its current location.
     */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}

