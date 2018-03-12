package edu.union.adt.view;
import java.io.*;
import java.util.*;
import edu.union.adt.fsm.*;
import javax.swing.filechooser.FileFilter;
public class GraphicFileHandlerStrategy extends FileFilter implements FileHandlerStrategy {

  private String extension;
  private String description;

  public GraphicFileHandlerStrategy(){
    this.extension = ".graphicFSM";
    this.description = "Graphical FSM file";
  }

  public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().endsWith(extension);
    }
 
    public String getDescription() {
        return description + ", *" +extension;
    }

    public String getExtension() {
      return extension;
    }



  //given a list of viewNodes, viewEdges, a start Node, and a string file
  //save the given FSM to the file in plaintext format
  public void save(ArrayList<ViewNode> viewNodes, ArrayList<ViewEdge> viewEdges, Node start, String file){
      File f;
      FileWriter fw;
          try {
          file += extension;  
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
            fw.write("NODES\n");
            for (ViewNode v : viewNodes){
              fw.write(String.valueOf(v.getNode().getLabel()));
              fw.write("®");
              fw.write(String.valueOf(v.getNode().getAccepting()));
              fw.write("®");
              fw.write(String.valueOf(v.getX()));
              fw.write("®");
              fw.write(String.valueOf(v.getY()));
              fw.write("\n");
            }
            fw.write("EDGES\n");
            for (ViewEdge e : viewEdges){
              fw.write(String.valueOf(e.getEdge().getLabel()));
              fw.write("®");
              fw.write(String.valueOf(e.getEdge().getTo().getLabel()));
              fw.write("®");
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
              ViewEdge newVE = new ViewEdge(map, newEdge, defaultTheme);
              display.viewEdgeList.add(newVE);
              line = br.readLine();
              System.out.println(line);
            }
            display.map = new HashMap<Node, ViewNode>(map);
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
