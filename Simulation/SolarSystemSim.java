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


public class SolarSystemSim extends Application {
    private static final int WIDTH=1400;
    private static final int HEIGHT=800;
    private static int index=0;
    private static int index1=0;
    public void start (Stage primaryStage){
        //Test spheres, so we have an idea of the camera's position.
        Sphere sphere1= new Sphere(900);
        Sphere sphere2= new Sphere(500);

        //The probe.
        Box probe=new Box(20,20,20);
        Group group = new Group();
        group.getChildren().add(sphere1);
        group.getChildren().add(sphere2);
        group.getChildren().add(probe);

        Camera camera= new PerspectiveCamera();
        Scene scene= new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        Rotate ry= new Rotate(-90,Rotate.X_AXIS);

        //Feel free to update this as you add planets.
        camera.translateYProperty().set(-10000);
        camera.getTransforms().add(ry);
        scene.setCamera(camera);

            //Properties of the test spheres.
            sphere1.translateXProperty().set(WIDTH/0.9);
            sphere1.translateYProperty().set(HEIGHT / 2);
            sphere1.translateZProperty().set(-400);
            sphere2.translateXProperty().set(-10*WIDTH);
            sphere2.translateYProperty().set(50*HEIGHT);
            Point3D axis=new Point3D(675,680,10);

            //Properties of the probe.
            probe.translateXProperty().set(WIDTH/2);
            probe.translateYProperty().set(WIDTH/2);
            probe.translateZProperty().set(-400);

        //Also, rotation of the spheres. Can also delete since the SpaceObject class will contain this property.
        RotateTransition rt= new RotateTransition(Duration.millis(3000),sphere1);
        rt.setByAngle(180);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        rt.setAxis(axis);
        rt.play();

        //Array of positions for the test spheres.
        int[] sphere2position= new int[140];
        for(int i=0; i<sphere2position.length;i++){
            sphere2position[i]=i*-20;
        }
        int[] sphere1position= new int[140];
        for(int i=0; i<sphere1position.length;i++){
            sphere1position[i]=i*-100;
        }


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,event2 ->{
            switch(event2.getCode()){
                case A:
                    if(index<140 && index>0) {
                        sphere1.translateZProperty().set(sphere1position[index]);
                        sphere2.translateZProperty().set(sphere2position[index]);
                        index++;
                    }else if(index==0){
                        index++;
                    }else{}
                    break;
                case D:
                    if(index>0){
                        sphere1.translateZProperty().set(sphere1position[index]);
                    sphere2.translateZProperty().set(sphere2position[index]);
                        index--;
                    }
                    break;
            }
        });

        primaryStage.setTitle("Mission Impossible");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }

}
