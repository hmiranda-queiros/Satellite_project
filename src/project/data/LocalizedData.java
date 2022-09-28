package project.data;

import java.util.Random;

/**
 * The data from a mesure, realised by a Component of a Satellite, that is
 * geolocalized in space.
 * 
 * @author h.miranda-queiros
 */
public class LocalizedData extends Data {
    /**Generated serialVersionUID */
    private static final long serialVersionUID = 2465167638604450016L;

    /** The coordinate x of the geolocalization of this Data. */
    private double x;

    /** The coordinate y of the geolocalization of this Data. */
    private double y;

    /**
     * Creates a LocalizedData with its timeStamp corresponding to the date of its
     * creation and with the provided coordinates x and y.
     * 
     * @param x The coordinate x of this LocalizedData at the moment of its
     *          creation.
     * @param y The coordinate y of this LocalizedData at the moment of its
     *          creation.
     */
    public LocalizedData(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a LocalizedData with its timeStamp corresponding to the date of its
     * creation and with its coordinates x and y that are picked randomly between 0
     * and 100.
     * 
     */
    public LocalizedData() {
        super();
        Random rand = new Random();
        x = rand.nextDouble() * 100;
        y = rand.nextDouble() * 100;
    }

    /**
     * Returns a description of this LocalizedData.
     * 
     * @return a string that is a description of this LocalizedData.
     */
    @Override
    public String toString() {
        return super.toString() + ", {x = " + x + ", y = " + y + "}";
    }

}
