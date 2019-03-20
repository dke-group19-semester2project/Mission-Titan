import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaFXTest extends Application {
    public void start(Stage primaryStage) throws Exception {
        long startTime = System.currentTimeMillis();
        Sphere sun = new Sphere();
        sun.setTranslateX(300.0f);
        sun.setTranslateY(300.0f);
        sun.setRadius(16.0f);

        Sphere earth = new Sphere();
        earth.setTranslateX(400);
        earth.setTranslateX(400);
        earth.setRadius(8);
        Sphere mercury = new Sphere ();
        mercury.setTranslateX(250);
        mercury.setTranslateY(250);
        mercury.setRadius(4.0f);

        PathTransition earthTransition = new PathTransition();
        earthTransition.setDuration(Duration.millis(15000));
        earthTransition.setNode(earth);
        earthTransition.setPath(new Ellipse(sun.getTranslateX(), sun.getTranslateY(), 50, 200));
        // TODO: Would need to know the angle of the elliptical orbit of each planet in relation to our absolute X and Y axes
        earthTransition.setCycleCount(Animation.INDEFINITE);
        earthTransition.setInterpolator(Interpolator.LINEAR);
        earthTransition.play();

        PathTransition mercuryTransition = new PathTransition();
        mercuryTransition.setDuration(Duration.millis(10000));
        mercuryTransition.setNode(mercury);
        mercuryTransition.setPath(new Ellipse(sun.getTranslateX(), sun.getTranslateY(), 50, 40));
        mercuryTransition.setCycleCount(Animation.INDEFINITE);
        mercuryTransition.setInterpolator(Interpolator.LINEAR);
        mercuryTransition.play();

        Group root = new Group(sun, earth, mercury);
        Scene scene = new Scene(root,600, 600);
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
    public static void main(String args[]){
        launch(args);
    }
}
