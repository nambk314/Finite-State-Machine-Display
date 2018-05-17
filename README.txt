HOW TO USE FSM DISPLAY:

S: create node
E: Click on the node/edge to edit the label
T: create edge (Click on 1 node and then click on the other node to create edge)
C: nothing
D: drag
A: change accepting node
H: set the start node

Press the key and follow with the mouse click behavior to control the FSM
(EX: Click S and then click on the screen => create a node)

Traverse: 

Click on simulate and a type box will pop up
type in words in this format: a,o,p 
Each letter should be separate by a comma (if there is only one letter you don’t need a comma, I believe)
When you click Ok, the string you type in will be shown in the entered string
to traverse through each string, you click “next”.

This release included: 

1. allow users to create Finite State Machines by drawing the states and connecting them with transi- tions.
2. allow users to name/label states and transitions.
3. allow users to mark states as start states and accept states. Accept states should be drawn with con- centric circles.
4. allow users to save/load State Machines to/from files. You can choose/invent the file format, but it would be nice if it was human readable.
5. allow users to move states around using the mouse (and have the transitions stay connected).

6. Simulating FSM - user can give a string and step through the operation with the String.
7. Support non-determinism. Support simulation of non-deterministic FSMs.
8. Support theme for basic color and shape of the node.
9. Saving from 2 different human-readble file format.
10. Implement reset button to reset the display and the FSM

How to run the FSM:
1. Requirement:
- Gradle build tool
2. Steps:
- Clone the repository to your directory.
- run "gradle build" in the terminal
- run "gradle run" in the terminal inside the directory
