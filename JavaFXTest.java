import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JavaFXTest extends Application {
    private double WIDTH = 1000;
    private double HEIGHT = 1000;
    public void start(Stage primaryStage) throws Exception {



        double planetAUToPixelsMultiplier = Math.pow(10, 6)/2;
        double sunAUToPixelsMultiplier = Math.pow(10,4)/2;
        double distanceToPixelsMultiplier = (Math.pow(10,3));
        long startTime = System.currentTimeMillis();
        Sphere sun = new Sphere();
        sun.setTranslateX(0);
        sun.setTranslateY(0);
        sun.setRadius(0.00465*sunAUToPixelsMultiplier); //Based on sun radius (convert 1391000/2 km (kilometers) to astronomical units)×10^5/5

        // Mercury position on A.D. 2019-Mar-17 00:00:00
        double mercuryStartX = -3.884951255359090E-01;
        double mercuryStartY = -1.193703739273220E-02;
        double mercuryStartZ = 3.466414962075259E-02;
        Planet mercury = new Planet(3.302*10e23, mercuryStartX, mercuryStartY, mercuryStartZ,  (-4.989539396985841E-03/86400), (-2.691230619778414E-02/86400), (-1.741371578050843E-03/86400));
        Sphere mercurySphere = new Sphere();
        mercurySphere.setRadius(1.6308E-5*planetAUToPixelsMultiplier);
        mercurySphere.setTranslateX(mercuryStartX*distanceToPixelsMultiplier);
        mercurySphere.setTranslateY(mercuryStartY*distanceToPixelsMultiplier);
        System.out.println(mercurySphere.getRadius());
        System.out.println(mercurySphere.getTranslateX());
        System.out.println(mercurySphere.getTranslateY());
        System.out.println();

        Polygon polygon = new Polygon();
        Double[] mercuryTrajectory = mercury.getTrajectoryPoints(88);
        polygon.getPoints().addAll(mercuryTrajectory);

        PathTransition mercuryOrbit = new PathTransition();
        mercuryOrbit.setDuration(Duration.millis(15000));
        mercuryOrbit.setNode(mercurySphere);
        mercuryOrbit.setPath(polygon);
        mercuryOrbit.setInterpolator(Interpolator.LINEAR);
        mercuryOrbit.setCycleCount(Animation.INDEFINITE);
        mercuryOrbit.play();

        Group root = new Group(sun, mercurySphere);
        Scene scene = new Scene(root,WIDTH, HEIGHT);

        Camera camera= new PerspectiveCamera();
        camera.translateXProperty().set(-WIDTH/2);
        camera.translateYProperty().set(-HEIGHT/2);
        camera.translateZProperty().set(-1000);
        scene.setCamera(camera);

        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String args[]){
        launch(args);
    }

}
