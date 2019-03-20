import java.awt.*;
import java.util.ArrayList;

public class Planet {
    private int secondsPerDay = 86400;
    private double xPosition; // when creating an object
    private double yPosition; // take initial position and velocity
    private double zPosition; // from the same date for every planet
    private double xVelocity; // for example, 01-01-2000
    private double yVelocity;
    private double zVelocity;
    private double mass;
    private final double G = 1.993E-44;//6.67408*10e-11; // the second number is with m3 rather than au3
    private final double M = 2*10e30;

    public Planet (double mass, double xPosition, double yPosition, double zPosition, double xVelocity, double yVelocity, double zVelocity) {
        this.mass = mass;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
    }

    public double xPosition(double time) {
        double r = Math.sqrt(xPosition*xPosition + yPosition*yPosition + zPosition*zPosition);
        double force = (G*M)/(r*r);
        xPosition = xPosition + (time * (xVelocity + (force/this.mass)*time));
        xVelocity = xVelocity + ((force/this.mass)*time);
        return xPosition;
    }
    public double yPosition(double time) {
        double r = Math.sqrt(xPosition*xPosition + yPosition*yPosition + zPosition*zPosition);
        double force = (G*M)/(r*r);
        yPosition = yPosition + (time * (yVelocity + (force/this.mass)*time));
        yVelocity = yVelocity + ((force/this.mass)*time);
        return yPosition;
    }
    public double zPosition(double time) {
        double r = Math.sqrt(xPosition*xPosition + yPosition*yPosition + zPosition*zPosition);
        double force = (G*M)/(r*r);
        zPosition = zPosition + (time * (zVelocity + (force/this.mass)*time));
        zVelocity = zVelocity + ((force/this.mass)*time);
        return zPosition;
    }

    public static void main (String args[]) {
        int secondsPerDay = 86400;
        Planet mercury = new Planet(3.302*10e23, -3.884951255359090E-01,
                -1.193703739273220E-02, 3.466414962075259E-02,
                (-4.989539396985841E-03/86400), (-2.691230619778414E-02/86400),
                (-1.741371578050843E-03/86400));
        mercury.getTrajectoryPoints(88);
//        System.out.println(mercury.xPosition);
//        System.out.println(mercury.yPosition);
//        System.out.println(mercury.zPosition);
//        for (int i = 0; i<86400; i++) {
//            mercury.xPosition(1);
//            mercury.yPosition(1);
//            mercury.zPosition(1);
//        }
//        System.out.println(mercury.xPosition);
//        System.out.println(mercury.yPosition);
//        System.out.println(mercury.zPosition);
//        for (int i = 0; i<30; i++) {
//            mercury.xPosition(secondsPerDay);
//            mercury.yPosition(secondsPerDay);
//            mercury.zPosition(secondsPerDay);
//            System.out.println(mercury.xPosition);
//            System.out.println(mercury.yPosition);
//            System.out.println(mercury.zPosition);
//        }

    }
    public Double[] getTrajectoryPoints (int daysPerYear) {
        double distanceToPixelsMultiplier = (Math.pow(10,2));
        Double[] points = new Double[2*daysPerYear];
        for (int i = 0; i<(2*daysPerYear); i=i+2) {
            this.xPosition(secondsPerDay);
            this.yPosition(secondsPerDay);
            this.zPosition(secondsPerDay);
            System.out.println("Round " + i/2 + " New X position: " + xPosition);
            System.out.println("Round " + i/2 + " New Y position: " + yPosition);
            points[i] = xPosition*distanceToPixelsMultiplier;
            points[i+1] = yPosition*distanceToPixelsMultiplier;
        }
        return points;
    }
}
