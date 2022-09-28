import java.util.ArrayList;
import java.util.Scanner;

import project.cc.*;
import project.satellite.*;

/**
 * A Class that represents an user interface for the operator of the Satellites.
 * The operator only has to write the command by using the good syntax in order
 * to communicate with the Satellites. This program will not tell you what is
 * your mistake when the response is KO to respect the fact that the control
 * center only receives OK or KO from the Satellites.
 * 
 * @author h.miranda-queiros
 */
public class Main {
    public static void main(String[] args) {
        ControlCenter center = new ControlCenter();
        ArrayList<Component> l1 = new ArrayList<>();

        l1.add(new Component("IMAGER1", false));
        l1.add(new Component("IMAGER2", false));
        l1.add(new Component("IMAGER3", false));

        center.add(new Satellite("ISAESAT", l1));
        center.add(new Satellite("XSAT", l1));

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

    }

}
