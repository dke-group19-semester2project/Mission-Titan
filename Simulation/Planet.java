public class Planet {
    
    private String name;
    private double xPosition; // when creating an object
    private double yPosition; // take initial position and velocity
    private double zPosition; // from the same date for every planet
    private double xVelocity; // for example, 01-01-2000
    private double yVelocity;
    private double zVelocity;
    private double mass;
    private final static double G = 6.67408*10e-11;

    public Planet (double mass, double xPosition, double yPosition, double zPosition, double xVelocity, double yVelocity, double zVelocity) {
        this.mass = mass;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
    }

    public double distance(Planet o) {
        double deltaX = o.getxPosition() - this.xPosition;
        double deltaY = o.getyPosition() - this.yPosition;
        double deltaZ = o.getzPosition() - this.zPosition;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ *deltaZ);
    }

    public double xPosition(Planet o,double time) {
        double r = distance(o);
        double force = (G*o.getMass()*this.mass)/(r*r);
        xPosition = xPosition + (time * (xVelocity + (force/this.mass)*time));
        xVelocity = xVelocity + ((force/this.mass)*time);
        return xPosition;
    }
    public double yPosition(Planet o,double time) {
        double r = distance(o);
        double force = (G*o.getMass()*this.mass)/(r*r);
        yPosition = yPosition + (time * (yVelocity + (force/this.mass)*time));
        yVelocity = yVelocity + ((force/this.mass)*time);
        return yPosition;
    }
    public double zPosition(Planet o,double time) {
        double r = distance(o);
        double force = (G*o.getMass()*this.mass)/(r*r);
        zPosition = zPosition + (time * (zVelocity + (force/this.mass)*time));
        zVelocity = zVelocity + ((force/this.mass)*time);
        return zPosition;
    }

    public static void main (String args[]) {
        Planet sun = new Planet(2*10e30,0.0,0.0,0.0,0.0,0.0,0.0);
        Planet mercury = new Planet(3.302*10e23, -2.105262111032039E+10, -6.640663808353403E+10, -3.492446023382954E+09,  3.665298706393840E+04, -1.228983810111077E+04, -4.368172898981951E+03);
        for (int i = 0; i<86400; i++) {
            mercury.xPosition(sun,1);
            mercury.yPosition(sun,1);
            mercury.zPosition(sun,1);
        }
        System.out.println(mercury.xPosition);
        System.out.println(mercury.yPosition);
        System.out.println(mercury.zPosition);
    }

    /**
     * @return the xPosition
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * @param xPosition the xPosition to set
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * @return the yPosition
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * @param yPosition the yPosition to set
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * @return the zPosition
     */
    public double getzPosition() {
        return zPosition;
    }

    /**
     * @param zPosition the zPosition to set
     */
    public void setzPosition(double zPosition) {
        this.zPosition = zPosition;
    }

    /**
     * @return the xVelocity
     */
    public double getxVelocity() {
        return xVelocity;
    }

    /**
     * @param xVelocity the xVelocity to set
     */
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * @return the yVelocity
     */
    public double getyVelocity() {
        return yVelocity;
    }

    /**
     * @param yVelocity the yVelocity to set
     */
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * @return the zVelocity
     */
    public double getzVelocity() {
        return zVelocity;
    }

    /**
     * @param zVelocity the zVelocity to set
     */
    public void setzVelocity(double zVelocity) {
        this.zVelocity = zVelocity;
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
     * @return the g
     */
    public double getG() {
        return G;
    }

    /**
     * @return the m
     */
    public double getM() {
        return M;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}