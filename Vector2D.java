public class Vector2D {

    private double x;
    private double y;
    private double length;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.length = this.getLength();
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
        this.length = this.getLength();
    }

    public Vector2D addVector(Vector2D v) {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }

    public Vector2D addConstant(double d) {
        return new Vector2D(this.x + d, this.y + d);
    }

    public Vector2D substractVector(Vector2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }

    public Vector2D substractConstant(double d) {
        return new Vector2D(this.x - d, this.y - d);
    }

    public Vector2D multiplyVector(Vector2D v) {
        return new Vector2D(this.x * v.x, this.y * v.y);
    }

    public Vector2D multiplyConstant(double d) {
        return new Vector2D(this.x * d, this.y * d);
    }

    public Vector2D divideVector(Vector2D v) {
        return new Vector2D(this.x / v.x, this.y / v.y);
    }

    public Vector2D divideConstant(double d) {
        return new Vector2D(this.x / d, this.y / d);
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the length
     */
    public double getLength() {
        this.setLength(Math.sqrt(x*x + y*y));
        return this.length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }
}
