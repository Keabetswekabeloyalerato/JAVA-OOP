import java.util.Scanner;

public class CarParkSim {
        
    public static void main(final String[] args) {
        final Scanner keyboard = new Scanner(System.in);
        
        // Declare variables to store a Clock and a Register object, create the relevant objects and assign them.
        Clock clock = new Clock(new Time("00:00:00"));
        Register register = new Register();
        
        System.out.println("Car Park Simulator");
        System.out.println("The current time is " + clock.examine() + ".");
        System.out.println("Commands: advance {minutes}, arrive, depart, quit.");
        System.out.print(">");
        
        String input = keyboard.next().toLowerCase();
        
        while (!input.equals("quit")) {
            if (input.equals("advance")) {
                int minutesToAdvance = keyboard.nextInt();
                Duration advanceDuration = new Duration("minute", minutesToAdvance);
                clock.advance(advanceDuration);
                System.out.println("The current time is " + clock.examine() + ".");
            }
            else if (input.equals("arrive")) {
                Ticket ticket = new Ticket(clock.examine());
                register.add(ticket);
                System.out.println("Ticket issued: " + ticket);
            }
            else if (input.equals("depart")) {
                String ticketID = keyboard.next();
                
                if (register.contains(ticketID)) {
                    Ticket ticket = register.retrieve(ticketID);
                    System.out.println("Ticket details: " + ticket+ ".");
                    System.out.println("Current time: " + clock.examine() + ".");
                    Duration duration = ticket.age(clock.examine());
                    System.out.println("Duration of stay: " + duration.intValue("minute") + " minutes.");
                } else {
                    System.out.println("Invalid ticket ID.");
                }
            }
            else {
                System.out.println("That command is not recognised.");
                System.out.println("Commands: advance <minutes>, arrive, depart, quit.");
            }            
            
            System.out.print(">");
            input = keyboard.next().toLowerCase();
        }            
        System.out.println("Goodbye.");
    }
}
