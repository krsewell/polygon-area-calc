/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Jan 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/LinkedList.java
 */

package TieFighter;

import java.util.*;

/**
 * Contains an implementation of a doubly linked list.
 */
public class LinkedList<E> implements List {
  private Node<E> head;
  private Node<E> tail;
  private int size;
  private boolean isAscending;
  //private boolean sortByName;


  LinkedList() {
    size = 0;
    this.sortByName = true;
    this.isAscending = true;
  }

  public LinkedList(Node<E> initial) {
    setHead(initial);
    setTail(initial);
    size = 1;
    this.sortByName = true;
    this.isAscending = true;
  }


  /**
   * Returns the number of elements in this list.  If this list contains
   * more than <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of elements in this list
   */
  @Override
  public int size() {
    if (size >= 0)
      return size;
    //if size is negative it must be due to overflow.
    return Integer.MAX_VALUE;
  }


  /**
   * Returns <tt>true</tt> if this list contains no elements.
   *
   * @return <tt>true</tt> if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    if (size == 0){
      return true;
    }
    return false;
  }


  /**
   * Returns <tt>true</tt> if this list contains the specified element.
   * More formally, returns <tt>true</tt> if and only if this list contains
   * at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presence in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element
   * @throws ClassCastException if the type of the specified element
   *         is incompatible with this list
   * (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified element is null and this
   *         list does not permit null elements
   * (<a href="Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public boolean contains(Object o) throws ClassCastException,NullPointerException {
    if(o.getClass() == )


    for(Object e: this){
      if(e.equals(o)) return true;
    }
    return false;
  }


  //public boolean isSortByName() {return this.sortByName; }

  public void setAscending(boolean b) {
    this.isAscending = b;
  }

  public void toggle() {
    for (Object o : this) {
      if (o instanceof Payload) {
        ((Payload) o).toggleCheck();
      }
    }

    this.sortByName = !isSortByName();
  }

  public Node<E> getHead() {
    return head;
  }

  private void setHead(Node<E> var) {
    head = var;
  }

  public Node<E> getTail() {
    return tail;
  }

  private void setTail(Node<E> var) {
    tail = var;
  }

  private Node<E> decouple(Node<E> var) {
    if (var == null) {
      return null;
    }
    if (this.size() == 0) {
      setTail(null);
      setHead(null);
      return var; //special case w/ no previous or next.
    }

    if (var.getPrevious() != null) {
      var.getPrevious().nextIs(var.getNext());
    } else {
      //if previous is null then var was head. set new head to next node.
      setHead(var.getNext());
    }

    if (var.getNext() != null) {
      var.getNext().prevIs(var.getPrevious());
    } else {
      //if next is null then var was tail. set new tail to previous node.
      setTail(var.getPrevious());
    }
    //clean var before returning
    var.nextIs(null);
    var.prevIs(null);
    return var;
  }

  public Node delete(int index) throws IndexOutOfBoundsException {
    //go to index
    Node current = getHead();
    for (int i = 0; i != index; i++) {
      //if it reaches tail w/o i == index there is no result for the index.
      if (current == getTail()) {
        throw new IndexOutOfBoundsException();
      }
      current = current.getNext();
    }
    size--;
    return decouple(current);
  }

  public void add(Node<E> var) {
    //clean potentially dirty node
    var.prevIs(null);
    var.nextIs(null);
    //if null do nothing
    if (var == null) {return;}

    // list is empty
    if (this.size() == 0) {
      setHead(var);
      setTail(var);

    } // list isn't empty
    else {
      //var is the new tail
      tail.nextIs(var);
      var.prevIs(tail);
      setTail(var);
    }
    size++;
  }

  public Node<E> get(int index) {
    if (size() <= 0) {
      return null;
    }       // check for empty list
    if (index < 0 && index >= size()) {   // check for out-of-bounds index
      return null;
    }

    Node<E> current;
    int counter;
    // either count from end or beginning whichever is easier.
    if (index / this.size() < this.size() / 2) {
      current = getHead();
      counter = 0;
      while (counter != index) {
        counter++;
        current = current.getNext();
      }
    } else {
      current = getTail();
      counter = size() - 1;
      while (counter != index) {
        counter--;
        current = current.getPrevious();
      }
    }
    return current;
  }

  @Override
  public Object set(int index, Object element) {
    return null;
  }

  @Override
  public void add(int index, Object element) {

  }

  @Override
  public Object remove(int index) {
    return null;
  }

  @Override
  public int indexOf(Object o) {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator listIterator() {
    return null;
  }

  @Override
  public ListIterator listIterator(int index) {
    return null;
  }

  @Override
  public List subList(int fromIndex, int toIndex) {
    return null;
  }


  /**
   * For the entire list, prints each pilot and their respective area separated on individual lines.
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (Object pilot : this) {
      if (pilot instanceof Payload){
        sb.append(((Payload) pilot).getPilotname());
        sb.append(String.format("\t\t%.2f\r\n", ((Payload) pilot).getPilotarea()));
      }
    }

    return sb.toString();
  }

  /**
   * Can be used in for/while loops.It starts at the Head and ends on the tail.
   * @return Interator
   */
  @Override
  public Iterator iterator() {
    return new DLLIterator<E>();
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public boolean add(Object o) {
    return false;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean addAll(Collection c) {
    return false;
  }

  @Override
  public boolean addAll(int index, Collection c) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public boolean retainAll(Collection c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection c) {
    return false;
  }

  @Override
  public boolean containsAll(Collection c) {
    return false;
  }

  @Override
  public Object[] toArray(Object[] a) {
    return new Object[0];
  }

  @Override
  public void sort(){
    LinkedList<E> nw = this.sort(this,isAscending);
    setHead(nw.getHead());
    setTail(nw.getTail());
    this.size = nw.size();
  }

  public LinkedList sort(LinkedList list, boolean isAscending) {
    //Base Case
    if (list.size()-1 == 0){return list;}

    final int pivot = (list.size()-1)/2;
    LinkedList left = new LinkedList();
    LinkedList right = new LinkedList();
    for (int i = 0; i <= pivot; i++){
      left.add(list.delete(0));
    }
    while (list.size() > 0){
      right.add(list.delete(0));
    }
    left = sort(left,isAscending);
    right = sort(right,isAscending);
    return merge(left,right,isAscending);
  }

  private LinkedList<E> merge(LinkedList left, LinkedList right, boolean isAscending){
    LinkedList<E> rtn = new LinkedList();

    while(left.size() > 0 && right.size() > 0){
      if ( isAscending ?
              ((Payload) left.get(0).getObject()).compareTo(right.get(0).getObject()) < 0 :
              ((Payload) left.get(0).getObject()).compareTo(right.get(0).getObject()) > 0 )
      {
        rtn.add(left.delete(0));
      } else {
        rtn.add(right.delete(0));
      }
    }
    while(left.size() > 0){
      rtn.add(left.delete(0));
    }
    while(right.size() > 0){
      rtn.add(right.delete(0));
    }

    return rtn;
  }



  /**
   * Used to implement an Iterator for LinkedList class. The generic parameter ensures the correct typing
   * transfers to the iterator.
   * @param <E> type of linkedlist
   */
  private class DLLIterator<E> implements Iterator {
    private Node node;
    private Node prev;
    private boolean canRemove;

    private DLLIterator() {
      node = getHead();
      prev = null;
      canRemove = false;
      //looks funny, insures node is of same type as head.

    }

    /**
     * @return true if there is another element to process.
     */
    @Override
    public boolean hasNext() {
      return node != null;
    }

    /**
     * @return the next element.
     * @throws java.util.NoSuchElementException if there are no more elements
     */
    @Override
    public E next() throws NoSuchElementException {
      if (node == null) {
        throw new NoSuchElementException();
      }
      prev = node;
      node = node.getNext();
      canRemove = true;
      return (E) prev.getObject();
    }

    /**
     * Removes the last element returned by the next method.
     * Must be preceded by a call to next()
     */
    @Override
    public void remove() {
      //should remove previous node.
      if (canRemove) {
        decouple(prev);
        size--;
      }
    }
  }
}




