import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXKeplerSimulation extends Application{
        private double WIDTH = 1500;
        private double HEIGHT = 1000;
        private int millisPerDay = 86400;
        public void start(Stage primaryStage) throws Exception {

            double planetScalarConstant = 5*Math.pow(10,8);
            double distanceToPixelsMultiplier = (Math.pow(10,6));
            int orbitAnimationMillisPerDay = 170;

            // Sun
            Sphere sun = new Sphere();
            sun.setTranslateX(0);
            sun.setTranslateY(0);
            sun.setRadius(Math.log(0.00465*planetScalarConstant)); //Based on sun radius (convert 1391000/2 km (kilometers) to astronomical units)×10^5/5
            PhongMaterial sunMaterial = new PhongMaterial();
            sunMaterial.setDiffuseColor(Color.ORANGERED);
            sun.setMaterial(sunMaterial);

            // Mercury position on A.D. 2019-Mar-17 00:00:00
            double mercuryStartX = 2.564963171453538E-01;
            double mercuryStartY = 1.923172660668037E-01;
            double mercuryStartZ = -7.859784211492141E-03;
            int millisPerMercuryYear = 88*millisPerDay;
            SpaceObject mercury = new SpaceObject("Mercury", mercuryStartX, mercuryStartY, 0,millisPerMercuryYear, 0.387098, 0.205630);
            Sphere mercurySphere = new Sphere();
            mercurySphere.setRadius(Math.log(1.6308E-5*planetScalarConstant));
            mercurySphere.setTranslateX(mercuryStartX*distanceToPixelsMultiplier);
            mercurySphere.setTranslateY(mercuryStartY*distanceToPixelsMultiplier);
            PhongMaterial mercuryMaterial = new PhongMaterial();
            mercuryMaterial.setDiffuseColor(Color.ORANGE);
            mercurySphere.setMaterial(mercuryMaterial);

            Polygon mercuryPolygon = new Polygon();
            Double[] mercuryTrajectory = mercury.getTrajectoryPoints(88);
            mercuryPolygon.getPoints().addAll(mercuryTrajectory);
            PathTransition mercuryOrbit = new PathTransition();
            mercuryOrbit.setDuration(Duration.millis(15000));
            mercuryOrbit.setAutoReverse(false);
            mercuryOrbit.setNode(mercurySphere);
            mercuryOrbit.setPath(mercuryPolygon);
            mercuryOrbit.setInterpolator(Interpolator.LINEAR);
            mercuryOrbit.setCycleCount(Animation.INDEFINITE);
            mercuryOrbit.play();

            // Venus
            double venusStartX = -3.594511921255050E-02;
            double venusStartY = -7.259170192929906E-01;
            int millisPerVenusYear = 225*millisPerDay;
            SpaceObject venus = new SpaceObject("Venus", venusStartX, venusStartY, 0,millisPerVenusYear, 0.723, 0.007);
            Sphere venusSphere = new Sphere();
            venusSphere.setRadius(Math.log(4.0454E-5*planetScalarConstant));
            venusSphere.setTranslateX(venusStartX*distanceToPixelsMultiplier);
            venusSphere.setTranslateY(venusStartY*distanceToPixelsMultiplier);
            PhongMaterial venusMaterial = new PhongMaterial();
            venusMaterial.setDiffuseColor(Color.BLUE);
            venusSphere.setMaterial(venusMaterial);

            Polygon venusPolygon = new Polygon();
            Double[] venusTrajectory = venus.getTrajectoryPoints(225);
            venusPolygon.getPoints().addAll(venusTrajectory);
            PathTransition venusOrbit = new PathTransition();
            venusOrbit.setDuration(Duration.millis(225*orbitAnimationMillisPerDay));
            venusOrbit.setAutoReverse(false);
            venusOrbit.setNode(venusSphere);
            venusOrbit.setPath(venusPolygon);
            venusOrbit.setInterpolator(Interpolator.LINEAR);
            venusOrbit.setCycleCount(Animation.INDEFINITE);
            venusOrbit.play();

            // Earth
            double earthStartX = -1.805335093876885E-01;
            double earthStartY = 9.665959914157644E-01;
            int millisPerEarthYear = 365*millisPerDay;
            SpaceObject earth = new SpaceObject("Earth", earthStartX, earthStartY, 0,millisPerEarthYear, 1.00000102, 0.0167);
            Sphere earthSphere = new Sphere();
            earthSphere.setRadius(Math.log(4.259E-5*planetScalarConstant));
            earthSphere.setTranslateX(Math.log(earthStartX*distanceToPixelsMultiplier));
            earthSphere.setTranslateY(Math.log(earthStartY*distanceToPixelsMultiplier));
            PhongMaterial earthMaterial = new PhongMaterial();
            earthMaterial.setDiffuseColor(Color.GREEN);
            earthSphere.setMaterial(earthMaterial);

            Polygon earthPolygon = new Polygon();
            Double[] earthTrajectory = earth.getTrajectoryPoints(365);
            earthPolygon.getPoints().addAll(earthTrajectory);
            PathTransition earthOrbit = new PathTransition();
            earthOrbit.setDuration(Duration.millis(365*orbitAnimationMillisPerDay));
            earthOrbit.setAutoReverse(false);
            earthOrbit.setNode(earthSphere);
            earthOrbit.setPath(earthPolygon);
            earthOrbit.setInterpolator(Interpolator.LINEAR);
            earthOrbit.setCycleCount(Animation.INDEFINITE);
            earthOrbit.play();

            // Mars
            double marsStartX = 1.326104799544920E+00;
            double marsStartY = 4.961918261856586E-01;
            int millisPerMarsYear = 687*millisPerDay;
            SpaceObject mars = new SpaceObject("Mars", marsStartX, marsStartY, 0,millisPerMarsYear, 1.524, 0.0934);
            Sphere marsSphere = new Sphere();
            marsSphere.setRadius(Math.log(2.2657E-5*planetScalarConstant));
            marsSphere.setTranslateX(Math.log(marsStartX*distanceToPixelsMultiplier));
            marsSphere.setTranslateY(Math.log(marsStartY*distanceToPixelsMultiplier));
            PhongMaterial marsMaterial = new PhongMaterial();
            marsMaterial.setDiffuseColor(Color.RED);
            marsSphere.setMaterial(marsMaterial);

            Polygon marsPolygon = new Polygon();
            Double[] marsTrajectory = mars.getTrajectoryPoints(687);
            marsPolygon.getPoints().addAll(marsTrajectory);
            PathTransition marsOrbit = new PathTransition();
            marsOrbit.setDuration(Duration.millis(687*orbitAnimationMillisPerDay));
            marsOrbit.setAutoReverse(false);
            marsOrbit.setNode(marsSphere);
            marsOrbit.setPath(marsPolygon);
            marsOrbit.setInterpolator(Interpolator.LINEAR);
            marsOrbit.setCycleCount(Animation.INDEFINITE);
            marsOrbit.play();

            // Jupiter
            double jupiterStartX = -5.011101928794896;
            double jupiterStartY = -2.140212536181223;
            int millisPerJupiterYear = 4333*millisPerDay;
            SpaceObject jupiter = new SpaceObject("Jupiter", jupiterStartX, jupiterStartY, 0,millisPerJupiterYear, 5.20336301, 0.0489);
            Sphere jupiterSphere = new Sphere();
            jupiterSphere.setRadius(Math.log(4.779E-4*planetScalarConstant));
            jupiterSphere.setTranslateX(Math.log(jupiterStartX*distanceToPixelsMultiplier));
            jupiterSphere.setTranslateY(Math.log(jupiterStartY*distanceToPixelsMultiplier));
            PhongMaterial jupiterMaterial = new PhongMaterial();
            jupiterMaterial.setDiffuseColor(Color.PURPLE);
            jupiterSphere.setMaterial(jupiterMaterial);

            Polygon jupiterPolygon = new Polygon();
            Double[] jupiterTrajectory = jupiter.getTrajectoryPoints(4333);
            jupiterPolygon.getPoints().addAll(jupiterTrajectory);
            PathTransition jupiterOrbit = new PathTransition();
            jupiterOrbit.setDuration(Duration.millis(4333*orbitAnimationMillisPerDay));
            jupiterOrbit.setAutoReverse(false);
            jupiterOrbit.setNode(jupiterSphere);
            jupiterOrbit.setPath(jupiterPolygon);
            jupiterOrbit.setInterpolator(Interpolator.LINEAR);
            jupiterOrbit.setCycleCount(Animation.INDEFINITE);
            jupiterOrbit.play();

            // Saturn
            double saturnStartX = 7.238111623638653E+00;
            double saturnStartY = 5.689145638670566E+00;
            int millisPerSaturnYear = 10759 *millisPerDay;
            SpaceObject saturn = new SpaceObject("Saturn", saturnStartX, saturnStartY, 0,millisPerSaturnYear, 9.5826, 0.0565);
            Sphere saturnSphere = new Sphere();
            saturnSphere.setRadius(Math.log(4.029E-4*planetScalarConstant));
            saturnSphere.setTranslateX(Math.log(saturnStartX*distanceToPixelsMultiplier));
            saturnSphere.setTranslateY(Math.log(saturnStartY*distanceToPixelsMultiplier));
            PhongMaterial saturnMaterial = new PhongMaterial();
            saturnMaterial.setDiffuseColor(Color.PINK);
            saturnSphere.setMaterial(saturnMaterial);

            Polygon saturnPolygon = new Polygon();
            Double[] saturnTrajectory = saturn.getTrajectoryPoints(10759);
            saturnPolygon.getPoints().addAll(saturnTrajectory);
            PathTransition saturnOrbit = new PathTransition();
            saturnOrbit.setDuration(Duration.millis(10759*orbitAnimationMillisPerDay));
            saturnOrbit.setAutoReverse(false);
            saturnOrbit.setNode(saturnSphere);
            saturnOrbit.setPath(saturnPolygon);
            saturnOrbit.setInterpolator(Interpolator.LINEAR);
            saturnOrbit.setCycleCount(Animation.INDEFINITE);
            saturnOrbit.play();

            /* TODO: TITAN (I already filled in the data so you just need to check that the getTrajectoryPoints method in the SpaceObject class works for a moon)
            double titanStartX = 2.255030407743145E-03;
            double titanStartY = 6.968540736485705E-03;
            int millisPerTitanYear = 16 * millisPerDay;
            SpaceObject titan = new SpaceObject("Titan", titanStartX, titanStartY, 0, millisPerTitanYear, 0.008168, 0.0288);
            Sphere titanSphere = new Sphere();
            titanSphere.setRadius(Math.log(1.721E-5*planetScalarConstant));
            titanSphere.setTranslateX(Math.log(titanStartX*distanceToPixelsMultiplier));
            titanSphere.setTranslateY(Math.log(titanStartY*distanceToPixelsMultiplier));
            PhongMaterial titanMaterial = new PhongMaterial();
            titanMaterial.setDiffuseColor(Color.WHITE);
            titanSphere.setMaterial(titanMaterial);

            Polygon titanPolygon = new Polygon();
            Double[] titanTrajectory = titan.getTrajectoryPoints(16); // TODO: Fred: this is the method to get the points for trajectory. Check if this number of points makes sense and take more if necessary.
            titanPolygon.getPoints().addAll(titanTrajectory);
            PathTransition titanOrbit = new PathTransition();
            titanOrbit.setDuration(Duration.millis(16*orbitAnimationMillisPerDay)); // The amount of time should be the same regardless of how many data points we use
            titanOrbit.setAutoReverse(false);
            titanOrbit.setNode(titanSphere);
            titanOrbit.setPath(titanPolygon);
            titanOrbit.setInterpolator(Interpolator.LINEAR);
            titanOrbit.setCycleCount(Animation.INDEFINITE);
            titanOrbit.play();

            */



            Group root = new Group(sun, mercurySphere, venusSphere, earthSphere, marsSphere, jupiterSphere, saturnSphere);
            Scene scene = new Scene(root,WIDTH, HEIGHT);
            scene.setFill(Color.BLACK);

            Camera camera= new PerspectiveCamera();
            camera.translateXProperty().set(-WIDTH/2);
            camera.translateYProperty().set(-HEIGHT/2);
            camera.translateZProperty().set(-2500);
            scene.setCamera(camera);

            primaryStage.setTitle("Solar System");
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        public static void main(String args[]){
            launch(args);
        }

}

