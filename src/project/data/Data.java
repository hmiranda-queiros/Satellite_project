package project.data;

import java.io.Serializable;
import java.util.Date;

/**
 * The data from a mesure realised by a Component of a Satellite.
 * 
 * @author h.miranda-queiros
 */
public class Data implements Serializable {
    /**Generated serialVersionUID */
    private static final long serialVersionUID = 3658124060315388671L;

    /** The date of creation of this Data. */
    private Date timeStamp;

    /**
     * Creates a Data with its timeStamp corresponding to the date of its creation.
     */
    public Data() {
        timeStamp = new Date();
    }

    /**
     * Returns a clone of the date at which this Data was created in order to
     * protect its real value by not giving its real address.
     * 
     * @return the date at which this Data was created.
     */
    public Date getDate() {
        return (Date)timeStamp.clone(); // To protect timeStamp from being modified outside.
    }

    /**
     * Returns a description of this Data.
     * 
     * @return a string that is a description of this Data.
     */
    @Override
    public String toString() {
        return timeStamp.toString();
    }
}
