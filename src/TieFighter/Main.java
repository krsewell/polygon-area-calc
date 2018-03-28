/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Jan 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./TieFighter/Main.java
 *
 *  LINKEDLIST SORT IMPLEMENTATION: Lines 129-168
 *  LINKEDLIST MERGESORT HEADER: ./TieFighter/LinkedList.java:129
 *
 *
 */

package TieFighter;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;


class Main {

  private static void area(LinkedList data){
    //print to file
    for (Object pilot : data) {
      if (pilot instanceof Payload){
        ((Payload) pilot).setPilotarea(sum(((Payload) pilot).getCoordinates()));
      }
    }
  }

  private static void fileOutput(File file, String string)throws FileNotFoundException{
    PrintWriter out = new PrintWriter(file);
    out.print(string);
    out.close();
  }

  private static double sum(LinkedList coordinates) {
    //find the last coord
    int lastPOS = -1;
    for (Object o : coordinates) {
      lastPOS++;
    }
    //calculate and return the sum
    double sum = 0.0;
    for (int i = 0; i < lastPOS; i++) {
      Tuple n = (Tuple) coordinates.get(i).getObject();
      Tuple np1 = (Tuple) coordinates.get(i+1).getObject();
      //sum += ((data[i + 1][0] + data[i][0]) * (data[i + 1][1] - data[i][1]));
      sum += (np1.getX() + n.getX()) * (np1.getY() - n.getY());
    }
    return Math.abs(0.5 * sum);
  }

  private static void parseData(final File file, LinkedList data) throws FileNotFoundException {
    Scanner input = new Scanner(file);


    while (input.hasNextLine()) {
      //initialization stuff.
      int data_index = 0;
      int split_index = 1;
      String[] splits = input.nextLine().split(" ");
      data.add(new Node<>(new Payload()));
      Payload varPilot = (Payload) data.get(data_index).getObject();
      varPilot.setPilotname(splits[0]);

      while (checkName(splits[split_index])) {
        varPilot.setPilotname(varPilot.getPilotname() + " " + splits[split_index++]);
      }
      data_index++;
      if (checkCoordinates(splits,split_index)){   //if checkName missed an incompatible name checkCoordinates will fail.
        data.delete(data_index--);                 //so there is no reason to recheck the name that passed checkName.
        continue;                                  //TODO: this should have been continue.
      }

      for (int j = 0; j < splits.length; j++) {    //TODO: removed -1 off by one?
        //format of x,y
        varPilot.getCoordinates().add(new Node(getTuple(splits[split_index])));
        split_index++;

        //breaks loop when last data point read.
        Tuple initial = (Tuple)varPilot.getCoordinates().get(0).getObject();
        Tuple current = (Tuple)varPilot.getCoordinates().get(j).getObject();
        if (j != 0 && initial.isEqual(current)) {
          break;
        }
      }
    }
    input.close();
  }

  private static boolean checkCoordinates(String[] splits, int start) {
    Pattern p = Pattern.compile("\\d+[\\x2e{1}\\d+]?\\x2c\\d+[\\x2e{1}\\d+]?");
    for (int i = start; i < splits.length; i++){  // TODO: removed ambiguous value and assigned it to a variable because
                                                  // TODO: of pass by value this should not have made a difference.
      if(!p.matcher(splits[i]).matches()){
        return false;
      }
    }
    return true;
  }

  private static boolean checkName(String s) {
    Pattern p = Pattern.compile("\\p{Alpha}[\\p{Alnum}\\x27\\x2d]+");
    return p.matcher(s).matches();
  }

  private static void parseCommands(final File file, LinkedList<Command> data) throws FileNotFoundException {
    Scanner input = new Scanner(file);
    while(input.hasNextLine()){
      String str = input.nextLine();
      try {
        Sort temp = new Sort(str);
        data.add(new Node<>(temp));
      } catch (IllegalArgumentException es) {
        try {
          Area temp = new Area(str);
          data.add(new Node<>(temp));
        } catch (IllegalArgumentException ea) {
          try {
            Name temp = new Name(str);
            data.add(new Node<>(temp));
          } catch (IllegalArgumentException en) {
            //not a valid command. abandon line from here.
          }
        }
      }
    }
  }

  private static String processCommands(LinkedList<Payload> database, LinkedList<Command> commandList) {
    StringBuilder sb = new StringBuilder();
    for(Object c : commandList){
      if (c instanceof Command){
        ((Command) c).addData(database);
        ((Command) c).execute();
        sb.append(((Command) c).getOutput());
      }
    }

    return sb.toString();

  }

  private static Tuple getTuple(String convert){
    return new Tuple(
      Double.parseDouble(convert.substring(0,convert.indexOf(','))),
      Double.parseDouble(convert.substring(convert.indexOf(',') + 1))
    );
  }

  public static void main(String[] args) {
    LinkedList<Payload> database = new LinkedList<>();
    LinkedList<Command> commandList = new LinkedList<>();
    File data, commands, output, command_out;
    try {
      //allocated required resources
      data = new File("pilot_routes.txt");
      commands = new File("commands.txt");
      command_out = new File("results.txt");
      output = new File("pilot_areas.txt");

      //get input from file
      if (data.canRead()) {
        parseData(data, database);
      }
      //calculate areas
      area(database);
      if (commands.canRead()){
        parseCommands(commands,commandList);
      }

      fileOutput(command_out,processCommands(database,commandList));
      fileOutput(output,database.toString());

    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
      System.exit(1);
    }

    System.exit(0);
  }


}



