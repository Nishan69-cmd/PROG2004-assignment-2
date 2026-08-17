/**
 * Interface for park objects that can be inspected.
 */
public interface Inspectable {

// close the item when inspection begins
    void startInspection();

// finish inspection, record the result and reopen
    void finishInspection(String result);

// check whether the item is currently closed
    boolean isClosed();

// return the latest inspection result
    String getInspectionResult();
}