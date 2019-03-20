import javafx.scene.shape.Sphere;
public class Planet extends Sphere {

        private double xPosition; // when creating an object
        private double yPosition; // take initial position and velocity
        private double zPosition; // from the same date for every planet
        private double xVelocity; // for example, 01-01-2000
        private double yVelocity;
        private double zVelocity;
        private double mass;
        private final double G = 6.67408*10e-11;
        private final double M = 2*10e30;

        public Planet (double mass, double xPosition, double yPosition, double zPosition, double xVelocity, double yVelocity, double zVelocity, double diameter) {
            this.mass = mass;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.zPosition = zPosition;
            this.xVelocity = xVelocity;
            this.yVelocity = yVelocity;
            this.zVelocity = zVelocity;
            setRadius(diameter/2);
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

        /*public static void main (String args[]) {
            Planet mercury = new Planet(3.302*10e23, -2.105262111032039E+10, -6.640663808353403E+10, -3.492446023382954E+09,  3.665298706393840E+04, -1.228983810111077E+04, -4.368172898981951E+03);
            for (int i = 0; i<86400; i++) {
                mercury.xPosition(1);
                mercury.yPosition(1);
                mercury.zPosition(1);
            }
            System.out.println(mercury.xPosition);
            System.out.println(mercury.yPosition);
            System.out.println(mercury.zPosition);
        }*/
    }


