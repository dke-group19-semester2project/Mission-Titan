public class Thruster {

    private Direction direction;
    private double force;
    private double maxBurnTime = 0;
    private double burnedTime = 0;
    public double fuelUsed = 0;

    public Thruster(Direction direction, double force) {
        this.direction = direction;
        this.force = force;
    }

    public double getForce(double mass) {
        return force / mass;
    }

    public Vector2D activateSeconds(double seconds, double mass) {
        if (burnedTime < maxBurnTime) {
            return new Vector2D(
                    direction.getDir().multiplyConstant(force).multiplyConstant(seconds).divideConstant(mass));
            burn(seconds);
        }
    }

    public void burn(double seconds) {
        if (burnedTime < maxBurnTime) {
            burnedTime += seconds;
        }
    }

    public enum Direction {

        X_LEFT(new Vector2D(-1, 0)), X_RIGHT(new Vector2D(1, 0)), Y_FWD(new Vector2D(0, 1));

        private Vector2D dir;

        Direction(Vector2D dir) {
            this.dir = dir;
        }

        /**
         * @return the dir
         */
        public Vector2D getDir() {
            return dir;
        }

        /**
         * @param dir the dir to set
         */
        public void setDir(Vector2D dir) {
            this.dir = dir;
        }

    }

    /**
     * @return the force
     */
    public double getForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(double force) {
        this.force = force;
    }

    /**
     * @return the maxBurnTime
     */
    public double getMaxBurnTime() {
        return maxBurnTime;
    }

    /**
     * @param maxBurnTime the maxBurnTime to set
     */
    public void setMaxBurnTime(double maxBurnTime) {
        this.maxBurnTime = maxBurnTime;
    }

    /**
     * @return the burnedTime
     */
    public double getBurnedTime() {
        return burnedTime;
    }

    /**
     * @param burnedTime the burnedTime to set
     */
    public void setBurnedTime(double burnedTime) {
        this.burnedTime = burnedTime;
    }

    /**
     * @return the fuelUsed
     */
    public double getFuelUsed() {
        return fuelUsed;
    }

    /**
     * @param fuelUsed the fuelUsed to set
     */
    public void setFuelUsed(double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }
}
