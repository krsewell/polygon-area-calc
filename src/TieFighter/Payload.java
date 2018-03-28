/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Payload.java
 */

package TieFighter;

class Payload implements Comparable {
  private String pilotname;
  private double pilotarea;
  private LinkedList<Tuple> coordinates;   //if we are going to have to store this for later may as well be here.
  private boolean checkName;
  private boolean checkArea;

  /**
   * By default checkName will be set.
   */
  Payload() {
    coordinates = new LinkedList<>();
    setCheckName(true);
  }

  Payload(String pilotname) {
    coordinates = new LinkedList<>();
    setPilotname(pilotname);
    setCheckName(true);
  }

  Payload(double pilotarea) {
    coordinates = new LinkedList<>();
    setPilotarea(pilotarea);
    setCheckName(true);
  }

  Payload(String pilotname, double pilotarea) {
    coordinates = new LinkedList<>();
    setPilotname(pilotname);
    setPilotarea(pilotarea);
    setCheckName(true);
  }

  public LinkedList getCoordinates() {
    return coordinates;
  }

  public String getPilotname() {
    return pilotname;
  }

  public double getPilotarea() {
    return pilotarea;
  }

  private boolean isCheckName() {
    return checkName;
  }

  public boolean isCheckArea() {
    return checkArea;
  }

  private void setCoordinates(LinkedList<Tuple> coordinates) {
    this.coordinates = coordinates;
  }

  public void setPilotname(String pilotname) {
    this.pilotname = pilotname;
  }

  public void setPilotarea(double pilotarea) {
    this.pilotarea = pilotarea;
  }

  private void setCheckName(boolean checkName) {
    this.checkName = checkName;
  }

  private void setCheckArea(boolean checkArea) {
    this.checkArea = checkArea;
  }

  public void toggleCheck() throws IllegalStateException {
    if (this.isCheckName()) {
      this.setCheckName(false);
      this.setCheckArea(true);
      //redundant check left in-case additional cases are implemented
    } else if (this.isCheckArea()) {
      this.setCheckName(true);
      this.setCheckArea(false);
    } else {
      throw new IllegalStateException("Class is in invalid state.");
    }
    //if it reaches here without any state change, then payload is in invalid state.
  }

  /**
   * Compares Payload to another Payload object based on the current state of 'this' object
   *
   * @param o Object to compare 'this' too
   * @return 0 if equal, positive if greater then, negative if less then.
   */
  @Override
  public int compareTo(Object o) throws IllegalArgumentException, IllegalStateException {
    if (!(o instanceof Payload)) {
      //not comparable to payload
      throw new IllegalArgumentException("Object must be of Payload Type.");
    }

    if (checkName) {
      return compareName((Payload) o);
    } else if (checkArea) {
      return compareArea((Payload) o);
    }

    throw new IllegalStateException("Payload class is in an invalid state");
    //should never run if it does something is wrong.
  }

  public String getValueCompared(){
    if (isCheckName()){
      return pilotname;
    } else {
      return String.format("%.2f",pilotarea);
    }
  }

  /**
   * Compares two payload's name fields
   *
   * @param that Payload object to compare this too
   * @return 0 if equal, positive int for greater then, negative for less then
   */
  private int compareName(Payload that) {
    if (this.getPilotname()
            .equalsIgnoreCase(that.getPilotname())) {
      return 0;
    } else {
      return this.getPilotname()
              .compareToIgnoreCase(that.getPilotname());
    }
  }

  /**
   * Compares two payload's area fields
   *
   * @param that Payload object to compare this too
   * @return 0 if equal, 1 if greater then, -1 if less then
   */
  private int compareArea(Payload that) {
    if (this.getPilotarea() == that.getPilotarea()) {
      return 0;
    } else if (this.getPilotarea() > that.getPilotarea()) {
      return 1;
    } else if (this.getPilotarea() < that.getPilotarea()) {
      return -1;
    }

    return -100; //should never run if it does something is wrong.
  }
}
