package project.satellite;

import java.util.ArrayList;
import java.util.HashMap;

import project.data.*;

/**
 * A Satellite with Components.
 * 
 * @author h.miranda-queiros
 */
public class Satellite implements TeleOperable {
    /** The name of the Satellite. */
    private String name;

    /**
     * An HashMap that stocks the Components of this Satellite by using their name.
     */
    private HashMap<String, Component> compMap;

    /**
     * Creates a Satellite with the provided name and set of Components.
     * 
     * @param name     The name of this Satellite.
     * @param compList The list of Components that this Satellite will have.
     */
    public Satellite(String name, ArrayList<Component> compList) {
        this.name = name;
        compMap = new HashMap<>();
        for (Component comp : compList) {
            compMap.put(comp.getName(), new Component(comp));
        }
    }

    /**
     * Returns the name of this Satellite.
     * 
     * @return the name of this Satellite.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the status of a command executed by a Component of this Satellite.
     * 
     * @param name_comp The name of the Component.
     * @param command   The name of the command.
     * 
     * @return Status.OK or Status.KO whether name_comp succeeded in executing
     *         command or not.
     */
    @Override
    public Status telecommand(String name_comp, String command) {
        if (compMap.containsKey(name_comp)) {
            return compMap.get(name_comp).operate(command);
        } else {
            return Status.KO; // name_comp is not a component of this satellite.
        }
    }

    /**
     * Returns the Data from the mesure made by a Component of this Satellite.
     * 
     * @param name_comp The name of the Component.
     * 
     * @return the Data from the mesure made by name_comp.
     */
    @Override
    public Data telemetry(String name_comp) {
        if (compMap.containsKey(name_comp)) {
            return compMap.get(name_comp).getData();
        } else {
            return null; // name_comp is not a component of this satellite.
        }
    }

}
