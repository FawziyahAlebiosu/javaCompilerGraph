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
//This is the class that deals with the invaild class name exception.

public class wrongClassExcept extends Exception {
	  public wrongClassExcept() {
		    System.out.println("unacceptable class name!");
		  }

		  public void showMessage(){
		    JOptionPane.showMessageDialog(null,"That class name is unacceptable");
		 }
		
}
