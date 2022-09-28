import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import project.satellite.Component;
import project.satellite.SatelliteFile;

/**
 * A class that instantiates a new SatelliteFile.
 * 
 * @author h.miranda-queiros
 */
public class MainSatellite {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("    ******************************");
            System.out.println("Choose the name of the new satellite :");
            String name_sat = sc.nextLine();
            name_sat = name_sat.toUpperCase();

        sc.close();

        ArrayList<Component> l1 = new ArrayList<>();

        l1.add(new Component("IMAGER1", false));
        l1.add(new Component("IMAGER2", false));
        l1.add(new Component("IMAGER3", false));

        SatelliteFile sat = new SatelliteFile(name_sat, l1);

        sat.loop();

    }
    
}
