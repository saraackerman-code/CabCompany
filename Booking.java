public record Booking(String vehicleId, String pickupLocation, String destination) {
    // Overriding the Booking record.
    @Override
    public String toString() {
        return "Vehicle: " + vehicleId + " from " + pickupLocation+ " to " + destination;
    }
}
