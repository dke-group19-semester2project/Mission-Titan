/**
 * SpaceObject
 */
public class SpaceObject {

    private double mass; //mass of the object (10e24 kg)
    private double diameter; //diameter of the object (km)
    private double escapeVelocity; //speed needed to escape gravitational force (km/s)
    private double gravity; //gravitational acceleration (m/s^2)
    private double distanceToSun; //distance from object to the sun (10e6 km)
    private double orbitalPeriod; //days needed to do one full tour of the orbit (days)
    private double orbitalVelocity; //speed on the orbit (km/s)
    private double orbitalInclination; //inclination to the plane sun - earth of the orbit
    private double orbitalEccentricity; //eccentricity of the orbit
    private int numberOfMoons; //number of objects turning around the object
    private SpaceObject[] moonList; //list of the moons
    private double a; //semi-major axis
    private double timeAngle0; //time at which planet has angle 0 (ms)
    private static double gravitationalConstant = 6.67553 * 10e-11; //gravitational constant (m^3 / kg * s^2)
    private PolarCoordinates pCoordinates; // coordinates of the planet in polar coodinates
    private CartesianCoordinates cCoordinates; //coordinates of the planet in cartesian coordinates
    private boolean isMoon;
    private SpaceObject moonOf;

    public SpaceObject(double mass,double distanceToSun, double orbitalVelocity,double orbitalEccentricity, double a){
        this.mass = mass;
        this.distanceToSun = distanceToSun;
        this.orbitalVelocity = orbitalVelocity;
        this.orbitalEccentricity = orbitalEccentricity;
        this.a = a;
    }

    public SpaceObject(){
    }


    public PolarCoordinates orbitPos() {
        double time = (System.currentTimeMillis() - timeAngle0) * 1000;
        double angle = orbitalVelocity * time; //radians
        double r = (this.a * (1 - Math.pow(orbitalEccentricity,2)/(1+orbitalEccentricity * Math.cos(angle))));
        return new PolarCoordinates(r,angle);
    }

    //t ms for java system at chosen time
    public PolarCoordinates orbitPos(double t){
        double time = (t - timeAngle0) * 1000;
        double angle = orbitalVelocity * time; //radians
        double r = (this.a * (1 - Math.pow(orbitalEccentricity,2)/(1+orbitalEccentricity * Math.cos(angle))));
        return new PolarCoordinates(r,angle);
    }

    public double gravForce(SpaceObject o){
        double mass = Math.max(this.mass,o.mass);
        double distance = Math.abs(this.distanceToSun - o.distanceToSun);
        double force = (gravitationalConstant * mass)/(Math.pow(distance, 2));
        return force;
    }

    public CartesianCoordinates polarToCartesian(PolarCoordinates pC){
        if (this.isMoon()){
            PolarCoordinates p = this.getMoonOf().orbitPos();
            x = p.getR() * Math.cos(p.getAngle()) - pC.getR() * Math.cos(pC.getAngle());
            y = p.getR() * Math.sin(p.getAngle()) - pC.getR() * Math.sin(pC.getAngle());
            z = Math.sin(p.getOrbitalInclination()) * p.getR() - Math.sin(this.getOrbitalInclination()) * this.getR() ;
        }
        else {
            double x = pC.getR() * Math.cos(pC.getAngle());
            double y = pC.getR() * Math.sin(pC.getAngle());
            double z = Math.sin(this.getOrbitalInclination()) * pC.getR();
        }
        return new CartesianCoordinates(x, y, z);
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * @param diameter the diameter to set
     */
    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    /**
     * @return the escapeVelocity
     */
    public double getEscapeVelocity() {
        return escapeVelocity;
    }

    /**
     * @param escapeVelocity the escapeVelocity to set
     */
    public void setEscapeVelocity(double escapeVelocity) {
        this.escapeVelocity = escapeVelocity;
    }

    /**
     * @return the gravity
     */
    public double getGravity() {
        return gravity;
    }

    /**
     * @param gravity the gravity to set
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    /**
     * @return the distanceToSun
     */
    public double getDistanceToSun() {
        return distanceToSun;
    }

    /**
     * @param distanceToSun the distanceToSun to set
     */
    public void setDistanceToSun(double distanceToSun) {
        this.distanceToSun = distanceToSun;
    }

    /**
     * @return the orbitalPeriod
     */
    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    /**
     * @param orbitalPeriod the orbitalPeriod to set
     */
    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    /**
     * @return the orbitalVelocity
     */
    public double getOrbitalVelocity() {
        return orbitalVelocity;
    }

    /**
     * @param orbitalVelocity the orbitalVelocity to set
     */
    public void setOrbitalVelocity(double orbitalVelocity) {
        this.orbitalVelocity = orbitalVelocity;
    }

    /**
     * @return the orbitalInclination
     */
    public double getOrbitalInclination() {
        return orbitalInclination;
    }

    /**
     * @param orbitalInclination the orbitalInclination to set
     */
    public void setOrbitalInclination(double orbitalInclination) {
        this.orbitalInclination = orbitalInclination;
    }

    /**
     * @return the orbitalEccentricity
     */
    public double getOrbitalEccentricity() {
        return orbitalEccentricity;
    }

    /**
     * @param orbitalEccentricity the orbitalEccentricity to set
     */
    public void setOrbitalEccentricity(double orbitalEccentricity) {
        this.orbitalEccentricity = orbitalEccentricity;
    }

    /**
     * @return the numberOfMoons
     */
    public int getNumberOfMoons() {
        return numberOfMoons;
    }

    /**
     * @param numberOfMoons the numberOfMoons to set
     */
    public void setNumberOfMoons(int numberOfMoons) {
        this.numberOfMoons = numberOfMoons;
    }

    /**
     * @return the moonList
     */
    public SpaceObject[] getMoonList() {
        return moonList;
    }

    /**
     * @param moonList the moonList to set
     */
    public void setMoonList(SpaceObject[] moonList) {
        this.moonList = moonList;
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * @return the timeAngle0
     */
    public double getTimeAngle0() {
        return timeAngle0;
    }

    /**
     * @param timeAngle0 the timeAngle0 to set
     */
    public void setTimeAngle0(double timeAngle0) {
        this.timeAngle0 = timeAngle0;
    }

    /**
     * @return the pCoordinates
     */
    public PolarCoordinates getpCoordinates() {
        return pCoordinates;
    }

    /**
     * @param pCoordinates the pCoordinates to set
     */
    public void setpCoordinates(PolarCoordinates pCoordinates) {
        this.pCoordinates = pCoordinates;
    }

    /**
     * @return the cCoordinates
     */
    public CartesianCoordinates getcCoordinates() {
        return cCoordinates;
    }

    /**
     * @param cCoordinates the cCoordinates to set
     */
    public void setcCoordinates(CartesianCoordinates cCoordinates) {
        this.cCoordinates = cCoordinates;
    }

    /**
     * @return the isMoon
     */
    public boolean isMoon() {
        return isMoon;
    }

    /**
     * @param isMoon the isMoon to set
     */
    public void setMoon(boolean isMoon) {
        this.isMoon = isMoon;
    }

    /**
     * @return the moonOf
     */
    public SpaceObject getMoonOf() {
        return moonOf;
    }

    /**
     * @param moonOf the moonOf to set
     */
    public void setMoonOf(SpaceObject moonOf) {
        this.moonOf = moonOf;
    }
}