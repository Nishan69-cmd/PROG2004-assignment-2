import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ParkIO {

    private static final String SEPARATOR = ",";

    public static void savePark(Park park, String fileName) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(fileName));

            for (Attraction attraction : park.getAttractions()) {

                if (attraction instanceof Ride) {

                    writer.write(
                            "RIDE"
                                    + SEPARATOR
                                    + attraction.getAttractionID()
                                    + SEPARATOR
                                    + attraction.getName()
                                    + SEPARATOR
                                    + attraction.getCapacity());

                    writer.newLine();

                } else if (attraction instanceof Show) {

                    writer.write(
                            "SHOW"
                                    + SEPARATOR
                                    + attraction.getAttractionID()
                                    + SEPARATOR
                                    + attraction.getName()
                                    + SEPARATOR
                                    + attraction.getCapacity());

                    writer.newLine();
                }
            }

            writer.close();

            System.out.println(
                    "Park saved to " + fileName);

        } catch (IOException e) {

            System.out.println(
                    "Error saving park.");
        }
    }
}