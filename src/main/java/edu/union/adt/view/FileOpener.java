package edu.union.adt.view;

import java.io.*;
import edu.union.adt.fsm;

public class FileOpener.java
{
    /**
     *
     */
     BufferedReader br;
     File f;
     FileReader fr;

    public FileSaver(String filename)
    {
      f = new File(filename);
      fr = new FileReader(f);
      br = new BufferedReader(fr);
    }

    /**
     * Saves the given machine to the file
     * The machine is an ArrayList containing two Arraylists
     * the first contained ArrayList is of ViewNodes
     * the second contained ArrayList is of ViewEdges
     */
    public save(ArrayList machine)
    {
      try {
        fw.write("NODES\n")
        for (ViewNode v : machine.get(0)){
          fw.write(v.node.label);
          fw.write(",")
          fw.write(v.node.accept);
          fw.write("\n")
        }
        fw.write("EDGES\n");
        for (ViewEdge e : machine.get(1)){
          fw.write(e.edge.label);
          fw.write(",")
          fw.write(e.edge.to.label);
          fw.write(",")
          fw.write(e.edge.from.label);
          fw.write("\n");
        }
        fw.write("NODES\n")
        for (ViewNode v : machine.get(0)){
          fw.write(v.node.label);
          fw.write(",")
          fw.write(v.node.accept);
          fw.write("\n")
        }
        fw.write("EDGES\n");
        for (ViewEdge e : machine.get(1)){
          fw.write(e.edge.label);
          fw.write(",")
          fw.write(e.edge.to.label);
          fw.write(",")
          fw.write(e.edge.from.label);
          fw.write("\n");
        }
    } catch (IOException ioexcept) {

    }
    }

}
