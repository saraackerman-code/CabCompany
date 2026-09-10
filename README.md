# CabCompany
# Taxi Company Program (Java)

This Java program simulates the backend logic of a taxi company. It handles vehicle registration, booking management, and revenue calculation using object-oriented design.

## Components
### Vehicle System
- Supports two vehicle types:
  - **Cab**: driver name + price per journey
  - **Bus**: circular route + price per stop
- Vehicles are loaded from a text file and instantiated using `decodeVehicleDetails()`.

### Booking System
- Reads bookings from a file and associates them with the correct vehicle.
- Stores all bookings inside the `CabCompany` class.

### Reporting
- Lists all vehicles and bookings.
- Computes total takings for any vehicle.
- Writes a final report to an output file.

## Execution
Run the program with:
`java Main <vehiclesFile> <bookingsFile> <reportFile>`

The program validates arguments, loads data, prints summaries, and generates a final report.
