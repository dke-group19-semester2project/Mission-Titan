public class Rocket extends SpaceObject {
    private Vector3D position;
    private Vector3D velocity;

    private Thruster mainThruster;
    private Thruster rightThruster;
    private Thruster leftThruster;

    private Controller openLoopController;
    private Controller closedLoopController;


    public Rocket(double px, double py, double pz, double vx, double vy, double vz, double mass) {
        super(px, py, pz, vx, vy, vz, mass);
    }
}
