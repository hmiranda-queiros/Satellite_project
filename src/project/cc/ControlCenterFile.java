package project.cc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

import project.data.*;
import project.database.DataArchive;
import project.filer.Filer;
import project.filer.FilerControlCenter;

/**
 * A control center of TeleOpearables that allows an operator to communicate
 * with them through files.
 * 
 * @author h.miranda-queiros
 */
public class ControlCenterFile {
    /**
     * The archive that stocks all the Data from the mesures made by the
     * TeleOperables of this ControlCenterFile.
     */
    private DataArchive archive;

    /**
     * Creates an empty ControlCenterFile.
     * 
     * @throws IOException
     */
    public ControlCenterFile() throws IOException {
        archive = new DataArchive();
        Files.createDirectory(Paths.get("src", "channels"));
    }

    /**
     * Sends the request to a specified TeleOperable and prints "OK" or "KO" whether
     * the request succeeded or not.
     * 
     * @param request The request that the operator typed in the user interface.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void teleOperate(String request) throws ClassNotFoundException, IOException {
        String name_sat;
        String name_comp;
        String command;

        int f = request.indexOf(':');
        int s = request.lastIndexOf(':');
        int l = request.length();

        name_sat = request.substring(0, f);
        name_comp = request.substring(f + 1, s);
        command = request.substring(s + 1, l);

        if (Filer.existDir(name_sat)) {
            FilerControlCenter.putString(name_comp, command, name_sat);

            if (command.equals("DATA")) {
                /*
                 * Waits until the SatteliteFiles responds.
                 */
                while (Filer.existFile(name_sat, "uplink.bin")) {
                }

                Data newData = FilerControlCenter.getData(name_sat);
                if (newData == null)
                    System.out.println("KO");
                else {
                    archiveData(newData);
                    System.out.println("OK");
                }

            } else {
                /*
                 * Waits until the SatteliteFiles responds.
                 */
                while (Filer.existFile(name_sat, "uplink.bin")) {
                }

                if (FilerControlCenter.getStatus(name_sat) == Status.OK)
                    System.out.println("OK");
                else
                    System.out.println("KO");
            }
        }

        else
            System.out.println("KO"); // name_sat is not a satellite of this ControlCenterFile.
    }

    /**
     * Tests whether the syntax of the request is correct or not.
     * 
     * @param request The request that the operator typed in the user interface.
     * 
     * @return true if the syntax is correct.
     */
    public boolean testSyntaxRequest(String request) {
        int l = request.length();
        int c = 0;

        for (int i = 0; i < l; i++) {
            if (request.charAt(i) == ':')
                c++;
        }

        if (c != 2) {
            System.out.println("KO"); // Error in the syntax of request.
            return false;
        }

        int f = request.indexOf(':');
        int s = request.lastIndexOf(':');

        if (f != 0 && s != l - 1 && s != f + 1)
            return true;

        System.out.println("KO"); // Error in the syntax of request.
        return false;
    }

    /**
     * Stocks the new Data created after a T/M in the archive of this
     * ControlCenterFile.
     * 
     * @param newData The new Data that is going to be stocked.
     */
    public void archiveData(Data newData) {
        archive.add(newData);
    }

    /**
     * Prints the archive of this ControlCenterFile
     */
    public void printArchive() {
        System.out.println("    ******************************");
        if (archive.getDataMap().isEmpty())
            System.out.println("The archive of this control center is empty !");
        else {
            System.out.println("The datas in the archive of this control center are :");
            for (Data value : archive.getDataMap().values()) {
                System.out.println(value);
            }
        }
    }

    /**
     * Deletes the folders and files created by this ControlCenterFile by using the
     * library "commons" present in the directory lib.
     * 
     * @throws IOException
     */
    public void cleanFolders() throws IOException {
        Path path = Paths.get("src", "channels");
        FileUtils.deleteDirectory(new File(path.toString()));
    }

}
