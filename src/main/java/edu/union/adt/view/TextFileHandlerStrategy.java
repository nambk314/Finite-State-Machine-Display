package edu.union.adt.view;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import edu.union.adt.fsm.*;

public class TextFileHandlerStrategy implements FileHandlerStrategy{

  public TextFileHandlerStrategy(){

  }

  //given a list of viewNodes, viewEdges, a start Node, and a string file
  //save the given FSM to the file in plaintext format
  public void save(ArrayList<ViewNode> viewNodes, ArrayList<ViewEdge> viewEdges, Node start, String file){
      File f;
      FileWriter fw;
          try {
          f = new File(file);
          fw = new FileWriter(f);
        } catch (IOException ioexcept) {
          return;
        }

        /**
         * Saves the given machine to the file
         * The machine is an ArrayList containing two Arraylists
         * the first contained ArrayList is of ViewNodes
         * the second contained ArrayList is of ViewEdges
         * ViewEdges and ViewNodes are used so position can be saved
         */
          // DECIDE WHETHER USING VIEWNODES / ViewEdges
          // OR NODES / EDGES
          // NODE SECTION USING NODES CURRENTLY
          // EDGES SECTION USING ViewEdges
          try {
            fw.write("The following is a text representation of an FSM\n");
            fw.write("The format is as follows:\n");
            fw.write("Node: <label>, is (not) accepting\n");
            fw.write("Edge: goes from <fromNode label> to <toNodeLabel> with the following transtitons; <edgeLabel>\n");
            fw.write("Begin info:\n");
            for (ViewNode v : viewNodes){
              fw.write("Node: ");
              fw.write(String.valueOf(v.getNode().getLabel()));
              fw.write(", is");
              if (!v.getNode().getAccepting()){
                fw.write(" not");
              }
              fw.write(" accepting.");
              fw.write("\n");
            }
            fw.write("EDGES\n");
            for (ViewEdge e : viewEdges){
              fw.write("Edge: goes from ");
              fw.write(String.valueOf(e.getEdge().getFrom().getLabel()));
              fw.write(" to ");
              fw.write(String.valueOf(e.getEdge().getTo().getLabel()));
              fw.write(" with the following transitions; ");
              fw.write(String.valueOf(e.getEdge().getLabel()));
              fw.write("\n");
            }

            fw.close();
            // return true;
        } catch (IOException ioexcept) {
            // return false;
        }
  }

  //given a String filename and an FSM (should be empty), populates the FSM
  //returns an ArrayList with ArrayLists containing ViewNodes, ViewEdges, a HashMap (Node to viewNode), and an FSM
  public boolean load(String textFile, FSM fsm, Display display){
         BufferedReader br;
         File f;
         FileReader fr;
         double SIZE = 50;


          try {
          f = new File(textFile);
          fr = new FileReader(f);
          br = new BufferedReader(fr);
        } catch (IOException ioexcept) {
          return false;
        }

        /**
         * Saves the given machine to the file
         * The machine is an ArrayList containing two Arraylists
         * the first contained ArrayList is of ViewNodes
         * the second contained ArrayList is of ViewEdges
         */
          try {
            //The next three elements should be returned somehow
            ArrayList<ViewEdge> viewEdgeList = new ArrayList();
            ArrayList<ViewNode> viewNodeList = new ArrayList();
            String[] defaultTheme = new String[]{"circle", "black"};

            HashMap<Node,ViewNode> map = new HashMap<Node,ViewNode>();


            HashMap<String,Node> StringToNode = new HashMap<String,Node>();

            String line = br.readLine();      //get first line
            line = br.readLine();      //get down to the appropriate area
            line = br.readLine();
            line = br.readLine();
            line = br.readLine();
            line = br.readLine();

            while (line.charAt(0) != 'E'){
              String[] tokens = line.split(" ");
              String label = tokens[1];
              boolean accepting = tokens[3].equals("accepting");

              Node newNode = fsm.addNode(label);
              ViewNode newV = new ViewNode(1, 1, SIZE, SIZE, newNode, defaultTheme);
              display.viewNodeList.add(newV);

              StringToNode.put(label,newNode);

              map.put(newNode,newV);

              line = br.readLine();
            }


            line = br.readLine();           //Skip over the "EDGES" line
            while(!line.equals(null)){
              String[] tokens = line.split("®");            //Split the line on spaces
              //String[] transitions = tokens[0].split(",");  //Split the label on commas, for later use
              String label = tokens[10];
              Node to = StringToNode.get(tokens[5]);
              Node from = StringToNode.get(tokens[3]);
              Edge newEdge = fsm.addEdge(from,to,label);
              ViewEdge newVE = new ViewEdge(map, newEdge);
              display.viewEdgeList.add(newVE);
            }

             display.viewNodeList = new ArrayList<ViewNode>(viewNodeList);
             display.viewEdgeList = new ArrayList<ViewEdge>(viewEdgeList);
             display.map = new HashMap<Node,ViewNode>(map);
             fsm.notifyListeners();
             return true;
          } catch (IOException ioexcept) {
              return false;
          }

  }

}
