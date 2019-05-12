import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LandingDisplay extends JComponent{
    private static double minDistance;
    private static int minDistanceTime;
    private static ArrayList<Body> bodies = new ArrayList<Body>();
    static LandingDisplay display;
    static double titanRadius = 2575*1000;
    static double startingDistance = titanRadius+800*1000;
    public static void main(String[] args) {
        // Set-up
        Body titan = new Body(new Vector(0,0), new Vector(0,0), 1.3452E23, 2*titanRadius);
        bodies.add(titan);
        Body probe = new Body(new Vector(8000000,0), new Vector(0, 100), 5000, 1);
        bodies.add(probe);

        JFrame frame = new JFrame();
        display = new LandingDisplay();
        frame.add(display);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //double startYVelocity = optimiseStartParameter(0, 3000);
        Vector deltaV = probe.getVelocity().multipliedBy(0.5);
        //simulateLanding(deltaV, 1);
        solveDeltaV(1);


    }

/*
    solveDeltaV is a method intended to solve for a CONSTANT multiplier for the DeltaV which would lead to landing safely.
 */
    public static void solveDeltaV (int delayBetweenThrusterUse) {
        // DeltaV is understood in this context as purely the change in velocity.
        // Want to find the constant deltaV vector which will be applied to the probe every time the thruster is used.
        // For now the presumption is that thrusters are applied constantly, i.e. with every update of position.
        // DeltaV will be defined so that it is collinear with the velocity vector.
        // For decreasing velocity, DeltaV equals -a*velocity, with 0<a<1. For increasing velocity, DeltaV equals a*velocity.
        // If this approach does not work: try approaching deltaV through its (second) derivative instead.
        // Starting bracket for DeltaV: [0, 1] (find a value between no change to velocity and stopping the spacecraft completely).
        double minDeltaVMultiplier = 0.0;
        double maxDeltaVMultiplier = 0.001;
        double bracketMidPoint = (minDeltaVMultiplier+maxDeltaVMultiplier)/2;
        Vector landingVelocity = simulateLanding(0.25/*bracketMidPoint*/, delayBetweenThrusterUse);
        while (Math.abs(landingVelocity.x)>7 || Math.abs(landingVelocity.y)>7) {
            minDeltaVMultiplier = bracketMidPoint;
            bracketMidPoint = (minDeltaVMultiplier+maxDeltaVMultiplier)/2;
            landingVelocity = simulateLanding(bracketMidPoint, delayBetweenThrusterUse);
        }
    }
    public static Vector simulateLanding(double deltaVMultiplier, int delayBetweenThrusterUse) {
        boolean hasLanded = false;
        Body titan = bodies.get(0);
        Body probe = new Body(new Vector(startingDistance,0), new Vector(0, 2000), 5000, 1);
        bodies.set(1, probe);
        Vector probeStartPosition = probe.getPosition();
        Vector titanPosition = titan.getPosition();
        Vector deltaV = probe.getVelocity().multipliedBy(-deltaVMultiplier);

        // Run upates and visualise with delay of 50 ms between updates
        for (int i=0; i<1000000; i++) {
            if (i%delayBetweenThrusterUse==0) {
                probe.useMainThrusters(deltaV);
                deltaV = probe.getVelocity().multipliedBy(-deltaVMultiplier);
            }
            probe.updatePositionAndVelocity(1, titan);
            if(i % 100 == 0) {
                display.repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            double newDistance = probe.getDistanceFrom(titan);
            System.out.println("Current distance: " + newDistance);
            if (newDistance<=titanRadius) {
                hasLanded = true;
                System.out.println("The probe has landed.");
                System.out.println("Current distance: Probe\n" + newDistance);
                System.out.println("Current velocity: Probe\n" + probe.getVelocity().toString());
                break;
            }
        }
        if (!hasLanded) {
            System.out.println("Did not manage to land. ");
        }

        System.exit(1);
        return probe.getVelocity();
    }
    public static void solveThrusterFrequency (Vector deltaV) {
        /* To be implemented only if we decide not to use thrusters constantly. DeltaV or at least its rate of change must be known. */
    }
    @Override
    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        for (Body body: bodies) {
            Vector position = body.getPosition();
            int scale = 16000;
            int size = Math.max(10, (int)body.getDiameter() / scale);
            int x = (int) position.x/ scale + 400 - size/2;
            int y = (int) position.y/ scale + 400 - size/2;
            g2.fillOval(x, y, size, size);
        }

    }
    class DisplayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
