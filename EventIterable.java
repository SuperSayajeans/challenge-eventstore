package net.intelie.challenges;
import java.util.*;
/**
 * An iterator over an event collection.
 */
public class EventIterable implements EventIterator {
//Implementation of the EventIterator interface.
//Used the linked list Java implementation for means of simplicity.
//list is the ArrayList of the events selected for the iterator.
//store is the whole Event ArrayList store. I will need it in the remove() function.
    int current;
    private ArrayList<Event> list;
    private ArrayList<Event> store;

    public EventIterable(ArrayList<Event> list, ArrayList<Event> store) {
      this.list = list; 
      this.store = store;
      current = -1;
    }
    
    public ArrayList<Event> list() {
      return list;
    }
    
    public ArrayList<Event> store() {
      return store;
    }
    
//I am not very familiar with the Autocloseable interface, but, as the Event Iterator will vanish from memory when its references are done, I just made the list
//clear itself.
    public void close() {
      list.clear();
    }
    
     /**
     * Move the iterator to the next event, if any.
     *
     * @return false if the iterator has reached the end, true otherwise.
     */
    public boolean moveNext() {
      current++;
      if(list.size() <= current) {
        return false;
      }
      return true;
    }

    /**
     * Gets the current event ref'd by this iterator.
     *
     * @return the event itself.
     * @throws IllegalStateException if {@link #moveNext} was never called
     *                               or its last result was {@code false}.
     */
    public Event current() {
      if(list.size() <= current || current == -1) {
         throw new IllegalStateException();
      }
      return list.get(current);
    }

    /**
     * Remove current event from its store.
     *
     * @throws IllegalStateException if {@link #moveNext} was never called
     *                               or its last result was {@code false}.
     */
    //I assumed that remove() would remove the element in the store and in the iterator.
    //When removing something, the current variabla points to the next element.
    public void remove() {               
      if(list.size() <= current || current == -1) {
         throw new IllegalStateException();
      }
        store.remove(store.indexOf(list.get(current)));
        list.remove(list.get(current));
    }
}
