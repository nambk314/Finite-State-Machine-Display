package edu.union.adt.fsm;
import java.util.*;

public class FSMfactory
{
  /**
   * Create an empty FSM
   */

   public FSM createFSM()
   {
     // if (test){
       // return new FSMtest();
     // } else {
        FSM butt = new ConcreteFSM();
        return butt;
     // }
   }
}
