/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Mar 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Command.java
 */

package TieFighter;

interface Command {

  void addData(LinkedList<Payload> data);
  void execute();
  String getOutput();
}
