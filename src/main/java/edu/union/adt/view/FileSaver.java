package edu.union.adt.view;

import java.io.*;
import java.util.*;
import edu.union.adt.fsm.*;

public class FileSaver
{
    /**
     *
     */
     File f;
     FileWriter fw;

    public FileSaver(String filename)
    {
      try {
      f = new File(filename);
      fw = new FileWriter(f);
    } catch (IOException ioexcept) {
    }
  }

    /**
     * Saves the given machine to the file
     * The machine is an ArrayList containing two Arraylists
     * the first contained ArrayList is of ViewNodes
     * the second contained ArrayList is of ViewEdges
     * ViewEdges and ViewNodes are used so position can be saved
     */
    public boolean save(ArrayList ViewMachine)
    {
      // DECIDE WHETHER USING VIEWNODES / ViewEdges
      // OR NODES / EDGES
      // NODE SECTION USING NODES CURRENTLY
      // EDGES SECTION USING ViewEdges
      try {
        fw.write("NODES\n");
        ArrayList viewNodes = (ArrayList)ViewMachine.get(0);
        for (Object o : viewNodes){
          ViewNode v = (ViewNode)o;
          fw.write(String.valueOf(v.getNode().getLabel()));
          fw.write(" ");
          fw.write(String.valueOf(v.getNode().getAccepting()));
          fw.write(" ");
          fw.write(String.valueOf(v.getX()));
          fw.write(" ");
          fw.write(String.valueOf(v.getY()));
          fw.write("\n");
        }
        fw.write("EDGES\n");
        ArrayList viewEdges = (ArrayList)ViewMachine.get(0);
        for (Object o : viewEdges){
          ViewEdge e = (ViewEdge)o;
          fw.write(String.valueOf(e.getEdge().getLabel()));
          fw.write(" ");
          fw.write(String.valueOf(e.getEdge().getTo().getLabel()));
          fw.write(" ");
          fw.write(String.valueOf(e.getEdge().getFrom().getLabel()));
          fw.write("\n");
        }
        return true;
    } catch (IOException ioexcept) {
        return false;
    }
    }
}
