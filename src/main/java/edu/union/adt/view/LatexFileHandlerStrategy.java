package edu.union.adt.view;
import java.io.*;
import java.util.*;
import edu.union.adt.fsm.*;

public class LatexFileHandlerStrategy implements FileHandlerStrategy{

  public LatexFileHandlerStrategy(){

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


	String result = "";
	result = result + "
		\documentclass{article} \n

		\usepackage{pgf} \n
		\usepackage{tikz} \n
		\usetikzlibrary{arrows,automata} \n
		\usepackage[latin1]{inputenc} \n
		\usepackage{verbatim} \n

		\title{Finite State Machine} \n	
		\begin{document} \n

		\begin{comment} \n
		:Title: State machine \n
		\end{comment} \n

		\begin{tikzpicture}[->,>=stealth',shorten >=1pt,auto,node distance=2.8cm,semithick] \n
		\tikzstyle{state}=[fill=red,draw=none,text=white] \n
		\tikzstyle{state, accepting}=[fill=green,draw=none,text=white] ";

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
            result = result + "Nodes:"
            for (int i=0; i<viewNodes.size(); i++) {
              if (i==0) {
		result = result + "\node[initial,state] (" + String.valueOf(v.getNode().getLabel()) + ")" ;
		}
	      else {
		  result = result + "\node[state] (" + String.valueOf(v.getNode().getLabel()) + ")";
	      }


              fw.write("\n");
            }
           result = result + "Nodes:"
            for (int k=0; k<viewEdges.size(); k++) {
              fw.write(String.valueOf(e.getEdge().getLabel()));
            
              fw.write(String.valueOf(e.getEdge().getTo().getLabel()));
           
              fw.write(String.valueOf(e.getEdge().getFrom().getLabel()));
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
          System.out.println(textFile);
          f = new File(textFile);
          fr = new FileReader(f);
          br = new BufferedReader(fr);
        } catch (IOException ioexcept) {
          System.out.println("error 1");

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
            line = br.readLine();      //get next line (first line is "NODES")

            while (!line.equals("EDGES")){
              String[] tokens = line.split("®");
              String label = tokens[0];
              boolean accepting = tokens[1].equals("true");
              double x = Double.parseDouble(tokens[2]);
              double y = Double.parseDouble(tokens[3]);

              Node newNode = fsm.addNode(label);
              ViewNode newV = new ViewNode(x, y, SIZE, SIZE, newNode, defaultTheme);
              display.viewNodeList.add(newV);

              StringToNode.put(label,newNode);

              map.put(newNode,newV);

              line = br.readLine();
            }

            line = br.readLine();           //Skip over the "EDGES" line
            System.out.println(line);
            while(line != null){
              System.out.println(line);
              String[] tokens = line.split("®");            //Split the line on spaces
              //String[] transitions = tokens[0].split(",");  //Split the label on commas, for later use
              String label = tokens[0];
              Node to = StringToNode.get(tokens[1]);
              Node from = StringToNode.get(tokens[2]);
              Edge newEdge = fsm.addEdge(from,to,label);
              ViewEdge newVE = new ViewEdge(map, newEdge);
              display.viewEdgeList.add(newVE);
              line = br.readLine();
              System.out.println(line);
            }
             fsm.notifyListeners();
             return true;
          } catch (IOException ioexcept) {
            System.out.println("error 2");
              return false;
          } catch (NullPointerException nullp){
            return false;
          }

  }

}
