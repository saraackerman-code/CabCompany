import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
/**
 * Copyright 2026.
 * @author Sara Aazarou
 * @version 2026.02.27
 *  **/
public class CabCompany {
    // Create a Hash Map to store the Ids and their corresponding vehicles.
    private final Map<String, Vehicle> vehicles;
    /**
     * Method to store the vehicles in a hashmap with their id as Key.
     * @param theVehicles The company's vehicles.
     */
    public CabCompany(ArrayList<Vehicle> theVehicles) {
        //Initialize the Hash Map
        this.vehicles = new HashMap<>();
        // Loop through the Array and then get the Ids and Objects and store them.
        for (Vehicle theVehicle : theVehicles) {
            vehicles.put(theVehicle.getVehicleId(), theVehicle);
        }
    }

    /**
     * Method to list all the vehicles
     * in the ArrayList theVehicles.
     **/

    public void listAllVehicles() {

        System.out.println(vehicles);
    }

    public int getTotalTakings(String id) {
        if  (vehicles.containsKey(id))
        {
            return vehicles.get(id.trim()).getTakings(id);
        }
        return 0;
    }
    /**
     * Method that reads the booking information from booking.csv
     * and creates a booking object.
     * @param bookingsFilename;
     **/

    public void readBookings(String bookingsFilename) throws IOException {
        Path filePath = Paths.get(bookingsFilename);
        List<String> lines = Files.readAllLines(filePath);
        for (String line: lines) {
            String[] part = line.strip().split(",");
            String vehicleId = part[0];
            String pickUpPoint = part[1];
            String destination = part[2];
            Booking booking = new Booking(vehicleId, pickUpPoint, destination);
            for (Vehicle vehicle : vehicles.values()) {
                if (vehicle.getVehicleId().equals(vehicleId)) {
                    vehicle.addBookings(booking);
                }
            }
        }
    }
    /**
     * Print the report for Cab Company and write it in report.txt.
     * **/

    public void writeReport(String reportFilename) throws IOException {
        Path filePath = Paths.get(reportFilename);
        FileWriter myWriter = new FileWriter(filePath.toString());
        myWriter.write("""
                Test part 5: Results of writing the report.
                Cab Company Report
                """);
        System.out.println("""
                
                Test part 5: Results of writing the report.
                Cab Company Report""");

        //List that holds all the takings
        ArrayList<Integer> takings = new ArrayList<>();
        int sum = 0;
        for (Vehicle aVehicle : vehicles.values()) {
            //Variables
            int totalTakings = getTotalTakings(aVehicle.getVehicleId());
            String vehicleId = aVehicle.getVehicleId();
            String vehicleType = aVehicle.getVehicleType();
            int journeys = aVehicle.getJourneys(aVehicle.getVehicleId());
            takings.add(totalTakings);
            // if vehicle is a Bus
            if (vehicleType.equals("B")) {
                myWriter.write( "Bus"+ " "+ vehicleId+ " had " +journeys  +" journeys "+ "and made £"+ totalTakings+"\n");
            System.out.println( "Bus"+ " "+ vehicleId+ " had " +journeys  +" journeys "+ "and made £"+ totalTakings);}
            // if vehicle is a Cab
                if (vehicleType.equals("C")) {
                    String driverName = aVehicle.getDriverName();
                    myWriter.write("Cab" + " " + vehicleId + " driven by "+ driverName+ " had " + journeys + " journeys " + "and made £" + totalTakings+"\n");
                        System.out.println("Cab" + " " + vehicleId + " driven by "+ driverName+ " had " + journeys + " journeys " + "and made £" + totalTakings);
                }
        }
        for (int taking : takings){
            sum += taking;
        }
        myWriter.write("Total takings for the day £" + sum+ "\n===========================================");
        System.out.println("Total takings for the day £" + sum);
        System.out.println("===========================================");
        myWriter.close();  // must close manually
    }
/**
 *Method to list all the bookings for each Vehicle
 * **/
    public void listAllBookings() {
        for (Vehicle vehicle : vehicles.values())
            System.out.println(vehicle.getVehicleId() + ": " + vehicle.getBookings());
    }
}
