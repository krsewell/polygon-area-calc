/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Tuple.java
 */

package TieFighter;

final class Tuple {
  private double x;
  private double y;
  private Tuple(){}

  public Tuple(double x, double y) {
    setX(x);
    setY(y);
  }

  public double getX() {
    return x;
  }

  private void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  private void setY(double y) {
    this.y = y;
  }

  public boolean isEqual(Tuple that) {
    return this.getX() == that.getX() && this.getY() == that.getY();
  }
}
