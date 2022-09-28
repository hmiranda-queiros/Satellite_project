package project.satellite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import project.filer.Filer;
import project.filer.FilerSatellite;

/**
 * A SatelliteFile with Components that will communicate with a
 * ControlCenterFile through files.
 * 
 * @author h.miranda-queiros
 */
public class SatelliteFile extends Satellite {

    /**
     * Creates a SatelliteFile with the provided name and set of Components.
     * 
     * @param name     The name of this SatelliteFile.
     * @param compList The list of Components that this SatelliteFile will have.
     * 
     * @throws IOException
     */
    public SatelliteFile(String name, ArrayList<Component> compList) throws IOException {
        super(name, compList);
        if (Files.exists(Paths.get("src", "channels")))
            Filer.createDirSat(name);
    }

    /**
     * An infinite loop that ends only when its ControlCenterFile is deleted. This
     * loop waits for the requests from the ControlCenterFile and sends it a
     * response.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loop() throws IOException, ClassNotFoundException {
        String name_sat = this.getName();
        if (!Files.exists(Paths.get("src", "channels")))
            System.out.println("Turn on the control center before launching this satellite !! ");

        while (Files.exists(Paths.get("src", "channels"))) {

            /*
             * "ok.txt" signals to this SatelliteFile when the file "uplink.bin" is ready to
             * be read.
             */
            if (Filer.existFile(name_sat, "ok.txt")) {
                String request[] = FilerSatellite.getString(name_sat);

                if (request[1].equals("DATA")) {
                    FilerSatellite.putData(super.telemetry(request[0]), name_sat);
                }

                else {
                    FilerSatellite.putStatus(super.telecommand(request[0], request[1]), name_sat);
                }
            }
        }
    }
}
