import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.io.IOException;

/** Time class for manipulating times of day.
    @author Joanne Selinski
*/
public class Time {

   /** Format to print hours and minutes with two digits. */
   public static final DecimalFormat TIMEFMT = new DecimalFormat("00");

   // Time is stored in military format
   private int hours;    // 0-23 valid, inclusive
   private int minutes;  // 0-59 valid, inclusive

   /** Default constructor for Time class.
   */
   public Time() {
      hours = 0;
      minutes = 0;
   }

   /** Time constructor that takes an input string.
       @param timeString the time string to parse
   */
   public Time(String timeString) throws IOException {  
      try {
         StringTokenizer tok = new StringTokenizer(timeString, " :apAP");
         hours = Integer.parseInt(tok.nextToken());
         minutes = Integer.parseInt(tok.nextToken());
         char meridian = 
            timeString.toLowerCase().charAt(timeString.length() - 1);
      
         if (meridian == 'p' && hours != 12) { 
            hours += 12; 
         }
         if (meridian == 'a' && hours == 12) { 
            hours = 0; 
         }
         if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new BadTimeException("invalid time of day");
         }
      }
      catch (NumberFormatException e) {
         System.err.println("invalid format");
      }
      catch (BadTimeException e) {
         System.err.println("invalid time of day");
      } 
   }

   /** Time constructor that takes in the time in military format.
       @param hrs the hour in military format
       @param mins the minutes past the hour
   */
   public Time(int hrs, int mins) {
      try {
         hours = hrs;
         minutes = mins;
         if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new BadTimeException("invalid time of day");
         }
      }
      catch (BadTimeException e) {
         System.err.println("invalid time of day");
      }
   }

   /** Method to return the minutes.
       @return the minutes past the hour
   */
   public int getMinutes() {
      return minutes;
   }

   /** Method to return the hour.
       @return the hour (0 <= hour <= 23)
   */
   public int getHours() {
      return hours;
   }
  
   /** Method to return whether or not the stored time is in the morning.
       @return true if morning, false if afternoon/evening
   */
   public boolean isMorning() {
      return hours < 12;
   }
 
   /** Method to return the time elapsed between two Time objects.
       @param t the end time
       @return a Time object representing the elapsed time between this 
              and the parameter time
   */
   public Time until(Time t) throws BadTimeException {
      int tHours = t.getHours();
      int tMins = t.getMinutes();
     
      if (compareTo(t) == 0) {
         return new Time();   // 0:00 difference
      }
      if (compareTo(t) > 0) {
         tHours += 24; 
      }
     
      int elapsedH = tHours - hours;
      int elapsedM = tMins - minutes;
     
      if (elapsedM < 0) {
         elapsedH--;
         elapsedM += 60;
      }
   
      Time ttime = new Time();
      ttime = new Time(elapsedH, elapsedM);
      return ttime;
   }

   /** Method to compare this Time to another Time.
       @param t the Time object to compare to this
       @return 0 if the Times are equal, -1 if this is before t, 
               1 if this is after t
   */
   public int compareTo(Time t) {
      if (hours < t.hours || (hours == t.hours && minutes < t.minutes)) {
         return -1;
      }
      else if (hours > t.hours || (hours == t.hours && minutes > t.minutes)) {
         return 1;
      }
      else {
         return 0;
      }
   }

   /** Method to convert this Time object to a string.
       @return a String representing the time stored by this Time object
   */
   public String toString() {
      return TIMEFMT.format(hours) + ":" + TIMEFMT.format(minutes);  
   }

   /** Add minutes to the current time.
       @param mins how many minutes to increase
   */
   public void add(int mins) {
      int elapsed = hours * 60 + minutes + mins;
      hours = elapsed / 60;
      minutes = elapsed % 60;
      if (hours >= 24) {
         hours = hours % 24;
      }
   }

   /** Calculate how many minutes between midnight and this time.
       @return the number of minutes
   */
   public int elapsed() {
      return hours * 60 + minutes;
   }

}
