import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
* This program has menu options to manipulate a personal planner, including:
* add a Task, DueDo, or Appointment to the planner (getting input
* from the user), as well as search for items, and display all items.
*/
public class ProjectD {
   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) {
      int choice = 1;
      Planner p = new Planner();
      Task t;
      DueDo d = new DueDo();
      Appointment a = null;
      String s;
      int item;
   
      while (choice != 0) {
         choice = menu();
         switch (choice) {
            case 0: 
               break;
            case 1:
               try {
                  t = new Task();
                  t.read(keyboard);
                  p.add(t);
               }
               catch (IOException e) {
                  System.out.println("Caught IOException: " + e.getMessage());
               }
               break;
            case 2:
               try {
                  d.read(keyboard);
                  p.add(d);
               }
               catch (IOException e) {
                  System.out.println("Caught IOException: " + e.getMessage());
               }
               break;
            case 3:
               try {
                  a = new Appointment();
                  a.read(keyboard);
                  p.add(a);
               }
               catch (IOException e) {
                  System.out.println("Caught IOException: " + e.getMessage());
               }
               break;
            case 4:
               System.out.println("there are " + p.size()
                                 + " items in the planner");
               break;
            case 5:
               p.display();
               break;
            case 6:
               System.out.print("what to search for? ");
               try {
                  s = keyboard.nextLine();
                  System.out.println(p.find(s));
               }
               catch (NoSuchElementException ne) {
                  System.err.println(ne);
                  System.err.println("search cancelled");
               }
               break;
            case 7:
               System.out.print("enter item number: ");
               item = keyboard.nextInt();
               keyboard.nextLine();
               if (item < p.size()) {
                  ((Task) p.get(item + 1)).toggleDone(); 
               } else {
                  System.err.println("invalid index: " + item);
               }
               break;
            default: System.err.println("invalid choice!");
         }
      }
   }

   /** Print a menu of operations for the user.
       @return the option chosen
   */
   public static int menu() {
      int choice;
      System.out.println(); // spacing for readability
      System.out.println("0) quit");
      System.out.println("1) add to-do item");
      System.out.println("2) add due item");
      System.out.println("3) add appointment");
      System.out.println("4) planner size");
      System.out.println("5) display items");
      System.out.println("6) search (use [ ] or [x] to filter)");
      System.out.println("7) toggle item done");
      do {
         System.out.print("  enter choice -> ");
         try {
            choice = Integer.parseInt(keyboard.nextLine());
         }
         catch (NumberFormatException e) {
            System.err.println(e);
            choice = -1;
         }
         catch (NoSuchElementException ne) {
            System.err.println(ne);
            choice = -1;
         }
      } while (choice == -1);
      return choice;
   }

}

