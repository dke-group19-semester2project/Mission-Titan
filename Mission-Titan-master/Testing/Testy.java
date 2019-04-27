//Trying to match the output position for Mercury in the x, y and z direction with those recorded on April 16,2019 from HORIZONS.
import javafx.scene.shape.Sphere;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Instant;
public class Testy {

    public static void main(String[] args){
        SpaceObject merc = new SpaceObject(0.3302,57.9,47.362,0.205,57909050);

        Date today= new Date();
        String myDate="2019/04/16 00:00:00";
        LocalDateTime ldt=LocalDateTime.parse(myDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        long millis=ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        merc.setTimeAngle0(0);
        PolarCoordinates initalpc= merc.orbitPos();
        CartesianCoordinates initialcc=merc.polarToCartesian(initalpc);
        //Divided by 149597871 to convert to AUs, the units that were used in the HORIZONS data set.
        System.out.println(initialcc.getX()/149597871);
        System.out.println(initialcc.getY()/149597871);
        System.out.println(initialcc.getZ()/149597871);

    }
}

