import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.geometry.Point3D;


public class Sphere3D extends Application {
    private static final int WIDTH=1400;
    private static final int HEIGHT=800;
    private static int index=0;
    private static int index1=0;
    public void start (Stage primaryStage){
        Sphere sphere1= new Sphere(700);
        Sphere sphere2= new Sphere(50);
        Box probe=new Box(50,50,50);
        Group group = new Group();
        group.getChildren().add(sphere1);
        group.getChildren().add(sphere2);
        group.getChildren().add(probe);

        Camera camera= new PerspectiveCamera();
        Scene scene= new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        primaryStage.setTitle("Genuine Coder");
        primaryStage.setScene(scene);
        primaryStage.show();

            sphere1.translateXProperty().set(WIDTH/0.9);
            sphere1.translateYProperty().set(HEIGHT / 2);
            sphere2.translateXProperty().set(WIDTH / 4);
            sphere2.translateYProperty().set(HEIGHT / 4);
            Point3D axis=new Point3D(675,680,10);
            probe.translateXProperty().set(WIDTH/2);
            probe.translateYProperty().set(WIDTH/2);

        RotateTransition rt= new RotateTransition(Duration.millis(3000),sphere1);
        rt.setByAngle(180);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        rt.setAxis(axis);
        rt.play();
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
