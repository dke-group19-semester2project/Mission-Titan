/**
 * This class is used to simulate Titan and the spacecraft orbiting Titan (in isolation from the rest of the solar system).
 * Titan is presumed as fixed at the origin (zero-velocity), while the spacecraft is orbiting with an initial velocity.
 * The trajectory can be calculated with an open-loop controller that takes the force of the thrusters as a parameter.
 * (For now the thrusters are only used to slow down the orbital velocity but later can be updated to ensure safe landing.)
 * The current model only models the acceleration and NOT the attitude of the probe (using torque).
 */
import java.util.ArrayList;

public class Body {
    public static final double G = 6.674E-11; // unit: m3⋅kg−1⋅s−2 (gravitational constant)
    private Vector position; // Cartesian coordinates as m values.
    private Vector velocity; // Cartesian coordinates as m/s.
    double massInKg;
    double diameter;
    public static int simulationTime = 0;

    public Body (Vector initialPosition, Vector initialVelocity, double mass, double diameter) {
        position = initialPosition;
        velocity = initialVelocity;
        massInKg = mass;
        this.diameter = diameter;
    }
    public double getDiameter () {
        return diameter;
    }
    public Vector getPosition () {
        return position;
    }
    public Vector getVelocity () {
        return velocity;
    }
    /*
    Use force and acceleration to update the position and velocity vectors based on the time step
    TODO: update this class to take into account the impulse from thrusters
    TODO: update to take into account the effect of other celestial bodies
     */
    /*
        The objective of method useMainThrusters is to update velocity (without taking another time step).
        For now the spacecraft is presumed to be correctly oriented to either increase or reduce the speed.
     */
    public void useMainThrusters (Vector deltaV) {
        velocity = sumOf(velocity, deltaV);
    }
    public void updatePositionAndVelocity (int time, Body attractingBody) {
        simulationTime = simulationTime+time;
        Vector acceleration = getAcceleration(attractingBody);
        Vector changeInVelocity = getChangeInVelocity(acceleration, time);
        Vector updatedVelocity = sumOf(this.getVelocity(), changeInVelocity);
        this.velocity = updatedVelocity;
        Vector changeInPosition = getChangeInPosition(updatedVelocity, time);
        Vector updatedPosition = sumOf(this.getPosition(), changeInPosition);
        this.position = updatedPosition;
    }
    public Vector getChangeInVelocity (Vector acceleration, int time) {
        return acceleration.multipliedBy(time);
    }
    public Vector getChangeInPosition (Vector velocity, int time) {
        return velocity.multipliedBy(time);
    }
    public Vector getAcceleration (Body attractingBody) {
        // F=ma => a=F/m
        Vector force = getForceAsVector(attractingBody);
        Vector acceleration = force.dividedBy(this.massInKg); // Force is divided by the mass of the accelerating body
        return acceleration;
    }
    public Vector getForceAsVector (Body attractingBody) {
        double magnitude = computeForceMagnitude(attractingBody);
        Vector direction = computeForceDirection(attractingBody);
        Vector forceVector = direction.multipliedBy(magnitude);
        return forceVector;
    }
    /*
    Magnitude of the force as a scalar value
     */
    public double computeForceMagnitude (Body attractingBody) {
        double distance = getDistanceFrom(attractingBody);
        double forceMagnitude = -(G*this.massInKg*attractingBody.massInKg)/Math.pow(distance,2);
        return forceMagnitude;
    }
    public double getDistanceFrom (Body attractingBody) {
        Vector distanceVector = differenceOf(this.getPosition(), attractingBody.getPosition());
        double distance = distanceVector.getEuclideanLength();
        return distance;
    }
    /*
    Direction of the force as a unit vector
     */
    public Vector computeForceDirection (Body attractingBody) {
        Vector distanceVector = differenceOf(this.getPosition(), attractingBody.getPosition());
        double distance = distanceVector.getEuclideanLength();
        Vector unitVector = distanceVector.dividedBy(distance);
        return unitVector;
    }
    public Vector sumOf(Vector v, Vector u) {
        double newX = v.x + u.x;
        double newY = v.y + u.y;
        Vector sum = new Vector(newX, newY);
        return sum;
    }
    public Vector differenceOf(Vector v, Vector u) {
        double newX = v.x-u.x;
        double newY = v.y-u.y;
        Vector difference = new Vector (newX, newY);
        return difference;
    }
}
