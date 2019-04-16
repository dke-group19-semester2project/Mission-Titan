import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ControllerTest {
    public static void main(String[] args) {
        Body titan = new Body(new Vector(0,0), new Vector(0,0), 1.3452E23);
        Body probe = new Body(new Vector(400000,0), new Vector(0,0), 5000);
        System.out.println("Initial position: Titan\n" + titan.getPosition().toString());
        System.out.println("Initial velocity: Titan\n" + titan.getVelocity().toString());
        System.out.println("Initial position: Probe\n" + probe.getPosition().toString());
        System.out.println("Initial velocity: Probe\n" + probe.getVelocity().toString());
        System.out.println("Position updates: Probe");
        for (int i=0; i<20; i++) {
            probe.updatePositionAndVelocity(1, titan);
            System.out.println(probe.getPosition().toString());
            //System.out.println("Velocity:   " + probe.getVelocity().toString());
        }

    }
}
