/**
 * PolarCoordinates
 */
public class PolarCoordinates {

    private double r;
    private double angle;

    public PolarCoordinates(double r,double angle){
        this.r = r;
        this.angle = angle;
    }

    public PolarCoordinates(){
        
    }

    /**
     * @return the r
     */
    public double getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(double r) {
        this.r = r;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }
}