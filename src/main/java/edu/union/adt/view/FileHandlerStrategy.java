package edu.union.adt.view;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import edu.union.adt.fsm.*;

public interface FileHandlerStrategy {
  //given a list of viewNodes, viewEdges, a start Node, and a string file
  //save the given FSM to the file in plaintext format
  public void save(ArrayList<ViewNode> viewNodes, ArrayList<ViewEdge> viewEdges, Node start, String file);

  //given a String filename, creates a new FSM and populates it
  //returns an ArrayList with ArrayLists containing ViewNodes, ViewEdges, a HashMap (Node to viewNode), and an FSM
  public boolean load(String file, FSM fsm, Display display);

}
