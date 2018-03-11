// package edu.union.adt.view;

// import java.io.*;
// import java.util.*;
// import java.util.ArrayList;
// import edu.union.adt.fsm.*;

// public class FileOpener
// {
//     /**
//      *
//      */
//      BufferedReader br;
//      File f;
//      FileReader fr;
//      double SIZE = 50;

//     public FileOpener(String filename)
//     {
//       try {
//       String textFile = filename + ".txt";
//       f = new File(textFile);
//       fr = new FileReader(f);
//       br = new BufferedReader(fr);
//     } catch (IOException ioexcept) {

//     }
//   }

//     /** 
//      * Saves the given machine to the file
//      * The machine is an ArrayList containing two Arraylists
//      * the first contained ArrayList is of ViewNodes
//      * the second contained ArrayList is of ViewEdges
//      */
//     public boolean open(ConcreteFSM fsm, Display display)
//     {
//       try {
//         //The next three elements should be returned somehow
//         ArrayList<ViewEdge> viewEdgeList = new ArrayList();
//         ArrayList<ViewNode> viewNodeList = new ArrayList();
        
//         HashMap<Node,ViewNode> map = new HashMap<Node,ViewNode>();
        

//         HashMap<String,Node> StringToNode = new HashMap<String,Node>();

//         String line = this.br.readLine();      //get first line
//         line = this.br.readLine();      //get next line (first line is "NODES")

//         while (!line.equals("EDGES")){
//           String[] tokens = line.split("®");
//           String label = tokens[0];
//           boolean accepting = tokens[1].equals("true");
//           double x = Double.parseDouble(tokens[2]);
//           double y = Double.parseDouble(tokens[3]);

//           Node newNode = fsm.addNode(label);
//           ViewNode newV = new ViewNode(x, y, this.SIZE, this.SIZE, newNode);
//           display.viewNodeList.add(newV);

//           StringToNode.put(label,newNode);

//           map.put(newNode,newV);

//           line = br.readLine();
//         }

//         line = br.readLine();           //Skip over the "EDGES" line

//         while(!line.equals(null)){
//           String[] tokens = line.split("®");            //Split the line on spaces
//           //String[] transitions = tokens[0].split(",");  //Split the label on commas, for later use
//           String label = tokens[0];
//           Node to = StringToNode.get(tokens[1]);
//           Node from = StringToNode.get(tokens[2]);
//           Edge newEdge = fsm.addEdge(from,to,label);
//           ViewEdge newVE = new ViewEdge(map, newEdge);
//           display.viewEdgeList.add(newVE);
//         }

         

//          display.viewNodeList = new ArrayList<ViewNode>(viewNodeList);
//          display.viewEdgeList = new ArrayList<ViewEdge>(viewEdgeList);         
//          display.map = new HashMap<Node,ViewNode>(map);
//          display.paint();
//          return true;
//       } catch (IOException ioexcept) {
//           return false;
//       }
//     }

// }
