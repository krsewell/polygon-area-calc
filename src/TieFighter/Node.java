/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Node.java
 */

package TieFighter;

public class Node<E> {
  private E object;
  private Node<E> next;
  private Node<E> previous;

  public Node() {
    objectIs(null);
    nextIs(null);
    prevIs(null);
  }

  public Node(E var) {
    objectIs(var);
    nextIs(null);
    prevIs(null);
    // Node knows nothing of a linked list. But should initialize next, and prev.
  }

  public void nextIs(Node<E> var) {
    next = var;
  }

  public void prevIs(Node<E> var) {
    previous = var;
  }

  private void objectIs(E var) {
    object = var;
  }

  public E getObject() {
    return object;
  }

  public Node<E> getNext() {
    return next;
  }

  public Node<E> getPrevious() {
    return previous;
  }
}
