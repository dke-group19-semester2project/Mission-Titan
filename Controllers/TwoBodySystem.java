/**
 * This class simulates the spacecraft orbiting Titan (in isolation from the rest of the solar system).
 * Titan is presumed as fixed at the origin (zero-velocity), while the spacecraft is orbiting with an initial velocity.
 * The trajectory can be calculated with an open-loop controller that takes the force of the thrusters as a parameter.
 * (For now the thrusters are only used to slow down the orbital velocity but later can be updated to ensure safe landing.)
 * The current model only models the acceleration and NOT the attitude of the probe (using torque).
 */
public class TwoBodySystem {
    private double G = 6.674E-11; // unit: m3⋅kg−1⋅s−2 (gravitational constant)
    private Body titan;
    private Body probe;
    public TwoBodySystem () {
        titan = new Body(new Vector(0,0), new Vector(0,0), 1.3452E23);
        probe = new Body(new Vector(400,0), new Vector(0,5), 5000);
    }


}
