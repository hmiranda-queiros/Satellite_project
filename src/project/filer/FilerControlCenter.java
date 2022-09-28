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
 * An abstract class that allows a ControlCenterFile to communicate with its
 * SatelliteFiles by writting and reading files.
 * 
 * @author h.miranda-queiros
 */
public abstract class FilerControlCenter extends Filer {

    /**
     * Writes and sends a request to a Component of a SatelliteFile.
     * 
     * @param name_comp The name of the Component.
     * @param command   The command.
     * @param name_sat  The name of the SatelliteFile.
     * 
     * @throws IOException
     */
    public static void putString(String name_comp, String command, String name_sat) throws IOException {
        createFile(name_sat, "uplink.bin");

        String file_path = (basic_path.resolve(Paths.get(name_sat, "uplink.bin"))).toString();
        FileOutputStream fos = new FileOutputStream(file_path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(name_comp);
        oos.writeObject(command);

        oos.close();

        /*
         * Creates a file "ok.txt" to warn the SatelliteFile nam_sat that he can read
         * "uplink.bin".
         */
        createFile(name_sat, "ok.txt");

    }

    /**
     * Reads and returns the Status from a request sent to a SatelliteFile.
     * 
     * @param name_sat The name of the SatelliteFile
     * 
     * @return Status.OK or Status.KO whether the Component succeeded in executing
     *         the command or not.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Status getStatus(String name_sat) throws IOException, ClassNotFoundException {
        String file_path = (basic_path.resolve(Paths.get(name_sat, "downlink.bin"))).toString();
        FileInputStream fis = new FileInputStream(file_path);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Status obj = (Status) ois.readObject();

        ois.close();
        deleteFile(name_sat, "downlink.bin");

        return obj;

    }

    /**
     * Reads and returns the Data from the mesure made by a Component of a
     * SatelliteFile.
     * 
     * @param name_sat The name of the SatelliteFile.
     * 
     * @return the Data from the mesure made by a Component of name_sat.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Data getData(String name_sat) throws IOException, ClassNotFoundException {
        String file_path = (basic_path.resolve(Paths.get(name_sat, "datalink.bin"))).toString();
        FileInputStream fis = new FileInputStream(file_path);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Data obj = (Data) ois.readObject();

        ois.close();
        deleteFile(name_sat, "datalink.bin");

        return obj;

    }

}
