import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        Event concert = new Event("Symphony Concert", 120.0);
        Event theater = new Event("Hamlet", 85.0);
        system.addEvent(concert);
        system.addEvent(theater);

        Customer customer1 = new Customer("John", "Smith", "john@example.com");
        Customer customer2 = new Customer("Anna", "Johnson", "anna@example.com");
        system.addCustomer(customer1);
        system.addCustomer(customer2);

        system.makeReservation(customer1, concert);
        system.makeReservation(customer1, theater);
        system.makeReservation(customer2, concert);

        System.out.println("Reservations for " + customer1.getFirstName() + " " + customer1.getLastName() + ":");
        customer1.displayReservations();

        concert.setPrice(150.0);

        System.out.println("\nReservations for " + customer1.getFirstName() + " after price change:");
        customer1.displayReservations();

        System.out.println("\nReservations for " + customer2.getFirstName() + ":");
        customer2.displayReservations();

        System.out.println("\n" + customer1.getFirstName() + " cancels theater reservation:");
        customer1.cancelReservation(theater);
        customer1.displayReservations();

        System.out.println("\nAvailable seats for the concert: " + concert.getAvailableSeats() + "/100");
    }
}

class Event {
    private final String name;
    private double price;
    private int availableSeats = 0;

    public Event(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAvailableSeats() { return availableSeats; }
    public void setPrice(double price) { this.price = price; }
    public void reserveSeat() { availableSeats++; }

    public String toString() {
        return name + " - Price: " + price;
    }
}

class Customer {
    private final String firstName;
    private final String lastName;
    private final ArrayList<Event> reservationList = new ArrayList<>();

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public void addReservation(Event event) {
        reservationList.add(event);
    }

    public void cancelReservation(Event event) {
        reservationList.remove(event);
    }

    public void displayReservations() {
        for (Event e : reservationList) {
            System.out.println(e);
        }
    }
}

class ReservationSystem {
    private final ArrayList<Event> events = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void makeReservation(Customer customer, Event event) {
        customer.addReservation(event);
        event.reserveSeat();
    }
}
