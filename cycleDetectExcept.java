package projectFour;
/*FAWZIYAH ALEBIOSU CMSC350 PROJECT FOUR
 * This project simulates a java compiler by building a directed graph
 * of classes retrieved from a file. After building the graph, a class
 * can be searhed for using depth-first search and generate a topological sort .
 * After, we shpw the recompilation order of the classes as the output
 * based on the class specified.
 * 
 */

import javax.swing.JOptionPane;

//This is the class that deals with detecting a cycle in the graph
public class cycleDetectExcept extends Exception {
		  public cycleDetectExcept() {
		    System.out.println("Just detected cycle!");
		  }

		  public void showMessageException(){
		    JOptionPane.showMessageDialog(null,"I just detected a cycle!!!!!!!");
		 }

}
