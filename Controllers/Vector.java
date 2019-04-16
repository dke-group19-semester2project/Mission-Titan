public class Vector {
    double x;
    double y;
    public Vector (double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getEuclideanLength () {
        double length = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        return length;
    }
    public Vector dividedBy (double denominator) {
        double unitX = x/denominator;
        double unitY = y/denominator;
        Vector unitVector = new Vector(unitX, unitY);
        return unitVector;
    }
    public Vector multipliedBy (double multiplier) {
        double newX = x*multiplier;
        double newY = y*multiplier;
        Vector product = new Vector(newX, newY);
        return product;
    }
    public String toString () {
        String newString = "X = " + x + "   Y = " + y;
        return newString;
    }
}
