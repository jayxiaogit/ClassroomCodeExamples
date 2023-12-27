import java.util.NoSuchElementException;

/** This class creates objects that are personal planners.
*/
public class Planner implements Collection {

   /** Default maximum size of planner. */
   private static final int MAXSIZE = 1000;
   
   /** Collection of things that must be done. */
   private Task[] todo;
   /** Number of actual tasks in the planner. */
   private int numtasks;

   /** Create a Planner with the default Collection starting size.
   */
   public Planner() {
      this.todo = new Task[10];
      this.numtasks = 0;
   }

   // THIS METHOD IS COMPLETE
   @Override
   public int size() {
      return this.numtasks;
   }

   /** Add an item to the planner, doubling the size of the planner
      if not yet at the maximum size.
      @param o the item to add, must be some type of Task
      @return true if added, false otherwise
   */
   @Override
   public boolean add(Object o) {
      if (! (o instanceof Task)) {
         return false;
      }
      if (this.numtasks >= MAXSIZE) {
         return false;
      }
      this.todo[this.numtasks] = (Task) o;
      this.numtasks++;
      if (todo.length * 2 <= MAXSIZE) {
         Task[] newArray = new Task[todo.length * 2];
         for (int j = 0; j < todo.length; ++j) {
            newArray[j] = todo[j];
         }
         todo = newArray;
      }

      return true;    
   }

   // THIS METHOD IS COMPLETE
   @Override
   public void display() {
      for (int i = 0; i < this.numtasks; i++) {
         System.out.printf("(%3d) %s\n", i, this.todo[i]);
      }
   }

   /** Find all items in the Planner that contain the parameter as a substring.
       @param o the target string to find (case sensitive)
       @return a string with all items in the Planner containing o, one per line
               or an empty string if there are none
   */
   @Override
   public Object find(Object o) {
      String findobject = (String) o;
      String search = "";
      for (int i = 0; i < this.numtasks; i++) {
         if (this.todo[i].toString().contains(findobject)) {
            search += this.todo[i] + "\n";
         }
      }
      if ("".equals(search)) {
         throw new NoSuchElementException();
      }
      return search;
   }

   // THIS METHOD IS COMPLETE
   @Override
   public Object get(int n) {
      if (n > size()) {
         throw new ArrayIndexOutOfBoundsException();
      }
      return this.todo[n - 1];  // adjust for nth numbering
   }

}
