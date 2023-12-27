// THIS INTERFACE DEFINITION IS COMPLETE

/** Interface specifying methods for a general collection.
    @author Joanne Selinski
*/
public interface Collection {

   /** The default initialize size of a collection. */
   int STARTSIZE = 10;

   /** Find out how many things are in the collection.
       @return the number
   */
   int size();

   /** Display the items in the collection on the screen.
   */
   void display();

   /** Find a particular item in the collection.
       @param o the object to search for
       @return the object if found, null otherwise
   */
   Object find(Object o);

   /** Add an item to the collection, if there is room.
       @param o the object to add
       @return true if added, false otherwise
   */
   boolean add(Object o);
   
   /** Get the nth item from the collection, 1 <= n <= size().
       @param n the number of the item to retrieve
       @return the item
       @throws ArrayIndexOutOfBoundsException if n > size()
   */
   Object get(int n);
       
}
