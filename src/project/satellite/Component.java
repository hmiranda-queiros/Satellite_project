package project.satellite;

import java.util.Random;

import project.data.*;

/**
 * A component of a Satellite.
 * 
 * @author h.miranda-queiros
 */
public class Component {
    /** The name of this Component. */
    private String name;
    /** The state of this Component. */
    private boolean activated;

    /**
     * Creates a Component with the provided name and state.
     * 
     * @param name      The name of this Component.
     * @param activated The state of this Component.
     */
    public Component(String name, boolean activated) {
        this.name = name;
        this.activated = activated;
    }

    /**
     * Creates a Component by duplicating a former Component in an other address of
     * the memory.
     * 
     * @param original The Component that is going to be duplicated.
     */
    public Component(Component original) {
        this.name = original.name;
        this.activated = original.activated;
    }

    /**
     * Returns the name of this Component.
     * 
     * @return the name of this Component.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the status of a command executed by this Component.
     * 
     * @param command The name of the command.
     * 
     * @return Status.OK or Status.KO whether this Component succeeded in executing
     *         command or not.
     */
    Status operate(String command) {
        if (command.equals("ON")) {
            if (!activated) {
                activated = true;
                return Status.OK;
            } else
                return Status.KO;
        }

        else if (command.equals("OFF")) {
            if (activated) {
                activated = false;
                return Status.OK;
            } else
                return Status.KO;
        }

        else
            return Status.KO; // The command doesn't exist for this component.

    }

    /**
     * Returns the Data from the mesure made by this Component. This Data is
     * randomly a LocalizedData or a simple Data.
     * 
     * @param name_comp The name of the Component.
     * 
     * @return the Data from the mesure made by this Component.
     */
    Data getData() {
        Random rand = new Random();
        if (activated) {
            if (rand.nextBoolean())
                return new Data();
            else
                return new LocalizedData();
        }

        else
            return null;
    }

}
