package edu.union.adt.fsm;
import java.util.*;

public class FSMfactory
{
  /**
   * Create an empty FSM
   */

   public FSM createFSM(boolean test)
   {
     if (test){
       return new FSMtest();
     } else {
       return new ConcreteFSM();
     }
   }

}
