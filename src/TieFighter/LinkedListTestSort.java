/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/LinkedListTestSort.java
 */

package TieFighter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTestSort {
  private final LinkedList test = new LinkedList();


  @Before
  public void setUp() {

    test.add(new Node<>(new Payload("Joe", 1.0)));
    test.add(new Node<>(new Payload("Bob", 2.0)));
    test.add(new Node<>(new Payload("Murphy", 3.0)));
    test.add(new Node<>(new Payload("Murphy", 4.0)));
    test.add(new Node<>(new Payload("Jimmy", 4.0)));
  }

  @Test
  public void sortNameAscending() {
    LinkedList rtn1 = test.sort(test, true);

    //Bob, Jimmy, Joe, Murphy
    assertEquals(0, ((Payload) rtn1.delete(0).getObject()).getPilotname().compareToIgnoreCase("Bob"));
    assertEquals(0, ((Payload) rtn1.delete(0).getObject()).getPilotname().compareToIgnoreCase("Jimmy"));
    assertEquals(0, ((Payload) rtn1.delete(0).getObject()).getPilotname().compareToIgnoreCase("Joe"));
    assertEquals(0, ((Payload) rtn1.delete(0).getObject()).getPilotname().compareToIgnoreCase("Murphy"));
    assertEquals(0, ((Payload) rtn1.delete(0).getObject()).getPilotname().compareToIgnoreCase("Murphy"));
  }

  @Test
  public void sortNameDescending() {
    LinkedList rtn2 = test.sort(test, false);
    //Murphy, Joe, Jimmy, Bob
    assertEquals(0, ((Payload) rtn2.delete(0).getObject()).getPilotname().compareToIgnoreCase("Murphy"));
    assertEquals(0, ((Payload) rtn2.delete(0).getObject()).getPilotname().compareToIgnoreCase("Murphy"));
    assertEquals(0, ((Payload) rtn2.delete(0).getObject()).getPilotname().compareToIgnoreCase("Joe"));
    assertEquals(0, ((Payload) rtn2.delete(0).getObject()).getPilotname().compareToIgnoreCase("Jimmy"));
    assertEquals(0, ((Payload) rtn2.delete(0).getObject()).getPilotname().compareToIgnoreCase("Bob"));
  }

  @Test
  public void sortDoubleAscending() {
    if(!((Payload) test.get(0).getObject()).isCheckArea()){
      for (Object aTest : test) {
        ((Payload) aTest).toggleCheck();
      }
    }

    LinkedList rtn3 = test.sort(test, true);
    //1,2,3,4
    assertEquals(1.0, ((Payload) rtn3.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(2.0, ((Payload) rtn3.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(3.0, ((Payload) rtn3.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(4.0, ((Payload) rtn3.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(4.0, ((Payload) rtn3.delete(0).getObject()).getPilotarea(), 0.0001);
  }

  @Test
  public void sortDoubleDescending() {
    if(!((Payload) test.get(0).getObject()).isCheckArea()){
      for (Object aTest : test) {
        ((Payload) aTest).toggleCheck();
      }
    }

    LinkedList rtn4 = test.sort(test, false);
    //4,3,2,1
    assertEquals(4.0, ((Payload) rtn4.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(4.0, ((Payload) rtn4.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(3.0, ((Payload) rtn4.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(2.0, ((Payload) rtn4.delete(0).getObject()).getPilotarea(), 0.0001);
    assertEquals(1.0, ((Payload) rtn4.delete(0).getObject()).getPilotarea(), 0.0001);

  }
}