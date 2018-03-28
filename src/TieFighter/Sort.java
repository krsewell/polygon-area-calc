/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Sort.java
 */

package TieFighter;


public class Sort implements Command {
  private boolean isAscending;
  private boolean wantsArea;
  private boolean wantsName;
  private boolean hasData;
  private LinkedList<Payload> data;

  Sort(String line) throws IllegalArgumentException {
    String[] strArray = line.split(" ");
    if (strArray.length != 3 || strArray[0].compareToIgnoreCase("sort") != 0){
      throw new IllegalArgumentException();
    }
    if (strArray[1].compareToIgnoreCase("area") == 0){
      this.wantsArea = true;
      this.wantsName = false;
    } else if (strArray[1].compareToIgnoreCase("pilot") == 0) {
      this.wantsArea = false;
      this.wantsName = true;
    } else {
      throw new IllegalArgumentException();
    }

    if (strArray[2].compareToIgnoreCase("asc") == 0){
      this.isAscending = true;
    } else if (strArray[2].compareToIgnoreCase("dsc") == 0) {
      this.isAscending = false;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void addData(LinkedList<Payload> data){
    this.data = data;
    this.hasData = true;
  }

  @Override
  public void execute() {
    if (hasData) {
      if(wantsArea){
        data.setAscending(isAscending);

        if(data.isSortByName()) {
          data.toggle();
        }
        data.sort();
      }
      if(wantsName) {
        data.setAscending(isAscending);

        if(!data.isSortByName()) {
          data.toggle();
        }
        data.sort();
      }
    }
  }

  @Override
  public String getOutput() {
    return String.format("Head: %s, Tail: %s\r\n",
            data.getHead().getObject().getValueCompared(),
            data.getTail().getObject().getValueCompared());
  }
}
