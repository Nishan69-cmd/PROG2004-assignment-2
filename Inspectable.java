public interface Inspectable {

    void inspect(String result);

    boolean isClosed();

    String getInspectionResult();
}