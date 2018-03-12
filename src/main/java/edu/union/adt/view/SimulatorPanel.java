
package edu.union.adt.view;
import edu.union.adt.fsm.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class SimulatorPanel extends JComponent {
  //private JTextField filename = new JTextField(), dir = new JTextField();

  private JButton simulate = new JButton("Simulate"), next = new JButton("Next"), reset = new JButton("Reset Head");

  //private JTextField input = new JTextField(10);

  private JLabel label = new JLabel();

  Display display;

  FSM finiteStateMachine;

  FileHandlerStrategy FileHandler;

  String simulatedWord;
  ArrayList<String> simulatedWordList;

  public SimulatorPanel(Display display) {
    // JPanel p = new JPanel();
    simulate.addActionListener(new Simulate());
    // p.add(open);
    simulate.setBounds(100,100,140, 40);

    next.addActionListener(new Next());
    next.setBounds(100,100,140,40);

    reset.addActionListener(new Reset());
    reset.setBounds(100,100,140,40);
          //enter name label
    label.setText("Entered String :");
    label.setBounds(10, 10, 100, 100);
          //empty label which will show event after button clicked
    // JLabel label1 = new JLabel();
    // label1.setBounds(10, 110, 200, 100);
          //textfield to enter name
    //input.setBounds(110, 50, 1300, 30);
    this.display = display;
    finiteStateMachine = (FSM) display.finiteStateMachine;
    // p.add(save);
    // Container cp = getContentPane();
    // cp.add(p, BorderLayout.SOUTH);
    // dir.setEditable(false);
    // filename.setEditable(false);
    // p = new JPanel();
    // p.setLayout(new GridLayout(2, 1));
    // p.add(filename);
    // p.add(dir);
    // cp.add(p, BorderLayout.NORTH);
  }

  // public String getName(){
  //   return filename.getText();
  // }

  public JButton getSimulate() {
    return simulate;
  }

  public JButton getNext() {
    return next;
  }
  public JButton getReset() {
    return reset;
  }

  public JLabel getLabel() {
    return label;
  }

  class Simulate implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      simulatedWord = JOptionPane.showInputDialog("Please input a string to simulate");
      simulatedWordList = new ArrayList(Arrays.asList(simulatedWord.split(",")));

      label.setText("Entered String: " + simulatedWord);
    }
  }

  class Reset implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      display.currentViewNodes = new ArrayList();
      display.currentViewNodes.add(display.startViewNode);
      display.update();
    }
  }

  class Next implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        if (simulatedWordList.size() != 0) {
          String currentWord = simulatedWordList.get(0);
          simulatedWordList.remove(0);
          ArrayList<Node> listNode = new ArrayList<Node>();
          ArrayList<ViewNode> currentViewNodes = display.currentViewNodes;
          HashMap<Node, ViewNode> map = display.map;
          for (ViewNode element : currentViewNodes) {
            listNode.add(element.getNode());
          }
          listNode = new ArrayList<Node>(finiteStateMachine.getNextStates(listNode, currentWord));
          display.currentViewNodes = new ArrayList<ViewNode>();
          for (Node element : listNode) {
            ViewNode ViewNodeElement = map.get(element);
            display.currentViewNodes.add(ViewNodeElement);
          }
          // System.out.println(display.currentViewNodes.get(0).getNode().getLabel());
          display.update();
        }

      }
  }

  // public static void main(String[] args) {
  //   run(new FileChooserTest(), 250, 110);
  // }
  // public int width = 250;
  // public int height = 100;

  // public void run() {
  //   this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  //   this.setSize(250, 100);
  //   this.setVisible(true);
  // }
} ///:~
