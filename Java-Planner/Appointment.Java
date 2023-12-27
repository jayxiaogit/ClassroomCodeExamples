import java.util.Scanner;
import java.io.IOException;

/** This class represents objects with specific times and locations.
*/
public class Appointment extends Task {

   private String date;
   private Time startdue;
   private Time enddue;
   private String location;

   /** Prompt for and read a location,description,date and start and end time
       from user input, handling invalid Time inputs.
       @param in the input source
       @throws IOException if other input errors
   */
   @Override
   public void read(Scanner in) throws IOException {
      super.read(in);
      System.out.print("enter date [mm/dd/yy]: ");
      try {
         this.date = in.nextLine();
         if (this.date.length() != 8) {
            throw new InvalidDateException();
         }
      }
      catch (InvalidDateException e) {
         System.err.println("invalid date!");
      } 
      System.out.print("enter location: ");
      this.location = in.nextLine();
      System.out.print("enter when start [hh:mm a/p]: ");
      try {
         startdue = new Time(in.nextLine());
         if (startdue == new Time(23, 59)) {
            throw new BadTimeException();
         }
      }
      catch (BadTimeException e) {
         startdue = new Time(12, 0);
      }
      System.out.print("enter when end [hh:mm a/p]: ");
      try {
         enddue = new Time(in.nextLine());
         if (enddue == new Time(0, 1)) {
            throw new BadTimeException();
         }
      }
      catch (BadTimeException e) {
         startdue = new Time(23, 59);
      }
   }
   
   /** Get all the inherited data and
       well as the date and start and end time, separated by commas.
       @return the full description
   */
   @Override
   public String toString() {
      return super.toString() + "," + this.location + "," 
         + this.date + "," + startdue + "-" + enddue;
   }
}
