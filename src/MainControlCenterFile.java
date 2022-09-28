import java.io.IOException;
import java.util.Scanner;

import project.cc.ControlCenterFile;

/**
 * A Class that represents an user interface for the operator of the SatelliteFiles.
 * The operator only has to write the command by using the good syntax in order
 * to communicate with the SatelliteFiles. This program will not tell you what is
 * your mistake when the response is KO to respect the fact that the control
 * center only receives OK or KO from the SatelliteFiles.
 * 
 * @author h.miranda-queiros
 */
public class MainControlCenterFile {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ControlCenterFile center = new ControlCenterFile();

        Scanner sc = new Scanner(System.in);
        boolean cont = true;

        while (cont) {
            System.out.println("    ******************************");
            System.out.println("Type a request :");
            String request = sc.nextLine();
            request = request.toUpperCase();

            if (center.testSyntaxRequest(request)) // Testing if the syntax of request is correct.
                center.teleOperate(request);

            String ans = "";
            while (!ans.equals("Y") && !ans.equals("N")) {
                System.out.println("Do you want to try again ? y/n");
                ans = sc.nextLine();
                ans = ans.toUpperCase();
            }

            if (ans.equals("N"))
                cont = false;
        }

        sc.close();

        center.printArchive();

        center.cleanFolders();

    }
    
}
