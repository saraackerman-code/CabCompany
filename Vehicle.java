import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 /**
  * Extract Vehicle information;
  * @author Sara Aazarou
  * @version 2026.02.27
  * **/

public abstract class Vehicle {
    private final String vehicleType;
     private final String vehicleId;
     private final ArrayList<Booking> bookings;

     // Adding Constructor to Vehicle

    public Vehicle(String vehicleType, String vehicleId) {
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
        this.bookings = new ArrayList<>();
    }

    //Getter Methods for Vehicle

    protected String getVehicleType() {

        return vehicleType;
    }

    protected String getVehicleId() {

        return vehicleId;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Adds booking object to a List of bookings;
     * @param  booking;
     * **/

    public void addBookings(Booking booking)
    {
        bookings.add(booking);
    }
    abstract int getTakings(String id);
    abstract int getJourneys(String id);
    abstract String getDriverName();

}


// Bus Sub-Class

class Bus extends Vehicle {
    private final int pricePerStop;
    private final String circularRoute;

    public Bus(String vehicleType, String vehicleId, int pricePerStop, String circularRoute) {
        super(vehicleType, vehicleId);
        this.pricePerStop = pricePerStop;
        this.circularRoute = circularRoute;
    }

    //Getter Methods for Bus Sub-Class

    public int getPricePerStop() {

        return pricePerStop;
    }

    public String getCircularRoute() {

        return circularRoute;
    }
    public String getDriverName() {return null;}

    @Override
    public String toString() {
        String[] parts = circularRoute.split(":");

            String route = parts[0] + ", "+ parts[1];

            return "\nBus " + getVehicleId() + " has the route " + route;}

    /**
     * Method to get the total number of takings for each Bus.
     * @return int the Number of takings
     **/

    @Override
    int getTakings(String id) {
        if (getVehicleType().equals("B")) {
            int sum = 0;
            ArrayList<Integer> stops =new ArrayList<>();
            for (Booking aBooking : getBookings()) {
                String [] route = getCircularRoute().split(":");
                List<String> routeAsAList = Arrays.asList(route);
                int pickupIndex = routeAsAList.indexOf(aBooking.pickupLocation());
                int destinationIndex = routeAsAList.indexOf(aBooking.destination());
                if (destinationIndex > pickupIndex) {
                    int stop = (destinationIndex- pickupIndex);
                    stops.add(stop);
                }
                else if (pickupIndex > destinationIndex) {
                    int stop = (  routeAsAList.size() -pickupIndex )+ destinationIndex;
                    stops.add(stop);
                }
            }
            for (int stop: stops){
            sum += stop;}
            return sum*getPricePerStop();
        }
            return 0;
        }
    /**
     * Gets the number of Journeys for a Bus
     * @param id;
     * @return int number of Journeys
     **/
    @Override
    int getJourneys(String id) {
        if (getVehicleType().equals("B")) {
            for (Booking aBooking : getBookings()) {
                if (id.equals(aBooking.vehicleId())) {
                    return getBookings().size();
                }
            }
        }
        return 0;
    }
}


//Cab Sub-Class

class Cab extends Vehicle
{
    private final String driverName;
    private final int pricePerJourney;

    public Cab(String vehicleType, String vehicleId, String driverName, int pricePerJourney)
    {
        super(vehicleType, vehicleId);
        this.driverName = driverName;
        this.pricePerJourney = pricePerJourney;
    }

    //Getter Methods for Cab Sub-Class

    public String getDriverName() {
        return driverName;}

    public int getPricePerJourney() {

        return pricePerJourney;
    }


    @Override
    public String toString()
    {

        return "\nCab "+ getVehicleId() + " driven by " + driverName;
    }
    /**
     * Method to get the total earned for the Journeys.
     * @return int the Number of takings.
     * **/
    @Override

    int getTakings(String id) {
            return getJourneys(id)* getPricePerJourney();
    }
    /**
     * Gets the number of Journeys for a Cab
     * @param id;
     * @return int number of Journeys**/

    @Override
    int getJourneys(String id) {
        if (getVehicleType().equals("C")) {
            for (Booking aBooking : getBookings()) {
                if (id.equals(aBooking.vehicleId())) {
                    return getBookings().size();
                }
            }
        }
        return 0;
    }
}

