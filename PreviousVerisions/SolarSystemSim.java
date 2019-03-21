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
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarSystemSim extends Application {
    private static final int WIDTH=1400;
    private static final int HEIGHT=800;
    private static int AU=149597871;
    private static int index=0;
    private static CartesianCoordinates[] jupiterArray = new CartesianCoordinates[14000];
    private static CartesianCoordinates[] saturnArray= new CartesianCoordinates[14000];
    private static CartesianCoordinates[] neptuneArray= new CartesianCoordinates[14000];
    private static CartesianCoordinates[] uranusArray = new CartesianCoordinates[14000];

    public void start (Stage primaryStage){
        //Test spheres, so we have an idea of the camera's position.

        //Jupiter
        SpaceObject jupiter= new SpaceObject(1898,778.6*10,13.1,0.049,778.57*10);
        jupiter.setDiameter(142984/10);
        PhongMaterial jupiterMat=new PhongMaterial();
        jupiterMat.setDiffuseColor(Color.ORANGE);
        jupiter.setMaterial(jupiterMat);

        jupiter.setTimeAngle0(0);
       /* PolarCoordinates jupiterpc=jupiter.orbitPos(0);
        CartesianCoordinates jupitercc=jupiter.polarToCartesian(jupiterpc);
        jupiterArray[0]=jupitercc;*/

        /*jupiter.translateXProperty().set(jupitercc.getX()*10);
        jupiter.translateYProperty().set(jupitercc.getZ()*10);
        jupiter.translateZProperty().set(jupitercc.getY()*10);*/

        //Saturn
        SpaceObject saturn=new SpaceObject(568,1433.5*10,9.7,0.057,1433.53*10);
        saturn.setDiameter(120536/10);
        PhongMaterial saturnMat=new PhongMaterial();
        saturnMat.setDiffuseColor(Color.YELLOW);
        saturn.setMaterial(saturnMat);

        saturn.setTimeAngle0(0);
        /*PolarCoordinates saturnpc=saturn.orbitPos();
        CartesianCoordinates saturncc=saturn.polarToCartesian(saturnpc);

        saturn.translateXProperty().set(saturncc.getX()*10);
        saturn.translateYProperty().set(saturncc.getZ()*10);
        saturn.translateZProperty().set(saturncc.getY()*10);*/

        //Uranus
        SpaceObject uranus=new SpaceObject(86.8,2872.5*10,6.8,0.046,2872.46*10);
        uranus.setDiameter(51118/10);
        PhongMaterial uranusMat= new PhongMaterial();
        uranusMat.setDiffuseColor(Color.LIGHTBLUE);
        uranus.setMaterial(uranusMat);

        uranus.setTimeAngle0(0);


        //Neptune
        SpaceObject neptune= new SpaceObject(102,4495.1*10,5.4,0.011,4495.06*10);
        neptune.setDiameter(49528/10);
        PhongMaterial neptuneMat=new PhongMaterial();
        neptuneMat.setDiffuseColor(Color.BLUE);
        neptune.setMaterial(neptuneMat);

        neptune.setTimeAngle0(0);


       /* System.out.println(saturncc.getX());
        System.out.println(saturncc.getY());
        System.out.println(saturncc.getZ());
        System.out.println(jupitercc.getX());
        System.out.println(jupitercc.getY());
        System.out.println(jupiter.getTranslateZ());*/


        //The probe.
        Box probe=new Box(4000,4000,4000);
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
        camera.translateYProperty().set(-1000000);
        camera.translateXProperty().set(-70);
        camera.setFarClip(900000000);
        camera.getTransforms().add(ry);
        scene.setCamera(camera);

          //  sphere2.translateXProperty().set(-10*WIDTH);
            //sphere2.translateYProperty().set(50*HEIGHT);
            Point3D axis=new Point3D(675,680,10);



            //Properties of the probe.

        //Also, rotation of the spheres. Can also delete since the SpaceObject class will contain this property.
        RotateTransition rt= new RotateTransition(Duration.millis(3000),jupiter);
        rt.setByAngle(180);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        rt.setAxis(axis);
        rt.play();

        //Array of positions for the test spheres.
        for(int i=0; i<jupiterArray.length;i++){
            PolarCoordinates jupiterPCC=jupiter.orbitPos(i*2260);
            CartesianCoordinates jupiterCCC=jupiter.polarToCartesian(jupiterPCC);
            jupiterArray[i]=jupiter.polarToCartesian(jupiterPCC);

            PolarCoordinates saturnpc=saturn.orbitPos(i*2260);
            saturnArray[i]=saturn.polarToCartesian(saturnpc);

            PolarCoordinates uranuspc=uranus.orbitPos(i*2260);
            uranusArray[i]=uranus.polarToCartesian(uranuspc);

            PolarCoordinates neptunepc=neptune.orbitPos(i*2260);
            neptuneArray[i]= neptune.polarToCartesian(neptunepc);

        }
        jupiter.translateXProperty().set(jupiterArray[0].getX()*10);
        jupiter.translateYProperty().set(jupiterArray[0].getZ()*10);
        jupiter.translateZProperty().set(jupiterArray[0].getY()*10);

        /*probe.translateXProperty().set(jupiterArray[0].getX()*10+10000);
        probe.translateYProperty().set(jupiterArray[0].getZ()*10);
        probe.translateZProperty().set(jupiterArray[0].getY()*10);*/

        saturn.translateXProperty().set(saturnArray[0].getX()*10);
        saturn.translateYProperty().set(saturnArray[0].getZ()*10);
        saturn.translateZProperty().set(saturnArray[0].getY()*10);

        uranus.translateXProperty().set(uranusArray[0].getX()*10);
        uranus.translateYProperty().set(uranusArray[0].getZ()*10);
        uranus.translateZProperty().set(uranusArray[0].getY()*10);

        neptune.translateXProperty().set(neptuneArray[0].getX()*10);
        neptune.translateYProperty().set(neptuneArray[0].getY()*10);
        neptune.translateZProperty().set(neptuneArray[0].getZ()*10);

        //double[] sphere1position= new double[1400];
        /*for(int i=0; i<sphere1position.length;i++){
            double tmp= (double) sphere2position[i];
            sphere1position[i]=i*10*Math.log((Math.pow(tmp,2)-1));
        }*/

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,event2 ->{
            switch(event2.getCode()){
                case W:
                    if(index<14000 && index>0) {
                        jupiter.translateXProperty().set(jupiterArray[index].getX()*10);
                        jupiter.translateYProperty().set(jupiterArray[index].getZ()*10);
                        jupiter.translateZProperty().set(jupiterArray[index].getY()*10);

                        saturn.translateXProperty().set(saturnArray[index].getX()*10);
                        saturn.translateYProperty().set(saturnArray[index].getZ()*10);
                        saturn.translateZProperty().set(saturnArray[index].getY()*10);

                        uranus.translateXProperty().set(uranusArray[index].getX()*10);
                        uranus.translateYProperty().set(uranusArray[index].getZ()*10);
                        uranus.translateZProperty().set(uranusArray[index].getY()*10);

                        /*probe.translateXProperty().set(neptuneArray[14000].getX());
                        probe.translateYProperty().set(neptuneArray[14000].getZ());
                        probe.translateZProperty().set(neptuneArray[14000].getY());*/

                        /*neptune.translateXProperty().set(neptuneArray[index].getX()*10);
                        neptune.translateYProperty().set(neptuneArray[index].getY()*10);
                        neptune.translateZProperty().set(neptuneArray[index].getZ()*10);*/



                        index++;
                    }else if(index==0){
                        jupiter.translateXProperty().set(jupiterArray[0].getX()*10);
                        jupiter.translateYProperty().set(jupiterArray[0].getZ()*10);
                        jupiter.translateZProperty().set(jupiterArray[0].getY()*10);

                        saturn.translateXProperty().set(saturnArray[0].getX()*10);
                        saturn.translateYProperty().set(saturnArray[0].getZ()*10);
                        saturn.translateZProperty().set(saturnArray[0].getY()*10);

                        uranus.translateXProperty().set(uranusArray[0].getX()*10);
                        uranus.translateYProperty().set(uranusArray[0].getZ()*10);
                        uranus.translateZProperty().set(uranusArray[0].getY()*10);

                        neptune.translateXProperty().set(neptuneArray[0].getX()*10);
                        neptune.translateYProperty().set(neptuneArray[0].getY()*10);
                        neptune.translateZProperty().set(neptuneArray[0].getZ()*10);

                        index++;
                    }else{}
                    break;
                case S:
                    if(index>0) {
                        jupiter.translateXProperty().set(jupiterArray[index].getX() * 10);
                        jupiter.translateYProperty().set(jupiterArray[index].getZ() * 10);
                        jupiter.translateZProperty().set(jupiterArray[index].getY() * 10);

                        saturn.translateXProperty().set(saturnArray[index].getX()*10);
                        saturn.translateYProperty().set(saturnArray[index].getZ()*10);
                        saturn.translateZProperty().set(saturnArray[index].getY()*10);

                        uranus.translateXProperty().set(uranusArray[index].getX()*10);
                        uranus.translateYProperty().set(uranusArray[index].getZ()*10);
                        uranus.translateZProperty().set(uranusArray[index].getY()*10);

                        neptune.translateXProperty().set(neptuneArray[index].getX()*10);
                        neptune.translateYProperty().set(neptuneArray[index].getY()*10);
                        neptune.translateZProperty().set(neptuneArray[index].getZ()*10);

                        index--;
                    }else if (index==0){
                        jupiter.translateXProperty().set(jupiterArray[0].getX()*10);
                        jupiter.translateYProperty().set(jupiterArray[0].getZ()*10);
                        jupiter.translateZProperty().set(jupiterArray[0].getY()*10);

                        saturn.translateXProperty().set(saturnArray[0].getX()*10);
                        saturn.translateYProperty().set(saturnArray[0].getZ()*10);
                        saturn.translateZProperty().set(saturnArray[0].getY()*10);

                        uranus.translateXProperty().set(uranusArray[0].getX()*10);
                        uranus.translateYProperty().set(uranusArray[0].getZ()*10);
                        uranus.translateZProperty().set(uranusArray[0].getY()*10);

                        neptune.translateXProperty().set(neptuneArray[0].getX()*10);
                        neptune.translateYProperty().set(neptuneArray[0].getY()*10);
                        neptune.translateZProperty().set(neptuneArray[0].getZ()*10);

                        index--;
                    }else{}
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
