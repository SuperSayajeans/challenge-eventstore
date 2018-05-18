package net.intelie.challenges;
import java.util.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test
    public void thisIsAWarning() throws Exception {
        Event event = new Event("some_type", 123L);

        //THIS IS A WARNING:
        //Some of us (not everyone) are coverage freaks.
        assertEquals(123L, event.timestamp());
        assertEquals("some_type", event.type());
    }
    
    public void managerInsertTest() throws Exception {
    
      Event e1 = new Event("bla", 5);
      Event e2 = new Event("ble", 5);
      Event e3 = new Event("bli", 5);
      Event e4 = new Event("blo", 5);
      Event e5 = new Event("blu", 5);

      ArrayList<Event> list = new ArrayList<Event>();
      
      EventManager M = new EventManager(list);
      M.insert(e1);
      M.insert(e2);
      M.insert(e3);
      M.insert(e4);
      M.insert(e5);
      
      assertEquals(M.list().get(0).type(), "bla");
      assertEquals(M.list().get(1).type(), "ble");
      assertEquals(M.list().get(2).type(), "bli");
      assertEquals(M.list().get(3).type(), "blo");
      assertEquals(M.list().get(4).type(), "blu");
    }
    
    public void managerRemoveTest() throws Exception {
    
      Event e1 = new Event("bla", 5);
      Event e2 = new Event("ble", 5);
      Event e3 = new Event("bli", 5);
      Event e4 = new Event("blo", 5);
      Event e5 = new Event("blu", 5);

      ArrayList<Event> list = new ArrayList<Event>();
      
      EventManager M = new EventManager(list);
      M.insert(e1);
      M.insert(e2);
      M.insert(e3);
      M.insert(e4);
      M.insert(e5);
      
      M.removeAll("bla");
      assertEquals(M.list().get(0).type(), "ble");
      M.removeAll("ble");
      assertEquals(M.list().get(0).type(), "bli");
      M.removeAll("bli");
      assertEquals(M.list().get(0).type(), "blo");
      M.removeAll("blo");
      assertEquals(M.list().get(0).type(), "blu");
      M.removeAll("blu");
      assertEquals(M.list().isEmpty(), true);
      
      M.insert(e1);
      M.insert(e2);
      M.insert(e3);
      M.insert(e4);
      M.insert(e5);
      
      M.removeAll("blu");
      assertEquals(M.list().get(0).type(), "bla");
      M.removeAll("blo");
      assertEquals(M.list().get(0).type(), "bla");
      M.removeAll("bli");
      assertEquals(M.list().get(0).type(), "bla");
      M.removeAll("ble");
      assertEquals(M.list().get(0).type(), "bla");
      M.removeAll("bla");
      assertEquals(M.list().isEmpty(), true);
    }
    
    public void managerQueryTest() throws Exception {
      
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
      M.insert(e6);
      M.insert(e7);
      M.insert(e8);
      EventIterable et1 = (EventIterable)M.query("bla",1, 10);
      EventIterable et2 = (EventIterable)M.query("ble",1, 10);
      EventIterable et3 = (EventIterable)M.query("bli",1, 10);
      EventIterable et4 = (EventIterable)M.query("blo",1, 10);
      EventIterable et5 = (EventIterable)M.query("blu",1, 10);
      
      assertEquals(et1.list().size(), 2);
      assertEquals(et2.list().size(), 2);
      assertEquals(et3.list().size(), 2);
      assertEquals(et4.list().size(), 1);
      assertEquals(et5.list().size(), 1);
      
      et1 = (EventIterable)M.query("bla",5, 5);
      et2 = (EventIterable)M.query("ble",5, 10);
      et3 = (EventIterable)M.query("bli",1, 5);
      et4 = (EventIterable)M.query("blo",1, 10);
      et5 = (EventIterable)M.query("blu",6, 10);
      
      assertEquals(et1.list().size(), 0);
      assertEquals(et2.list().size(), 2);
      assertEquals(et3.list().size(), 0);
      assertEquals(et4.list().size(), 1);
      assertEquals(et5.list().size(), 0);
    }
    
    public void EventManagerTest() throws Exception {
      
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
      
      assertEquals(M.list().get(0).type(), "bla");
      assertEquals(M.list().get(1).type(), "ble");
      assertEquals(M.list().get(2).type(), "bli");
      assertEquals(M.list().get(3).type(), "blo");
      assertEquals(M.list().get(4).type(), "blu");

      M.removeAll("bla");
      assertEquals(M.list().get(0).type(), "ble");
      assertEquals(M.list().get(1).type(), "bli");
      assertEquals(M.list().get(2).type(), "blo");
      assertEquals(M.list().get(3).type(), "blu");
      
      M.insert(e6);
      M.insert(e7);

      assertEquals(M.list().get(0).type(), "ble");
      assertEquals(M.list().get(1).type(), "bli");
      assertEquals(M.list().get(2).type(), "blo");
      assertEquals(M.list().get(3).type(), "blu");
      assertEquals(M.list().get(4).type(), "bla");
      assertEquals(M.list().get(5).type(), "ble");
      
      EventIterable et1 = (EventIterable)M.query("bla",1, 10);
      
      assertEquals(et1.list().size(), 1);
      
      M.insert(e8);
      
      assertEquals(M.list().get(6).type(), "bli");

      M.removeAll("ble");
      assertEquals(M.list().get(0).type(), "bli");
      assertEquals(M.list().get(1).type(), "blo");
      assertEquals(M.list().get(2).type(), "blu");
      assertEquals(M.list().get(3).type(), "bla");
      assertEquals(M.list().get(4).type(), "bli");
      
      M.removeAll("bli");
      assertEquals(M.list().get(0).type(), "blo");
      assertEquals(M.list().get(1).type(), "blu");
      assertEquals(M.list().get(2).type(), "bla");
      
      M.removeAll("blo");
      assertEquals(M.list().get(0).type(), "blu");
      assertEquals(M.list().get(1).type(), "bla");
      
      M.removeAll("blu");
      assertEquals(M.list().get(0).type(), "bla");
      assertEquals(M.list().size(), 1);
    }
    
    public void iteratorMoveNextTest() throws Exception {
      
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
      M.insert(e6);
      M.insert(e7);
      M.insert(e8);
      
      EventIterable et1 = (EventIterable)M.query("bla",5, 5);
      EventIterable et2 = (EventIterable)M.query("ble",5, 10);
      EventIterable et3 = (EventIterable)M.query("bli",1, 5);
      EventIterable et4 = (EventIterable)M.query("blo",1, 10);
      EventIterable et5 = (EventIterable)M.query("blu",6, 10);
      
      assertEquals(et1.moveNext(), false);
      assertEquals(et2.moveNext(), true);
      assertEquals(et2.moveNext(), true);
      assertEquals(et3.moveNext(), false);
      assertEquals(et4.moveNext(), true);
      assertEquals(et5.moveNext(), false);
    }
    
    public void iteratorCurrentTest() throws Exception {
      
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
      M.insert(e6);
      M.insert(e7);
      M.insert(e8);
      
      EventIterable et1 = (EventIterable)M.query("bla",5, 5);
      EventIterable et2 = (EventIterable)M.query("ble",5, 10);
      EventIterable et3 = (EventIterable)M.query("bli",1, 5);
      EventIterable et4 = (EventIterable)M.query("blo",1, 10);
      EventIterable et5 = (EventIterable)M.query("blu",6, 10);
      
      assertEquals(et1.moveNext(), false);
      assertEquals(et1.current(), false);
      assertEquals(et2.current(), false);
      assertEquals(et2.moveNext(), true);
      assertEquals(et2.current(), e2);
      assertEquals(et2.moveNext(), true);
      assertEquals(et2.current(), e7);
      assertEquals(et3.current(), false);
      assertEquals(et3.moveNext(), false);
      assertEquals(et3.current(), false);
      assertEquals(et4.current(), false);
      assertEquals(et4.moveNext(), true);
      assertEquals(et4.current(), e4);
      assertEquals(et5.moveNext(), false);
      assertEquals(et5.current(), false);
    }
    //This function basically tests everything so I wont create a class test for EventIterable.
    public void iteratorRemoveTest() throws Exception {
      
      Event e1 = new Event("bla", 5);
      Event e2 = new Event("ble", 5);
      Event e3 = new Event("bli", 5);
      Event e4 = new Event("blo", 5);
      Event e5 = new Event("blu", 5);
      Event e6 = new Event("bla", 5);
      Event e7 = new Event("ble", 5);
      Event e8 = new Event("bli", 7);
      ArrayList<Event> list = new ArrayList<Event>();
      
      EventManager M = new EventManager(list);
      M.insert(e1);
      M.insert(e2);
      M.insert(e3);
      M.insert(e4);
      M.insert(e5);
      M.insert(e6);
      M.insert(e7);
      M.insert(e8);
      
      EventIterable et1 = (EventIterable)M.query("bla",5, 10);
      EventIterable et2 = (EventIterable)M.query("ble",5, 10);
      EventIterable et3 = (EventIterable)M.query("bli",5, 6);
      EventIterable et4 = (EventIterable)M.query("blo",5, 10);
      EventIterable et5 = (EventIterable)M.query("blu",5, 10);
      
     
      
      assertEquals(et1.moveNext(), true);
      et1.remove();
      assertEquals(M.list().get(0), e2);
      et1.remove();
      assertEquals(M.list().get(0), e2);
      assertEquals(M.list().get(4), e7);
      assertEquals(et2.moveNext(), true);
      assertEquals(M.list().get(0), e2);
      et2.remove();
      assertEquals(M.list().get(0), e3);
      et2.remove();
      assertEquals(M.list().get(0), e3);
      assertEquals(M.list().get(3), e8);
      assertEquals(et3.moveNext(), true);
      et3.remove();
      assertEquals(M.list().get(0), e4);
      et3.remove();
      assertEquals(et4.moveNext(), true);
      et4.remove();
      assertEquals(M.list().get(0), e5);
      assertEquals(et5.moveNext(), true);
      et5.remove();
      assertEquals(M.list().get(0), e8);
    }
    
    public static void main(String[] args)
    {
      EventTest test = new EventTest();
      try {
        test.managerInsertTest();
      }
      catch(Exception e) {}
    
      System.out.println("Function insert passed on the tests.");
    
      try {
        test.managerRemoveTest();
      }
      catch(Exception e) {}
      
     System.out.println("Function remove passed on the tests.");
     
     try {
        test.managerQueryTest();
      }
      catch(Exception e) {}
      
     System.out.println("Function query passed on the tests.");
     
     try {
        test.EventManagerTest();
      }
      catch(Exception e) {}
      
     System.out.println("Class EventManager passed on the tests.");
     
     try {
        test.iteratorMoveNextTest();
      }
      catch(Exception e) {}
      
      System.out.println("Function moveNext passed on the tests.");
      
      try {
        test.iteratorCurrentTest();
      }
      catch(Exception e) {}
      
      System.out.println("Function current passed on the tests.");
      
      try {
        test.iteratorRemoveTest();
      }
      catch(Exception e) {}
      
      System.out.println("Function remove passed on the tests.");
      
    }
}
