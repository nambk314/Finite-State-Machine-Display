// : c14:FileChooserTest.java
// Demonstration of File dialog boxes.
// From 'Thinking in Java, 3rd ed.' (c) Bruce Eckel 2002
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
package edu.union.adt.view;
import edu.union.adt.fsm.ConcreteFSM;
import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class FileChooser extends JComponent {
  //private JTextField filename = new JTextField(), dir = new JTextField();

  private JButton open = new JButton("Open"), save = new JButton("Save");

  Display display;

  ConcreteFSM finiteStateMachine;

  public FileChooser(Display display, ConcreteFSM finiteStateMachine) {
    // JPanel p = new JPanel();
    open.addActionListener(new OpenL());
    // p.add(open);
    save.addActionListener(new SaveL());

    this.display = display;
    this.finiteStateMachine = finiteStateMachine;
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

  public JButton getOpen() {
    return open;
  }

  public JButton getSave() {
    return save;
  }

  class OpenL implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      JFileChooser c = new JFileChooser();
      // Demonstrate "Open" dialog:
      int rVal = c.showOpenDialog(FileChooser.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        String fileName = c.getSelectedFile().getName();
        // dir.setText(c.getCurrentDirectory().toString());

        FileOpener file = new FileOpener(fileName);
        file.open(finiteStateMachine, display);
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
        // filename.setText("You pressed cancel");
        // dir.setText("");
      }
    }
  }

  class SaveL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JFileChooser c = new JFileChooser();
      // Demonstrate "Save" dialog:
      int rVal = c.showSaveDialog(FileChooser.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        String fileName = c.getSelectedFile().getName();
        // dir.setText(c.getCurrentDirectory().toString());

        FileSaver file = new FileSaver(fileName);
        ArrayList<ArrayList> ViewMachine = new ArrayList<>();
        ViewMachine.add(display.viewNodeList);
        ViewMachine.add(display.viewEdgeList);
        file.save(ViewMachine);
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
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
