/**
 * SpaceObject
 */
public class SpaceObject /*extends Sphere*/ {
    /*
        private double mass; // mass of the object (10e24 kg)
        private double diameter; // diameter of the object (km)
        private double escapeVelocity; // speed needed to escape gravitational force (km/s)
        private double gravity; // gravitational acceleration (m/s^2)
        private double distanceToSun; // distance from object to the sun (10e6 km)
        private double orbitalPeriod; // days needed to do one full tour of the orbit (days)
        private double orbitalVelocity; // speed on the orbit (km/s)
        private double orbitalInclination; // inclination to the plane sun - earth of the orbit
        private double orbitalEccentricity; // eccentricity of the orbit
        private int numberOfMoons; // number of objects turning around the object
        private SpaceObject[] moonList; // list of the moons
        private double a; // semi-major axis
        private double timeAngle0; // time at which planet has angle 0 (ms)
        private static double gravitationalConstant = 6.67553 * 10e-11; // gravitational constant (m^3 / kg * s^2)
        private PolarCoordinates pCoordinates; // coordinates of the planet in polar coodinates
        private CartesianCoordinates cCoordinates; // coordinates of the planet in cartesian coordinates
        private CartesianCoordinates direction;
        private CartesianCoordinates velocity;
        private double angle;
        private boolean isMoon;
        private SpaceObject moonOf;*/
    private final static double DAYTOMILI = 86400000;

    private String name;
    private double a;
    private double orbitalEccentricity;
    private double timeI;
    private double r;
    private double theta;
    private double x;
    private double y;
    private double z;
    private double xI;
    private double yI;
    private double zI;
    private double rI;
    private double orbitPeriod;
    private double angularSpeed;
    private int millisPerDay = 86400;

    public void calcR() {
        this.rI = Math.sqrt(xI * xI + yI * yI);
    }

    public SpaceObject(String name,double x,double y,double time,double orbitPeriod,double semiMajorAxis,double eccentricity){
        this.name = name;
        this.xI = x;
        this.yI = y;
        a = semiMajorAxis;
        this.orbitalEccentricity = eccentricity;
        calcR();
        this.orbitPeriod = orbitPeriod;
        angularSpeed = 2*Math.PI*a/orbitPeriod;
        theta = Math.atan(yI/xI);
        if (xI<0) {
            theta+=Math.PI;
        }
        timeI = theta / angularSpeed;
    }

    public double[] orbitXY(double time) {
        double theta = (time - timeI) * angularSpeed ;
        double r = (this.a * (1 - orbitalEccentricity * orbitalEccentricity) / (1 + orbitalEccentricity * Math.cos(theta)));
        double[] output = new double[2];
        output[0] = r * Math.cos(theta);
        output[1] = r * Math.sin(theta);
        return output;
    }
    /* TODO: This is the method to get points for the orbit trajectory
        Explanation: For each day in the orbital period the orbitXY method is called.
        The distanceToPixelsMultiplier is for translating the coordinates to the scale used in the simulation window.
     */
    public Double[] getTrajectoryPoints(int daysPerOrbitalPeriod) {
        Double[] points = new Double[2*daysPerOrbitalPeriod];
        double distanceToPixelsMultiplier = (Math.pow(10,2));
        System.out.println("Trajectory for planet " + this.name);
        for (int i=0; i<2*daysPerOrbitalPeriod; i=i+2) {
            double[] XY = orbitXY(i*millisPerDay);
            points[i] = XY[0]*distanceToPixelsMultiplier;
            points[i+1] = XY[1]*distanceToPixelsMultiplier;
            System.out.println("New X: " + points[i]);
            System.out.println("New Y: " + points[i+1]);
        }
        return points;
    }

    public static void main(String[] args) {
        SpaceObject test = new SpaceObject("mars",1.32,0.496,0.0,687.0 * DAYTOMILI,1.5273,0.094);
        double[] t = test.orbitXY(System.currentTimeMillis());
        System.out.println(t[0]+" "+t[1]);
    }
/*
    public SpaceObject(double mass, double distanceToSun, double orbitalVelocity, double orbitalEccentricity,
            double a) {
        this.mass = mass;
        this.distanceToSun = distanceToSun;
        this.orbitalVelocity = orbitalVelocity;
        this.orbitalEccentricity = orbitalEccentricity;
        this.a = a;
    }
    public SpaceObject() {
    }
    public PolarCoordinates orbitPos() {
        double time = (System.currentTimeMillis() - timeAngle0) * 1000;
        double angle = orbitalVelocity * time; // radians
        this.setAngle(angle);
        double r = (this.a * (1 - Math.pow(orbitalEccentricity, 2) / (1 + orbitalEccentricity * Math.cos(this.getAngle()))));
        this.setpCoordinates(new PolarCoordinates(r,this.getAngle()));
        return new PolarCoordinates(r, angle);
    }
    // t ms for java system at chosen time
    public PolarCoordinates orbitPos(double t) {
        double time = (t - timeAngle0) * 1000;
        double angle = orbitalVelocity * time; // radians
        this.setAngle(angle);
        double r = (this.a * (1 - Math.pow(orbitalEccentricity, 2) / (1 + orbitalEccentricity * Math.cos(this.getAngle()))));
        this.setpCoordinates(new PolarCoordinates(r,this.getAngle()));
        return new PolarCoordinates(r, angle);
    }
    public PolarCoordinates orbitPosAngle(){
        double r = (this.a * (1 - Math.pow(orbitalEccentricity, 2) / (1 + orbitalEccentricity * Math.cos(this.getAngle()))));
        this.setpCoordinates(new PolarCoordinates(r,this.getAngle()));
        return new PolarCoordinates(r, angle);
    }
    public double gravForce(SpaceObject o) {
        double mass = Math.max(this.mass, o.mass);
        double distance = Math.abs(this.distanceToSun - o.distanceToSun);
        double force = (gravitationalConstant * mass) / (Math.pow(distance, 2));
        return force;
    }
    public CartesianCoordinates polarToCartesian(PolarCoordinates pC) {
        double x, y, z;
        if (this.isMoon()) {
            SpaceObject o = this.getMoonOf();
            PolarCoordinates p = this.getMoonOf().orbitPos();
            x = p.getR() * Math.cos(p.getAngle()) - pC.getR() * Math.cos(pC.getAngle());
            y = p.getR() * Math.sin(p.getAngle()) - pC.getR() * Math.sin(pC.getAngle());
            z = Math.sin(o.getOrbitalInclination()) * p.getR() - Math.sin(this.getOrbitalInclination()) * this.getR();
        } else {
            x = pC.getR() * Math.cos(pC.getAngle());
            y = pC.getR() * Math.sin(pC.getAngle());
            z = Math.sin(this.getOrbitalInclination()) * pC.getR();
        }
        return new CartesianCoordinates(x, y, z);
    }
    public void move() {
        this.setcCoordinates(new CartesianCoordinates(this.cCoordinates.getX() + this.getVelocity().getX(),this.cCoordinates.getY() + this.getVelocity().getY()));
    }
    public void update(SpaceObject o) {
        this.pCoordinates.setR(Math.sqrt(Math.pow((o.cCoordinates.getX() - this.cCoordinates.getX()),2) + ath.pow((o.cCoordinates.getY() - this.cCoordinates.getY()),2)));

        double acc = o.getMass()/(Math.pow(this.pCoordinates.getR(),2));
        this.getDirection().setX((o.cCoordinates.getX() - this.cCoordinates.getX()) / this.pCoordinates.getR());
        this.getDirection().setY((o.cCoordinates.getY() - this.cCoordinates.getY()) / this.pCoordinates.getR());

        this.setDirection(new CartesianCoordinates((o.cCoordinates.getX() - this.cCoordinates.getX()) / this.pCoordinates.getR(),(o.cCoordinates.getY() - this.cCoordinates.getY()) / this.pCoordinates.getR()));
        this.setVelocity(new CartesianCoordinates(this.getVelocity().getX() + this.getDirection().getX() * acc, this.getVelocity().getY() + this.getDirection().getY() * acc));
        this.move();
    }
*/

}