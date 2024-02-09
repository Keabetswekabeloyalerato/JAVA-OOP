import java.util.Scanner;
/**
 * The CarParkSim class contains the main car park simulation method.
 * It creates and manipulates the main objects, and handles user I/O.
 *
 * @author Stephan Jamieson and ...
 * @version 14/7/2019
 */
public class CarParkSim {
        
    public static void main(final String[] args) {
        final Scanner keyboard = new Scanner(System.in);
        // YOUR CODE HERE.
        // Declare variables to store a Clock and a Register object, create the relevant objects and assign them.
        Register r = new Register(); 
        Clock c = new Clock(Time.MIDNIGHT);
        String ticketID;
        
        System.out.println("Car Park Simulator");
        // YOUR CODE HERE.
        // Print current time.
        System.out.println("The current time is "+c.examine()+".");
        System.out.println("Commands: advance {minutes}, arrive, depart, quit.");
        System.out.print(">");
        String input = keyboard.next().toLowerCase();
        
        while (!input.equals("quit")) {
            if (input.equals("advance")) {
                // YOUR CODE HERE.
                // Advance the clock, print the current time.
               int minutesToAdvance = keyboard.nextInt();
                Duration advanceDuration = new Duration("minute", minutesToAdvance);
                c.advance(advanceDuration); 
                System.out.println("The current time is "+c.examine().toString()+".");
             }
            else if (input.equals("arrive")) {
                // YOUR CODE HERE.
                // Create a new ticket, add it to the register, print details of ticket issued.
                ticketID = UIDGenerator.makeUID();
                Ticket ticket = new Ticket(c.examine(), ticketID); 
                r.add(ticket);
                System.out.println("Ticket issued: "+ticket+".");
            }
            else if (input.equals("depart")) {
                // YOUR CODE HERE.
                // Determine if ticket is valid, i.e. in the register.
                // If not, print error message.
                // If yes, retreive it, calculate duration of stay and print it.
                ticketID=keyboard.next();
                if (r.contains(ticketID)) {
                Ticket ticket =r.retrieve(ticketID);
                System.out.println("Ticket details: "+ ticket+".");
                System.out.println("Current time: "+c.examine()+".");
                Duration difference = ticket.age(c.examine());
                System.out.println("Duration of stay: "+ formatDuration(difference)+"."); 
                }             
                
                
                else{
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
     private static String formatDuration(Duration duration) {
        long[] timeComponents = Duration.split(duration, "hour", "minute");
        long hours = timeComponents[0];
        long minutes = timeComponents[1];

        StringBuilder formattedDuration = new StringBuilder();
        if (hours > 0) {
            formattedDuration.append(hours);
            if (hours == 1) {
                formattedDuration.append(" hour");
            } else {
                formattedDuration.append(" hours");
            }
        }

        if (minutes > 0) {
            if (formattedDuration.length() > 0) {
                formattedDuration.append(" ");
            }
            formattedDuration.append(minutes);
            if (minutes == 1) {
                formattedDuration.append(" minute");
            } else {
                formattedDuration.append(" minutes");
            }
        }

        return formattedDuration.toString();
    }
  }




