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
        //The next three elements should be returned somehow
        ArrayList<ViewEdge> viewEdges = new ArrayList();
        ArrayList<ViewNode> viewNodes = new ArrayList();
        HashMap<Node,ViewNode> nodeToViewNode = new HashMap<Node,ViewNode>();

        HashMap<String,Node> StringToNode = new HashMap<String,Node>();

        String line = this.br.readLine();      //get first line
        line = this.br.readLine();      //get next line (first line is "NODES")

        while (!line.equals("EDGES")){
          String[] tokens = line.split("®");
          String label = tokens[0];
          boolean accepting = tokens[1].equals("true");
          double x = Double.parseDouble(tokens[2]);
          double y = Double.parseDouble(tokens[3]);

          Node newNode = fsm.addNode(label);
          ViewNode newV = new ViewNode(x, y, this.SIZE, this.SIZE, newNode);
          viewNodes.add(newV);

          StringToNode.put(label,newNode);

          nodeToViewNode.put(newNode,newV);

          line = br.readLine();
        }

        line = br.readLine();           //Skip over the "EDGES" line

        while(!line.equals(null)){
          String[] tokens = line.split("®");            //Split the line on spaces
          //String[] transitions = tokens[0].split(",");  //Split the label on commas, for later use
          String label = tokens[0];
          Node to = StringToNode.get(tokens[1]);
          Node from = StringToNode.get(tokens[2]);
          Edge newEdge = fsm.addEdge(from,to,label);
          ViewEdge newVE = new ViewEdge(nodeToViewNode.get(from).getX(),
                                        nodeToViewNode.get(from).getY(),
                                        nodeToViewNode.get(to).getX(),
                                        nodeToViewNode.get(to).getY(),
                                        newEdge);
        }

         return true;
      } catch (IOException ioexcept) {
          return false;
      }
    }

}
