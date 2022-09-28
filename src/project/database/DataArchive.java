package project.database;

import java.util.Date;
import java.util.HashMap;

import project.data.*;

/**
 * An archive that stocks several Data.
 * 
 * @author h.miranda-queiros
 */
public class DataArchive {
    /** An HashMap that stocks Data by using their date of creation. */
    private HashMap<Date, Data> dataMap;

    /**
     * Creates an empty DataArchive.
     */
    public DataArchive() {
        dataMap = new HashMap<>();
    }

    /**
     * Returns a copy of this dataMap.
     * 
     * @return a copy of this dataMap.
     */
    public HashMap<Date, Data> getDataMap() {
        return new HashMap<>(dataMap); // returns a copy of dataMap to protect it from beeing changed outside.
    }

    /**
     * Adds a new Data to this DataArchive.
     * 
     * @param newData The new Data that is going to be added.
     */
    public void add(Data newData) {
        dataMap.put(newData.getDate(), newData);
    }

}
