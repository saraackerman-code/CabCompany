import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

    /**
    * @author Sara Aazarou
    * @version 2026.02.27
    * A class containing the main method for the taxi company program.
    * There are several test methods that will display the results of
    * different parts of the program.
     **/

    public class Main {

    public static void main(String[] args)
            throws IOException
    {
        /**
         * The main method for the cab company program.
         * Three arguments are required:
         * 1. The name of the file of vehicle details.
         * 2. The name of the file of bookings.
         * 3. The name of the file for the report.
         * @param args The program arguments.
         * @throws IOException If there is an error reading the files.
         */
        if(args.length != 3) {
            System.out.println(
                    "There should be three program arguments: " +
                            "the name of the vehicles file, " +
                            "the name of the bookings file and " +
                            "the name of the output file for the report.");
            System.exit(1);
        }
        else {
            System.out.printf("Using files: %s, %s and %s", args[0], args[1], args[2]);
        }
        String vehiclesFilename = args[0];
        String bookingsFilename = args[1];
        String reportFilename = args[2];

        //Part1 : Read the vehicle details.
        ArrayList<Vehicle> theVehicles = readVehicleDetails(vehiclesFilename);

        // Done: Test the reading of the vehicle details.
        System.out.println("\nVehicles: ");
           System.out.println(theVehicles);

        CabCompany cabCompany = new CabCompany(theVehicles);

        //Part 2: List the vehicles.
        // Done: List the vehicles.
        System.out.println("\nListing the vehicles: ");
         cabCompany.listAllVehicles();

        // Part 3: Read All Bookings.
        // Read Bookings**/
      cabCompany.readBookings(bookingsFilename);

        //  Done: Test the listing of the bookings.
        System.out.println("\nBookings: ");
        cabCompany.listAllBookings();

        // Part 4: Calculate the takings for each vehicle.
        // Done: Test the calculation of the takings.
        System.out.println("\nTotal takings: "+ cabCompany.getTotalTakings("Cant1"));

        //Part 5: Write Report.
        // Done: Test the writing of the report.
       cabCompany.writeReport(reportFilename);
    }

    /**
     * Method to decode the vehicle details and create a Vehicle object.
     * Done : Complete this method by creating the correct type of Vehicle object.
     * @param vehicleDetails The details of the vehicle.
     * @return The Vehicle object created from the details.
     */
    private static Vehicle decodeVehicleDetails(String vehicleDetails)
    {
        // Split once and reuse parts so variables are available in the right scope.
        String[] parts = vehicleDetails.strip().split(",");
        String vehicleType = parts[0];
        String vehicleId = parts[1];
        // Return the appropriate object;
        if (vehicleType.equals("B"))
        {
            //Convert String to int
            int pricePerStop = Integer.parseInt(parts[2]);
            String circularRoute = parts[3];
            return new Bus (vehicleType,vehicleId, pricePerStop, circularRoute );
        }
        else if (vehicleType.equals("C"))
    {
        String driverName = parts[2];
        //Convert String to int
        int pricePerJourney = Integer.parseInt(parts[3]);

        return new Cab (vehicleType, vehicleId, driverName, pricePerJourney);
    }
    return null;
    }



    /**
     * Method to read the file of vehicle details.
     * DO NOT CHANGE THIS METHOD
     * @param vehiclesFilename The name of the file of vehicle details.
     * @throws IOException  If there is an error reading the file.
     */
    private static ArrayList<Vehicle> readVehicleDetails(String vehiclesFilename)
            throws IOException
    {
        Path filePath = Paths.get(vehiclesFilename);
        List<String> lines = Files.readAllLines(filePath);
        ArrayList<Vehicle> theVehicles = new ArrayList<>();
        for(String vehicleDetails : lines) {
            vehicleDetails = vehicleDetails.trim();
            Vehicle aVehicle = decodeVehicleDetails(vehicleDetails);
            if(aVehicle != null) {
                theVehicles.add(aVehicle);
            }
        }
        return theVehicles;
    }


}
