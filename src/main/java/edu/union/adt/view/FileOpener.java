package edu.union.adt.view;

import java.io.*;
import java.util.*;
import edu.union.adt.fsm.*;

public class FileOpener
{
    /**
     *
     */
     BufferedReader br;
     File f;
     FileReader fr;
     double SIZE = 50;

    public void FileOpener(String filename)
    {
      try {
      f = new File(filename);
      fr = new FileReader(f);
      br = new BufferedReader(fr);
    } catch (IOException ioexcept) {

    }
  }

    /**
     * Saves the given machine to the file
     * The machine is an ArrayList containing two Arraylists
     * the first contained ArrayList is of ViewNodes
     * the second contained ArrayList is of ViewEdges
     */
    public boolean open(FSM fsm)
    {
      try {
        ArrayList<viewEdge> viewEdges = new ArrayList();
        ArrayList<viewNode> viewNodes = new ArrayList();

        line = this.br.readLine();      //get first line
        line = this.br.readLine();      //get next line (first line is "NODES")
        while (!line.equals("EDGES")){
          String[] tokens = line.split(" ");
          char label = tokens[0].charAt(0);
          boolean accepting = tokens[1].equals("true");
          double x = Double.parseDouble(tokens[2]);
          double y = Double.parseDouble(tokens[3]);

          Node newNode = fsm.addNode(character);
          ViewNode newV = new ViewNode(x, y, this.SIZE, this.SIZE, newNode);
          viewNodes.add(newV);

          line = br.readLine();
        }
         return true
      } catch (IOException ioexcept) {
         return false;
      }
    }

}
