/**
 * Probe
 */
public class Probe extends Planet{

    private double minDist;
    private double fitness;
    private double startX;
    private double startY;
    private double startZ;
    private double startVX;
    private double startVY;
    private double startVZ;

    public Probe(double mass, double xPosition, double yPosition, double zPosition, double xVelocity, double yVelocity, double zVelocity){
        this.super(mass, xPosition, yPosition, zPosition, xVelocity, yVelocity, zVelocity);
    }

    public Probe(double mass, double xPosition, double yPosition, double zPosition, double xVelocity, double yVelocity, double zVelocity,double minDist, double fitness){
        this.super(mass, xPosition, yPosition, zPosition, xVelocity, yVelocity, zVelocity);
        this.fitness = fitness;
        this.minDist = minDist;
    }

    public Probe clone(){
        return new Probe(this.getMass(),this.getxPosition(),this.getyPosition(),this.getzPosition(),this.getxVelocity(),this.getyVelocity(),this.getzVelocity(),this.fitness,this.minDist);
    }

    public void startPos(double x,double y,double z){
        this.startX = x;
        this.startY = y;
        this.startZ = z;
    }

    public void startVel(double x,double y,double z){
        this.startVX = x;
        this.startVY = y;
        this.startVZ = z;
    }

    /**
     * @return the minDist
     */
    public double getMinDist() {
        return minDist;
    }

    /**
     * @param minDist the minDist to set
     */
    public void setMinDist(double minDist) {
        this.minDist = minDist;
    }

    /**
     * @return the fitness
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * @param fitness the fitness to set
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * @return the startX
     */
    public double getStartX() {
        return startX;
    }

    /**
     * @param startX the startX to set
     */
    public void setStartX(double startX) {
        this.startX = startX;
    }

    /**
     * @return the startY
     */
    public double getStartY() {
        return startY;
    }

    /**
     * @param startY the startY to set
     */
    public void setStartY(double startY) {
        this.startY = startY;
    }

    /**
     * @return the startZ
     */
    public double getStartZ() {
        return startZ;
    }

    /**
     * @param startZ the startZ to set
     */
    public void setStartZ(double startZ) {
        this.startZ = startZ;
    }

    /**
     * @return the startVX
     */
    public double getStartVX() {
        return startVX;
    }

    /**
     * @param startVX the startVX to set
     */
    public void setStartVX(double startVX) {
        this.startVX = startVX;
    }

    /**
     * @return the startVY
     */
    public double getStartVY() {
        return startVY;
    }

    /**
     * @param startVY the startVY to set
     */
    public void setStartVY(double startVY) {
        this.startVY = startVY;
    }

    /**
     * @return the startVZ
     */
    public double getStartVZ() {
        return startVZ;
    }

    /**
     * @param startVZ the startVZ to set
     */
    public void setStartVZ(double startVZ) {
        this.startVZ = startVZ;
    }


}