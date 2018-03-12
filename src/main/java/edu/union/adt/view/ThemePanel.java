
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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ThemePanel extends JComponent {
  //private JTextField filename = new JTextField(), dir = new JTextField();

  private JComboBox shape, color;

  private JButton ok = new JButton("OK");

  //private JTextField input = new JTextField(10);

  private JLabel label = new JLabel();

  Display display;

  public ThemePanel(Display display) {

    String[] ShapeItems = {"Circle", "Square"};
    shape = new JComboBox(ShapeItems);
    shape.setEditable(false);
    shape.setSelectedItem("Circle");
    String[] ColorItems = {"black", "red", "green"};

    color = new JComboBox(ColorItems);
    color.setEditable(false);
    color.setSelectedItem("black");
    // JPanel p = new JPanel();
    ok.addActionListener(new OK());
    // p.add(open);
    ok.setBounds(100,100,140, 40);   
          //enter name label   
    label.setText("Set Theme: ");
    label.setBounds(10, 10, 100, 100);
          //empty label which will show event after button clicked
    // JLabel label1 = new JLabel();
    // label1.setBounds(10, 110, 200, 100);
          //textfield to enter name
    //input.setBounds(110, 50, 1300, 30);
    this.display = display;
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

  public JComboBox getShapeMenu() {
    return shape;
  }

  public JButton getOK() {
    return ok;
  }

  public JComboBox getColorMenu() {
    return color;
  }

  public JLabel getLabel() {
    return label;
  }

  class OK implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      String selectedShape = (String) shape.getSelectedItem();
      String selectedColor = (String) color.getSelectedItem();
      display.theme[0] = selectedShape;
      display.theme[1] = selectedColor;
      System.out.println(display.theme[0]);
      display.update();
  }

  class Next implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        
        
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
}
}
