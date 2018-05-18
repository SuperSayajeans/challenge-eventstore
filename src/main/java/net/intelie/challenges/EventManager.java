package net.intelie.challenges;
import java.util.*;
/**
 * An abstraction of an event store.
 * <p>
 * Events may be stored in memory, data files, a database, on a remote
 * server, etc. For this challenge, you should implement an in-memory
 * event event store.
 */
public class EventManager implements EventStore {
    //Implementation of the EventStore interface.
    //Used the linked list Java implementation for means of simplicity.
    //For thread safety purposes I used a simple mutex on the critical sessions of the functions
    private ArrayList<Event> list;
    private boolean mutex;

    public EventManager(ArrayList<Event> list) {
      this.list = list; 
      mutex = false;
    }
    
    public ArrayList<Event> list() {
      return list;
    }
     /**
     * Stores an event
     *
     * @param event
     */
    public void insert(Event event) {
      
      //System.out.println("Function insert() is trying to insert " + event.type() + ", requesting access...");
      while(mutex == true){}
      //System.out.println("Function insert() is trying to insert " + event.type() + ", access granted!");
      mutex = true;
      
      //beggining of critical session
      
      list.add(event);
      
      //end of critical session
      //System.out.println("Function insert() succesfully inserted " + event.type() + ", clearing the path!");
      mutex = false;
    }


    /**
     * Removes all events of specific type.
     *
     * @param type
     */
    public void removeAll(String type) {
      
      //System.out.println("Function removeAll() is trying to remove " + type + ", requesting access...");
      while(mutex == true){}
      //System.out.println("Function removeAll() is trying to remove " + type + ", access granted!");
      mutex = true;
      
      //beggining of critical session
      
      if(list.isEmpty()) {
        throw new IllegalStateException();
      }
      
      for (Iterator<Event> it = list.iterator(); it.hasNext(); ) {
        if(it.next().type() == type)
          it.remove();
      }
      
      //end of critical session
      //System.out.println("Function removeAll() succesfully removed " + type + ", clearing the path!");
      mutex = false;
      
    }

    /**
     * Retrieves an iterator for events based on their type and timestamp.
     *
     * @param type      The type we are querying for.
     * @param startTime Start timestamp (inclusive).
     * @param endTime   End timestamp (exclusive).
     * @return An iterator where all its events have same type as
     * {@param type} and timestamp between {@param startTime}
     * (inclusive) and {@param endTime} (exclusive).
     */
    public EventIterator query(String type, long startTime, long endTime) {
      
      ArrayList<Event> iteration = new ArrayList<Event>();
      
      //System.out.println("Function query() is trying to mount an iterable, requesting access...");
      while(mutex == true){}
      //System.out.println("Function query() is trying to mount an iterable, access granted!");
      mutex = true;
      
      for( Event e : list ) {
        if(e.type() == type && e.timestamp() >= startTime && e.timestamp() < endTime)
          iteration.add(e);
      }
      
      EventIterable eventIterator = new EventIterable(iteration, list);
      //System.out.println("Function query() succesfully create an iterable, clearing the path!");
      mutex = false;
      return eventIterator;
    }

    /* Simple Unit testing 
    public static class TestThread extends Thread {
      
      public void run() {
      Event e1 = new Event("bla", 5);
      Event e2 = new Event("ble", 5);
      Event e3 = new Event("bli", 5);
      Event e4 = new Event("blo", 5);
      Event e5 = new Event("blu", 5);
      Event e6 = new Event("bla", 5);
      Event e7 = new Event("ble", 5);
      Event e8 = new Event("bli", 5);
      ArrayList<Event> list = new ArrayList<Event>();
      
      EventManager M = new EventManager(list);
      M.insert(e1);
      M.insert(e2);
      M.insert(e3);
      M.insert(e4);
      M.insert(e5);
      M.removeAll("bla");
      M.insert(e6);
      M.insert(e7);
      M.query("bla",1, 10);
      M.insert(e8);
      M.removeAll("ble");
      M.removeAll("bli");
      M.removeAll("blo");
      M.removeAll("blu");
      }
    }

    public static void main(String[] args)
    {
      TestThread t1 = new TestThread();
      t1.start();   
      TestThread t2 = new TestThread();
      t2.start();   
      TestThread t3 = new TestThread();
      t3.start();   
      TestThread t4 = new TestThread();
      t4.start();   
      TestThread t5 = new TestThread();
      t5.start();   
    }
    */
}
