
package edu.union.adt.view;
import edu.union.adt.fsm.*;
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
import javax.swing.BoxLayout;

public class ButtonPanel extends JComponent {
  //private JTextField filename = new JTextField(), dir = new JTextField();

  private JButton open = new JButton("Open"), save = new JButton("Save");

  Display display;

  FSM finiteStateMachine;

  FileHandlerStrategy fileHandler;

  public ButtonPanel(Display display, FSM finiteStateMachine) {
    // JPanel p = new JPanel();
    open.addActionListener(new OpenL());
    // p.add(open);
    save.addActionListener(new SaveL());

    this.display = display;
    this.finiteStateMachine = finiteStateMachine;

    this.fileHandler = new GraphicFileHandlerStrategy();
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
      int rVal = c.showOpenDialog(ButtonPanel.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        String fileName = c.getSelectedFile().toString();
        display.finiteStateMachine = new ConcreteFSM();                    //this is hardcoded for now; it should test what the type of the FSM is and make a new one of those
        finiteStateMachine = display.finiteStateMachine;
        fileHandler.load(fileName, finiteStateMachine, display);

        // FileOpener file = new FileOpener(fileName);
        // file.open(finiteStateMachine, display);
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {

      }
    }
  }

  class SaveL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println(finiteStateMachine.toString());
      JFileChooser c = new JFileChooser();
      // Demonstrate "Save" dialog:
      int rVal = c.showSaveDialog(ButtonPanel.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        String fileName = c.getSelectedFile().toString();
        // dir.setText(c.getCurrentDirectory().toString());

        fileHandler.save(display.viewNodeList, display.viewEdgeList, finiteStateMachine.getStart(), fileName);
        // ArrayList<ArrayList> ViewMachine = new ArrayList<>();
        // ViewMachine.add(display.viewNodeList);
        // ViewMachine.add(display.viewEdgeList);
        // file.save(ViewMachine);
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
