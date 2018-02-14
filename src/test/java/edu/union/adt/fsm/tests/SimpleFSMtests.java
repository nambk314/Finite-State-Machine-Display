package edu.union.adt.fsm.tests;

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

@RunWith(JUnit4.class)
public class SimpleFSMtests
{
    private FSM fsm;

    @Before
    public void setUp()
    {
        fsm = new FSMfactory().createFSM();
    }

    @After
    public void tearDown()
    {
        fsm = null;
    }

    @Test
    public void construct()
    {
    	fsm.addNode('A');
      fsm.addNode('b');
    }

}
