package projectFour;

/*FAWZIYAH ALEBIOSU CMSC350 PROJECT FOUR
 * This project simulates a java compiler by building a directed graph
 * of classes retrieved from a file. After building the graph, a class
 * can be searhed for using depth-first search and generate a topological sort .
 * After, we shpw the recompilation order of the classes as the output
 * based on the class specified.
 * 
 */
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * This is the main class that will collect the path of the file with the 
 * class dependencies, call the build graph methods, and display the recompilation 
 * order back to the user.
 */

public class collectDisplayGUI extends JFrame {
	
    //dimensions
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    
    //panels
    private JPanel enterInputPanel;
    private JPanel panel;
  
    private Handler handler;
    
    //labels
    private JLabel enterInputFileName;
    private JLabel specifyClassToRecompile;
    
    private JTextField fileNameInput;
    private JTextField classNameInput;
   //buttons
    private JButton topologicalOrderButton;
    private JButton buildTheDirectedGraphButton;
   
    private JTextArea recompilationOutput;
    private GridBagConstraints genericLetter;
    
    private Scanner scanExample;
    private StringTokenizer linetoken;
    private String firstVertex;
    private String secondVertex;
    private graph<String> graphofClasses;
    

    // constructor
    public collectDisplayGUI() {
      
      super("Compiler Simulator");
      setSize(WIDTH, HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(true);
      
      panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

      enterInputPanel = new JPanel();
      enterInputPanel.setPreferredSize(new Dimension(700, 200));
      enterInputPanel.setMinimumSize(new Dimension(700, 125));
      enterInputPanel.setMaximumSize(new Dimension(600, 125));
      enterInputPanel.setLayout(new GridBagLayout());
      genericLetter = new GridBagConstraints();
      genericLetter.weightx = 1;
      
   // buttons, and labels stuff 
      enterInputFileName = new JLabel("Input file name:");
      genericLetter.gridx = 0;
      genericLetter.gridy = 0;
      genericLetter.anchor = GridBagConstraints.CENTER;
      
      enterInputPanel.add(enterInputFileName, genericLetter);
      buildTheDirectedGraphButton = new JButton("Build the Graph");
      genericLetter.gridx = 2;
      genericLetter.gridy = 0;
      enterInputPanel.add(buildTheDirectedGraphButton, genericLetter);
      panel.add(enterInputPanel);
      
      specifyClassToRecompile = new JLabel("Class to be recompiled:");
      genericLetter.gridx = 0;
      genericLetter.gridy = 1;
      enterInputPanel.add(specifyClassToRecompile, genericLetter);
      topologicalOrderButton = new JButton("Topological Order");
      genericLetter.gridx = 2;
      genericLetter.gridy = 1;
      
      enterInputPanel.add(topologicalOrderButton, genericLetter);
      fileNameInput = new JTextField(12);
      genericLetter.gridx = 1;
      genericLetter.gridy = 0;
      
      genericLetter.fill = GridBagConstraints.HORIZONTAL;
      enterInputPanel.add(fileNameInput, genericLetter);
      classNameInput = new JTextField(12);
      genericLetter.gridx = 1;
      genericLetter.gridy = 1;
      
      enterInputPanel.add(classNameInput, genericLetter);
      panel.add(enterInputPanel);
      
      handler = new Handler();
      topologicalOrderButton.addActionListener(handler);
      buildTheDirectedGraphButton.addActionListener(handler);
      buildTheDirectedGraphButton.setActionCommand("build the graph");
      topologicalOrderButton.setActionCommand("generate top order");
      
     
     
      recompilationOutput = new JTextArea();
      recompilationOutput.setLineWrap(true);
      recompilationOutput.setWrapStyleWord(true);
      panel.add(recompilationOutput);
      
    
      enterInputPanel.setBorder(BorderFactory.createTitledBorder(" "));
      recompilationOutput.setBorder(BorderFactory.createTitledBorder("Order for Recompilation"));

    

      add(panel);
     }
      
    // Method to make frame actually appear
    public void display() {
     setVisible(true);
    }
     
 // handles the button clicks and call appropriate graph methods
    private class Handler implements ActionListener {
       
       public void actionPerformed(ActionEvent e) {
         // If you build graph, collect the input, read the items in the file, and build the graph
         if("build the graph".equals(e.getActionCommand())) {
          
        	
        	 graphofClasses = new graph<String>();
        	 //file not found exception handling right here
        	  
        	 try{
        		  scanExample = new Scanner(new File(fileNameInput.getText()));
        	    while(scanExample.hasNext()){
        	    	//as long as there is more items on that line, there are more classes
        	        linetoken = new StringTokenizer(scanExample.nextLine());
        	        firstVertex = linetoken.nextToken();
        	        System.out.println(firstVertex + "           first ");//for testing to see if it worked
        	        while(linetoken.hasMoreTokens()) {
        	        	//while there is more items on the page
        	           secondVertex = linetoken.nextToken();
        	           System.out.println(secondVertex + "          second");//for testing
        	           graphofClasses.addEdge(firstVertex, secondVertex);//add the class we just read in as input
        	           }
        	        }
        	    JOptionPane.showMessageDialog(null, "yA GRAPH IS BUILTTT");
        	  }
               catch(FileNotFoundException exception){
            	   JOptionPane.showMessageDialog(null, "You have entered an invalid file name");   
              }
            }
          //meanwhile if user wants to generate top order
          else if("generate top order".equals(e.getActionCommand())) {
           /*call the generateTop method
            and display the final result
            */
           try {
             recompilationOutput.setText(graphofClasses.generateTop(classNameInput.getText()));
           } catch (wrongClassExcept exc) {
             JOptionPane.showMessageDialog(null, "Unacceptable Class Name");
           } catch (cycleDetectExcept ecpetion) {
             JOptionPane.showMessageDialog(null, "Cycle Detected");
           }
         }
       }
     }

  // display GUI
  public static void main(String[] args) {
    collectDisplayGUI gui = new collectDisplayGUI();
    gui.display();
  }
  }





