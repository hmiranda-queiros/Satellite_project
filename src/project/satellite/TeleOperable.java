package project.satellite;

import project.data.*;

/**
 * An interface that allows a ControlCenter to communicate with Teleoperables.
 * 
 * @author h.miranda-queiros
 */
public interface TeleOperable {
    /**
     * Returns the status of a command executed by a Component of this TeleOpeable.
     * 
     * @param name_comp The name of the Component.
     * @param command   The name of the command.
     * 
     * @return Status.OK or Status.KO whether name_comp succeeded in executing
     *         command or not.
     */
    Status telecommand(String name_comp, String command);

    /**
     * Returns the Data from the mesure made by a Component of this TeleOpeable.
     * 
     * @param name_comp The name of the Component.
     * 
     * @return the Data from the mesure made by name_comp.
     */
    Data telemetry(String name_comp);

    /**
     * Returns the name of this TeleOpeable.
     * 
     * @return the name of this TeleOpeable.
     */
    String getName();
}
