package project.cc;

import java.util.HashMap;

import project.data.*;
import project.database.DataArchive;
import project.satellite.*;

/**
 * A control center of TeleOpearables that allows an operator to communicate
 * with them.
 * 
 * @author h.miranda-queiros
 */
public class ControlCenter {
    /**
     * An HashMap that stocks the TeleOperables of this ControlCenter by using their
     * name.
     */
    private HashMap<String, TeleOperable> satMap;

    /**
     * The archive that stocks all the Data from the mesures made by the
     * TeleOperables of this ControlCenter.
     */
    private DataArchive archive;

    /**
     * Creates an empty ControlCenter.
     */
    public ControlCenter() {
        satMap = new HashMap<>();
        archive = new DataArchive();
    }

    /**
     * Adds a TeleOperable to this ControlCenter.
     * 
     * @param sat The TeleOperable that is going to be added to this ControlCenter.
     */
    public void add(TeleOperable sat) {
        satMap.put(sat.getName(), sat);
    }

    /**
     * Sends the request to a specified TeleOperable and prints "OK" or "KO" whether
     * the request succeeded or not.
     * 
     * @param request The request that the operator typed in the user interface.
     */
    public void teleOperate(String request) {
        String name_sat;
        String name_comp;
        String command;

        int f = request.indexOf(':');
        int s = request.lastIndexOf(':');
        int l = request.length();

        name_sat = request.substring(0, f);
        name_comp = request.substring(f + 1, s);
        command = request.substring(s + 1, l);

        if (satMap.containsKey(name_sat)) {
            TeleOperable sat = satMap.get(name_sat);

            if (command.equals("DATA")) {
                Data newData = sat.telemetry(name_comp);
                if (newData == null)
                    System.out.println("KO");
                else {
                    archiveData(newData);
                    System.out.println("OK");
                }
            } else {
                if (sat.telecommand(name_comp, command) == Status.OK)
                    System.out.println("OK");
                else
                    System.out.println("KO");
            }
        }

        else
            System.out.println("KO"); // name_sat is not a satellite of this ControlCenter.
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
     * Stocks the new Data created after a T/M in the archive of this ControlCenter.
     * 
     * @param newData The new Data that is going to be stocked.
     */
    public void archiveData(Data newData) {
        archive.add(newData);
    }

    /**
     * Prints the archive of this ControlCenter
     */
    public void printArchive() {
        System.out.println("    ******************************");
        if(archive.getDataMap().isEmpty())
            System.out.println("The archive of this control center is empty !");
        else {
            System.out.println("The datas in the archive of this control center are :");
            for (Data value : archive.getDataMap().values()) {
                System.out.println(value);
            }
        }
    }

}
