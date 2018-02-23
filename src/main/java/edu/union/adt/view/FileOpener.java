//package edu.union.adt.view;
//
//import java.io.*;
//import edu.union.adt.fsm;
//
//public class FileOpener.java
//{
//    /**
//     *
//     */
//     BufferedReader br;
//     File f;
//     FileReader fr;
//
//    public FileSaver(String filename)
//    {
//      f = new File(filename);
//      fr = new FileReader(f);
//      br = new BufferedReader(fr);
//    }
//
//    /**
//     * Saves the given machine to the file
//     * The machine is an ArrayList containing two Arraylists
//     * the first contained ArrayList is of ViewNodes
//     * the second contained ArrayList is of ViewEdges
//     */
//    public save(ArrayList machine)
//    {
//      try {
//      } catch (IOException ioexcept) {
//
//      }
//    }
//
//}

<<<<<<< HEAD
=======
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

    public void FileSaver(String filename)
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
    public boolean save(ArrayList machine)
    {
      // try {
      //   return true;
      // } catch (IOException ioexcept) {
         return false;
      // }
    }

}
>>>>>>> master
