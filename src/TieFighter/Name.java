/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Name.java
 */

package TieFighter;

public class Name implements Command{
  private String check;
  private String output;
  private boolean hasData;
  private LinkedList<Payload> data;

  Name(String check) throws IllegalArgumentException{
    if(check.matches("\\p{Alpha}[\\p{Alnum}\\x27\\x2d]+[\\p{Blank}\\p{Alpha}[\\p{Alnum}\\x27\\x2d]+]*")){
      this.check = check;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public void addData(LinkedList<Payload> data){
    this.data = data;
    this.hasData = true;
  }

  @Override
  public void execute() {
    if (hasData) {
      //be careful that only Payload linkedlist are in data.
      StringBuilder sb = new StringBuilder();
      if (!data.isSortByName()){
        data.toggle();
      }
      boolean found = false;
      for(Object p : data){
        if (((Payload) p).compareTo(new Payload(this.check)) == 0){  //implements ignore case automatically
          sb.append(String.format("%s \t\tFound\r\n",this.check));
          found = true;
        }
      }
      if (!found){
        sb.append(String.format("%s \t\tNot Found\r\n",this.check));
      }
      this.output = sb.toString();
    }
  }

  public String getOutput(){
    return this.output;
  }
}
