//This is the bird's eye view of the solar system.

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.lang.Math;
import javafx.scene.paint.PhongMaterial;


public class SolarSystemSim extends Application {
    private static final int WIDTH=1400;
    private static final int HEIGHT=800;
    private static int AU=149597871;
    private static int index=0;
    public void start (Stage primaryStage){
        //Jupiter
        SpaceObject jupiter= new SpaceObject(1898,778.6,13.1,0.049,778.57);
        jupiter.setDiameter(142984);
        PhongMaterial jupiterMat=new PhongMaterial();
        jupiterMat.setDiffuseColor(Color.ORANGE);
        jupiter.setMaterial(jupiterMat);

        jupiter.setTimeAngle0(0);
        PolarCoordinates jupiterpc=jupiter.orbitPos();
        CartesianCoordinates jupitercc=jupiter.polarToCartesian(jupiterpc);

        jupiter.translateXProperty().set(jupitercc.getX());
        jupiter.translateYProperty().set(jupitercc.getY());
        jupiter.translateZProperty().set(jupitercc.getZ());

        //Saturn
        SpaceObject saturn=new SpaceObject(568,1433.5,9.7,0.057,1433.53);
        saturn.setDiameter(120536);
        PhongMaterial saturnMat=new PhongMaterial();
        saturnMat.setDiffuseColor(Color.YELLOW);
        saturn.setMaterial(saturnMat);

        saturn.setTimeAngle0(0);
        PolarCoordinates saturnpc=saturn.orbitPos();
        CartesianCoordinates saturncc=saturn.polarToCartesian(saturnpc);

        saturn.translateXProperty().set(saturncc.getX());
        saturn.translateYProperty().set(saturncc.getY());
        saturn.translateZProperty().set(saturncc.getZ());

        //Uranus
        SpaceObject uranus=new SpaceObject(86.8,2872.5,6.8,0.046,2872.46);
        uranus.setDiameter(51118);
        PhongMaterial uranusMat= new PhongMaterial();
        uranusMat.setDiffuseColor(Color.LIGHTBLUE);
        uranus.setMaterial(uranusMat);

        uranus.setTimeAngle0(0);
        PolarCoordinates uranuspc=uranus.orbitPos();
        CartesianCoordinates uranuscc=uranus.polarToCartesian(uranuspc);

        uranus.translateXProperty().set(uranuscc.getX());
        uranus.translateYProperty().set(uranuscc.getY());
        uranus.translateZProperty().set(uranuscc.getZ());

        //Neptune
        SpaceObject neptune= new SpaceObject(102,4495.1,5.4,0.011,4495.06);
        neptune.setDiameter(49528);
        PhongMaterial neptuneMat=new PhongMaterial();
        neptuneMat.setDiffuseColor(Color.BLUE);
        neptune.setMaterial(neptuneMat);

        neptune.setTimeAngle0(0);
        PolarCoordinates neptunepc=neptune.orbitPos();
        CartesianCoordinates neptunecc= neptune.polarToCartesian(neptunepc);

        neptune.translateXProperty().set(neptunecc.getX());
        neptune.translateYProperty().set(neptunecc.getY());
        neptune.translateZProperty().set(neptunecc.getZ());

        System.out.println(saturncc.getX());
        System.out.println(saturncc.getY());
        System.out.println(saturncc.getZ());
        System.out.println(jupitercc.getX());
        System.out.println(jupitercc.getY());
        System.out.println(jupiter.getTranslateZ());


        //The probe.
        Box probe=new Box(20,20,20);
        Group group = new Group();
        group.getChildren().add(jupiter);
        group.getChildren().add(saturn);
        group.getChildren().add(uranus);
        group.getChildren().add(neptune);
        group.getChildren().add(probe);

        Camera camera= new PerspectiveCamera();
        Scene scene= new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        Rotate ry= new Rotate(-90,Rotate.X_AXIS);

        //Feel free to update this as you add planets.
        camera.translateZProperty().set(400);
        camera.translateYProperty().set(-3800000);
        camera.translateXProperty().set(-70);
        camera.setFarClip(900000000);
        camera.getTransforms().add(ry);
        scene.setCamera(camera);

            Point3D axis=new Point3D(675,680,10);



            //Properties of the probe.
            probe.translateXProperty().set(WIDTH/2);
            probe.translateYProperty().set(WIDTH/2);
            probe.translateZProperty().set(-400);

        //Also, rotation of the spheres. Can also delete since the SpaceObject class will contain this property.
        RotateTransition rt= new RotateTransition(Duration.millis(3000),jupiter);
        rt.setByAngle(180);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        rt.setAxis(axis);
        rt.play();

        //Array of positions for the test spheres.
        int[] sphere2position= new int[1400];
        for(int i=0; i<sphere2position.length;i++){
            sphere2position[i]=i*2;
        }
        double[] sphere1position= new double[1400];
        for(int i=0; i<sphere1position.length;i++){
            double tmp= (double) sphere2position[i];
            sphere1position[i]=i*10*Math.log((Math.pow(tmp,2)-1));
        }

        primaryStage.setTitle("Mission Impossible");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }

}
