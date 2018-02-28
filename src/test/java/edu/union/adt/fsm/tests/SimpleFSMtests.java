package edu.union.adt.fsm.tests;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.union.adt.fsm.FSM;
import edu.union.adt.fsm.FSMfactory;
import edu.union.adt.fsm.Node;
import edu.union.adt.fsm.Edge;

@RunWith(JUnit4.class)
public class SimpleFSMtests
{
    private FSM fsm;

    @Before
    public void setUp()
    {
        FSM fsm = new FSMfactory().createFSM();
    }

    @After
    public void tearDown()
    {
        fsm = null;
    }

    @Test
    public void construct()
    {
    	fsm = new FSMfactory().createFSM();
	assertFalse("Constructing an FSM does not cause null",fsm.equals(null));
    }

    @Test
    public void nodeConstructor()
    {
      Node a = new Node("a");
      assertEquals("adding a node with label 'a' creates a node with label 'a'","a",a.getLabel());
    }

    @Test
    public void addNode()
    {
      FSM fsm = new FSMfactory().createFSM();
      Node nodeOne = fsm.addNode("a");
      assertEquals("Adding 'a' will cause it to exist in the empty FSM", true, fsm.containsNode(nodeOne));
      Node nodeTwo = fsm.addNode("b");
      assertEquals("Adding 'b' will cause it to exist in the FSM thats not empty before", true, fsm.containsNode(nodeTwo));

    }

    @Test
    public void edgeConstructor()
    {
      FSM fsm = new FSMfactory().createFSM();
      Node a = fsm.addNode("a");
      Node b = fsm.addNode("b");
      Edge ab = new Edge(a,b,"");
    }


    @Test
    public void addEdge()
    {
      FSM fsm = new FSMfactory().createFSM();
      Node a = fsm.addNode("a");
      Node b = fsm.addNode("b");
      Edge ab = fsm.addEdge(a,b,"Hello");
      assertEquals("Adding an edge called 'Hello' between nodes 'a' and 'b' causes it to form","Hello",ab.getLabel());
      // AssertEquals("Adding an edge causes it to exist in the fsm",true,fsm.containsEdge(ab));
    }

    @Test
    public void setNodeLabel()
    {
      FSM fsm = new FSMfactory().createFSM();
      Node a = fsm.addNode("a");
      fsm.setNodeLabel(a,"b");
    }

    @Test
    public void setEdgeLabel()
    {
      FSM fsm = new FSMfactory().createFSM();
      Node a = fsm.addNode("a");
      Node b = fsm.addNode("b");
      Edge ab = fsm.addEdge(a,b,"");
      fsm.setEdgeLabel(ab,"whhat");
    }

    @Test
    public void getMachine()
    {
      FSM fsm = new FSMfactory().createFSM();
      Node a = fsm.addNode("a");
      Node b = fsm.addNode("b");
      Edge ab = fsm.addEdge(a,b,"");
      ArrayList<ArrayList> testMachine = fsm.getMachine();
    }

}
