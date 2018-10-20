import java.lang.Math;

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet P){
        this.xxPos = P.xxPos;
        this.yyPos = P.yyPos;
        this.xxVel = P.xxVel;
        this.yyVel = P.yyVel;
        this.mass = P.mass;
        this.imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P){
        double Dist;
        double xDist = this.xxPos - P.xxPos;
        double yDist = this.yyPos - P.yyPos;
        Dist = Math.sqrt(xDist * xDist + yDist * yDist);
        return Dist;
    }

    public double calcForceExertedBy(Planet P){
        double Dist = this.calcDistance(P);
        double Force = G * this.mass * P.mass / (Dist * Dist);
        return Force;
    }

    public double calcForceExertedByX(Planet P){
        double xDist = P.xxPos - this.xxPos;
        double xForce = calcForceExertedBy(P) * xDist / calcDistance(P);
        return xForce;
    }

    public double calcForceExertedByY(Planet P){
        double yDist = P.yyPos - this.yyPos;
        double yForce = calcForceExertedBy(P) * yDist / calcDistance(P);
        return yForce;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double xNetForce = 0;
        for (Planet P: allPlanets){
            if (! this.equals(P)){
                xNetForce += this.calcForceExertedByX(P);
            }
        }
        return xNetForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double yNetForce = 0;
        for (Planet P: allPlanets){
            if (! this.equals(P)){
                yNetForce += this.calcForceExertedByY(P);
            }
        }
        return yNetForce;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX/this.mass;
        double aY = fY/this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
