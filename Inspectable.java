public interface Inspectable {

    void startInspection();

    void finishInspection(String result);

    boolean isClosed();

    String getInspectionResult();
}