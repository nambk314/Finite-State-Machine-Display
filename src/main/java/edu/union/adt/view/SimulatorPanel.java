
// package edu.union.adt.view;
// import edu.union.adt.fsm.ConcreteFSM;
// import java.util.ArrayList;

// import java.awt.BorderLayout;
// import java.awt.Container;
// import java.awt.GridLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.JButton;
// import javax.swing.JFileChooser;
// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.JTextField;
// import javax.swing.JComponent;
// import javax.swing.JOptionPane;

// public class SimulatorPanel extends JComponent {
//   //private JTextField filename = new JTextField(), dir = new JTextField();

//   private JButton submit = new JButton("Submit");

//   private JTextField input = new JTextField();

//   Display display;

//   ConcreteFSM finiteStateMachine;

//   FileHandlerStrategy FileHandler;

//   public SimulatorPanel(Display display, ConcreteFSM finiteStateMachine) {
//     // JPanel p = new JPanel();
//     open.addActionListener(new Submit());
//     // p.add(open);
//     save.addActionListener(new SaveL());

//     this.display = display;
//     this.finiteStateMachine = finiteStateMachine;
//     // p.add(save);
//     // Container cp = getContentPane();
//     // cp.add(p, BorderLayout.SOUTH);
//     // dir.setEditable(false);
//     // filename.setEditable(false);
//     // p = new JPanel();
//     // p.setLayout(new GridLayout(2, 1));
//     // p.add(filename);
//     // p.add(dir);
//     // cp.add(p, BorderLayout.NORTH);
//   }

//   // public String getName(){
//   //   return filename.getText();
//   // }

//   public JButton getOpen() {
//     return open;
//   }

//   public JButton getSave() {
//     return save;
//   }

//   class Submit implements ActionListener {

//     public void actionPerformed(ActionEvent e) {
//       JFileChooser c = new JFileChooser();
//       // Demonstrate "Open" dialog:
//       int rVal = c.showOpenDialog(ButtonPanel.this);
//       if (rVal == JFileChooser.APPROVE_OPTION) {
//         String fileName = c.getSelectedFile().getName();

//         FileHandler.load(fileName, finiteStateMachine, display);
//         // FileOpener file = new FileOpener(fileName);
//         // file.open(finiteStateMachine, display);
//       }
//       if (rVal == JFileChooser.CANCEL_OPTION) {

//       }
//     }
//   }

//   class SaveL implements ActionListener {
//     public void actionPerformed(ActionEvent e) {
//       JFileChooser c = new JFileChooser();
//       // Demonstrate "Save" dialog:
//       int rVal = c.showSaveDialog(ButtonPanel.this);
//       if (rVal == JFileChooser.APPROVE_OPTION) {
//         String fileName = c.getSelectedFile().getName();
//         // dir.setText(c.getCurrentDirectory().toString());

//         FileSaver file = new FileSaver(fileName);


//         FileHandler.save(display.viewNodeList, display.viewEdgeList, finiteStateMachine.getStart(), fileName);
//         // ArrayList<ArrayList> ViewMachine = new ArrayList<>();
//         // ViewMachine.add(display.viewNodeList);
//         // ViewMachine.add(display.viewEdgeList);
//         // file.save(ViewMachine);
//       }
//       if (rVal == JFileChooser.CANCEL_OPTION) {
//       }
//     }
//   }

//   // public static void main(String[] args) {
//   //   run(new FileChooserTest(), 250, 110);
//   // }
//   // public int width = 250;
//   // public int height = 100;

//   // public void run() {
//   //   this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//   //   this.setSize(250, 100);
//   //   this.setVisible(true);
//   // }
// } ///:~
