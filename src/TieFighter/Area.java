/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Area.java
 */

package TieFighter;

public class Area implements Command {
  private double check;
  private String output;
  private boolean hasData;
  private LinkedList<Payload> data;

  Area(String check) throws IllegalArgumentException{
    if (check.matches("\\d+\\x2e\\d+")){
      this.check = Double.parseDouble(check);
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
      StringBuilder sb = new StringBuilder();
      if (data.isSortByName()){
        data.toggle();
      }
      boolean found = false;
      for(Object p : data){
        if (((Payload) p).compareTo(new Payload(this.check)) == 0){  //may cause problems with decimal places....
          sb.append(String.format("%.2f \t\tFound\r\n",this.check));
          found = true;
        }
      }
      if (!found){
        sb.append(String.format("%.2f \t\tNot Found\r\n",this.check));
      }
      this.output = sb.toString();
    }
  }

  @Override
  public String getOutput(){
    return this.output;
  }
}
