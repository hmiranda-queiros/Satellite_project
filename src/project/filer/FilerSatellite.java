package project.filer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import project.data.Data;
import project.data.Status;

/**
 * An abstract class that allows a SatelliteFile to communicate with its
 * ControlCenterFile by writting and reading files.
 * 
 * @author h.miranda-queiros
 */
public abstract class FilerSatellite extends Filer {

    /**
     * Reads and returns the request sent by a ControlCenterFile to a SatelliteFile.
     * 
     * @param name_sat The name of the SatelliteFile.
     * 
     * @return the request sent by the ControlCenterFile split in a tab :
     *         [name_comp, command].
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static String[] getString(String name_sat) throws IOException, ClassNotFoundException {
        String file_path = (basic_path.resolve(Paths.get(name_sat, "uplink.bin"))).toString();
        FileInputStream fis = new FileInputStream(file_path);
        ObjectInputStream ois = new ObjectInputStream(fis);

        String request[] = new String[2];
        request[0] = (String) ois.readObject();
        request[1] = (String) ois.readObject();

        ois.close();
        deleteFile(name_sat, "ok.txt");

        return request;

    }

    /**
     * Writes and sends the Status of a command executed by a Component of a
     * SatelliteFile.
     * 
     * @param status   The Status of the command.
     * @param name_sat The name of the SatelliteFile.
     * 
     * @throws IOException
     */
    public static void putStatus(Status status, String name_sat) throws IOException {
        createFile(name_sat, "downlink.bin");

        String file_path = (basic_path.resolve(Paths.get(name_sat, "downlink.bin"))).toString();
        FileOutputStream fos = new FileOutputStream(file_path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(status);

        oos.close();
        deleteFile(name_sat, "uplink.bin");

    }

    /**
     * Writes and sends the Data from the mesure made by a Component of a
     * SatelliteFile.
     * 
     * @param newdata  The Data from the mesure made by a Component of the
     *                 SatelliteFile.
     * @param name_sat The name of the SatelliteFile.
     * 
     * @throws IOException
     */
    public static void putData(Data newdata, String name_sat) throws IOException {
        createFile(name_sat, "datalink.bin");

        String file_path = (basic_path.resolve(Paths.get(name_sat, "datalink.bin"))).toString();
        FileOutputStream fos = new FileOutputStream(file_path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(newdata);

        oos.close();
        deleteFile(name_sat, "uplink.bin");

    }

}
