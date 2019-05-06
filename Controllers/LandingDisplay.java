import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * TODO: Build a controller program which takes as input the impulse from thrusters over time and outputs landing conditions
 * TODO: In the body class add a method to calculate impulse vector & update the other methods to take that into account
 */
public class LandingDisplay extends JComponent{
    private static double minDistance;
    private static int minDistanceTime;
    private static ArrayList<Body> bodies = new ArrayList<Body>();
    static LandingDisplay display;
    public static void main(String[] args) {
        // Set-up
        Body titan = new Body(new Vector(0,0), new Vector(0,0), 1.3452E23);
        bodies.add(titan);
        Body probe = new Body(new Vector(400000,0), new Vector(0,2000), 5000);
        bodies.add(probe);

//        for (int i=0; i<10000; i++) {
//            probe.updatePositionAndVelocity(1, titan);
//            System.out.println("Current position: " + probe.getPosition().toString());
//        }
//
//        System.out.println("Initial position: Titan\n" + titan.getPosition().toString());
//        System.out.println("Initial velocity: Titan\n" + titan.getVelocity().toString());
//        System.out.println("Initial position: Probe\n" + probe.getPosition().toString());
//        System.out.println("Initial velocity: Probe\n" + probe.getVelocity().toString());
//        System.out.println("Position updates: Probe");
        JFrame frame = new JFrame();
        display = new LandingDisplay();
        frame.add(display);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        double startYVelocity = optimiseStartParameter(0, 3000);

        // Update position without visualising
//        Vector probeStartPosition = probe.getPosition();
//        Vector titanPosition = titan.getPosition();
//
//        do {
//            probe.updatePositionAndVelocity(1, titan);
//            double newDistance = probe.getDistanceFrom(titan);
//            System.out.println("Current distance: Probe\n" + newDistance);
//            System.out.println("Current position: Probe\n" + probe.getPosition().toString());
//            //System.out.println("Current velocity: Probe\n" + probe.getVelocity().toString());
//            probeStartPosition = probe.getPosition();
//        } while (probeStartPosition.y>titanPosition.y);

        // Visualisation:
//        JFrame frame = new JFrame();
//        display = new LandingDisplay();
//        frame.add(display);
//        frame.setSize(800, 800);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//
//        Timer timer = new Timer(50, (ActionEvent event) -> {
//            //TODO: modify so the positions of all the bodies are updated
//            probe.updatePositionAndVelocity(1, titan);
//            double newDistance = probe.getDistanceFrom(titan);
//            if (minDistance==0 || newDistance<minDistance) {
//                minDistance = newDistance;
//                int currentTime = probe.simulationTime;
//                minDistanceTime = currentTime;
//                System.out.println("New minimum distance at time " + currentTime);
//                System.out.println("Current minimum distance: " + minDistance);
//            }
//            System.out.println(probe.getPosition().toString());
//            System.out.println("Velocity:   " + probe.getVelocity().toString());
//            display.repaint();
//        });
//        timer.start();
//        frame.setVisible(true);
    }
    /*
      Use bisection method to find the starting y-velocity that lands the probe on Titan within one orbital cycle.
    */
    public static double optimiseStartParameter (double lowerYVelocity, double higherYVelocity) {
        double sufficientStartYVelocity = 0;
        double testingYVelocity = (lowerYVelocity+higherYVelocity)/2;
        boolean probeHasLanded = tryToLandWith(testingYVelocity);
        if (probeHasLanded) {
            sufficientStartYVelocity = testingYVelocity;
        } else {
            sufficientStartYVelocity = optimiseStartParameter(lowerYVelocity, testingYVelocity);
        }
        return sufficientStartYVelocity;
    }
    public static boolean tryToLandWith (double testingYVelocity) {
        System.out.println("Trying to land with y-velocity " + testingYVelocity);
        boolean hasLanded = false;
        Body titan = bodies.get(0);
        Body probe = new Body(new Vector(400000,0), new Vector(0, testingYVelocity), 5000);
        bodies.set(1, probe);
        Vector probeStartPosition = probe.getPosition();
        Vector titanPosition = titan.getPosition();
        double titanRadius = 2575;
        //display.repaint();
        System.out.println("Display");
        for (int i=0; i<200; i++) {
            probe.updatePositionAndVelocity(1, titan);
//            if(i % 100 == 0) {
                //System.out.print(i+ ": ");
                //System.out.println("Current position: Probe\n" + probe.getPosition().toString());
                display.repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
//            }
            double newDistance = probe.getDistanceFrom(titan);
            if (newDistance<=titanRadius) {
                hasLanded = true;
                System.out.println("The probe has landed.");
                System.out.println("Current distance: Probe\n" + newDistance);
                System.out.println("Current velocity: Probe\n" + probe.getVelocity().toString());
                break;
            }
        }

//        do {
//            probe.updatePositionAndVelocity(1, titan);
//            double newDistance = probe.getDistanceFrom(titan);
//
//            //System.out.println("Current position: Probe\n" + probe.getPosition().toString());
//            if (newDistance<=titanRadius) {
//                hasLanded = true;
//                System.out.println("The probe has landed.");
//                System.out.println("Current distance: Probe\n" + newDistance);
//                System.out.println("Current velocity: Probe\n" + probe.getVelocity().toString());
//                break;
//            }
//            probeStartPosition = probe.getPosition();
//        } while (probeStartPosition.y>titanPosition.y);
        if (!hasLanded) {
            System.out.println("Did not manage to land. ");
        }
        return hasLanded;
    }
    @Override
    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        for (Body body: bodies) {
            Vector position = body.getPosition();
            int x = (int) position.x/1500 + 400;
            int y = (int) position.y/1500 + 400;
            g2.fillOval(x, y, 10, 10);
        }

    }
    class DisplayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
