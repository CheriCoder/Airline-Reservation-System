package resSys;

import java.util.ArrayList;
import java.util.Scanner;

public class AirlineReservationSystem {
    private final ArrayList<Flight> flights;
    /*
    Constructor for AirlineReservationSystem class
    Here flights is declared as private field which means it can be only accessed within AirlineReservation System
     */
    public AirlineReservationSystem() {
        flights = new ArrayList<>();
    }

//    This method adds a flight object to Flight arraylist
    public void addFlight(Flight flight) {
        flights.add(flight);
    }

//    displayFlightTypes() method displays the flight types,prices and seats by iterating over flight arraylist
    public void displayFlightTypes() {
        for (Flight flight : flights) {

            System.out.println("Type: " + flight.getType());
            System.out.println("Price: " + flight.getPrice());
            System.out.println("Seats: " + flight.getNumSeats());
            System.out.println("----------");
        }
    }
//    Loop to display prices
    public void displayPrices() {
        System.out.println("Prices:");
//        for (Flight flight : flights) {
//            System.out.println(flight.getType() + ": " + flight.getPrice());
//        }
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.println(flight.getType() + ": " + flight.getPrice());
        }
    }

//    Function to reserve seat
    public void reserveSeat() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

       /* Loop to display flight types while reservation
        iterates through the flight arraylist and uses the getType() method to get
        the type of flight at index i
        */
        System.out.println("Select a flight type:");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i+1) + ". " + flights.get(i).getType());
        }
        int flightIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Flight flight = flights.get(flightIndex);

        System.out.print("Enter the number of seats to reserve: ");
        int numSeats = scanner.nextInt();

        int mealPrice = 700;
        System.out.println("Would you like a meal ? (Y/N)");
        char m = scanner.next().charAt(0);
        if(m == 'Y' || m == 'y'){
            System.out.println("Meal Added !");
        }else {
            System.out.println("No Meal Charges !");
        }

//        checks if the seat is available or not in CheckSeats method in Flight class
        boolean success = flight.checkSeats(numSeats);
//        runs the if block only when true is returned by the function
//        else prints not enough seats
        if (success) {
            System.out.println("Reservation successful!");
            System.out.println("-----------------");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Flight Type: " + flight.getType());
            System.out.println("Number of Seats: " + numSeats);
            System.out.println("Total Price: " + ( mealPrice + numSeats * flight.getPrice()));
            System.out.println("-----------------");
        } else {
            System.out.println("Reservation failed - not enough seats available.");

        }
    }
/* Method to send unaccompanied baggage
    asks the user for no. of luggage, total weight and duration
    the price is decided based on the above factors
    price = (no. of luggages * price/kg) + duration
 */
    static void baggage(){
        float price;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of luggage: ");
        int quan = in.nextInt();
        System.out.println("Enter the total weight: ");
        int wg = in.nextInt();
        price = wg * 400;
        System.out.println("Select the length of journey: ");
        System.out.println("1. 1 Day - ₹" + (7000+price));
        System.out.println("2. 3 Days - ₹"+(5000+price));
        System.out.println("3. 1 Week - ₹"+ (3000+price));
        System.out.println("4. 1 Month - ₹"+(1500+price));
        System.out.println("Enter choice: ");
        int chB = in.nextInt();
        switch (chB){
            case 1: price+=7000;
                break;
            case 2: price += 5000;
                break;
            case 3: price += 3000;
                break;
            case 4: price += 1500;
        }
        System.out.println("----------");
        System.out.println("BILL");
        System.out.println("Number of Luggage: " + quan);
        System.out.println("Total Weight of Luggage: " + wg);
        System.out.println("Time taken: " + chB);
        System.out.println("Your Total: " + price);
        System.out.println("----------");
    }

//    Default username and pwd which are constants
    static final String Defname = "user";
    static final String Defpwd = "pass";
    public static void main(String[] args) {

//        Creating system instance of the class AirlineReservationSystem
        AirlineReservationSystem system = new AirlineReservationSystem();

/*        Creates 4 Flight objects with type, price and available seats
//        Each Flight object is created using the Flight class constructor,
        which takes three arguments: type, price, and numSeats */
        Flight domesticFlight = new Flight("Domestic", 5000, 50);
        Flight premEconomy = new Flight("Premium Economy", 7000,50);
        Flight internationalFlight = new Flight("International", 10000, 100);
        Flight BusinessFlight = new Flight("Business", 20000,20);

        /*
        These four lines of code add the Flight objects created earlier
        (domesticFlight, internationalFlight, premEconomy, and BusinessFlight)
        to the flights ArrayList of the AirlineReservationSystem object system.
         */
        system.addFlight(domesticFlight);
        system.addFlight(internationalFlight);
        system.addFlight(premEconomy);
        system.addFlight(BusinessFlight);

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("------LOG IN------");
        System.out.println();
        System.out.println("Please Enter your username: ");
        String userName = scanner.next();
        System.out.println("Please Enter your password: ");
        String userPass = scanner.next();
        if(userPass.equals(Defpwd) && userName.equals(Defname)){
            System.out.println("------Logged In------");
            System.out.println();
            System.out.println("------Airline Reservation System------");
            System.out.println();
            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Display Flight Types");
                System.out.println("2. Send Unaccompanied Baggage");
                System.out.println("3. Reserve a Seat");
                System.out.println("4. Quit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    system.displayFlightTypes();
                } else if (choice == 2) {
                    baggage();
                } else if (choice == 3) {
                    system.reserveSeat();
                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("Invalid option - please try again.");
                }
            }
        } else {
            System.out.println("Wrong Password");
        }
    }
}

//class flight stores and returns the type,price and no. of seats when called
//it also has the checkseats method which checks the seat availability and returns a boolean type
class Flight {
    private final String type;
    private final int price;
    private int numSeats;

    public Flight(String type, int price, int numSeats) {
        this.type = type;
        this.price = price;
        this.numSeats = numSeats;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getNumSeats() { return numSeats; }
    public boolean checkSeats(int numSeats) {
        if (numSeats > this.numSeats) {
            return false;
        }
        this.numSeats -= numSeats;
        return true;
    }
}
