package projectFour;

/*FAWZIYAH ALEBIOSU CMSC350 PROJECT FOUR
 * This project simulates a java compiler by building a directed graph
 * of classes retrieved from a file. After building the graph, a class
 * can be searhed for using depth-first search and generate a topological sort .
 * After, we shpw the recompilation order of the classes as the output
 * based on the class specified.
 * 
 */

import java.util.*;

//this is the class that desscribes the generic graph, the hashmap, and stack for dfs, as well as methods. 
public class graph<E>{
	//generic graph type
	private ArrayList<Vertex> listOfArrays;

	private HashMap<E, Integer> hashMapIndexToClassName;

	private Vertex weightOfGraph;
	
	
	private int keyForMap;
	private Stack<String> stackForSearch;
	private StringBuilder result;
	
	 public graph() {
		 //constructr  
        listOfArrays = new ArrayList<Vertex>();
        hashMapIndexToClassName = new HashMap <E, Integer>();
        keyForMap = 0;
	 }
	 //now create the array of linkedlists:
	 public void addEdge(E firstVertex, E secondVertex){
	     //add
		 if(!hashMapIndexToClassName.containsKey(firstVertex)){
			 this.addVertex(firstVertex);
		 }
		 if(!hashMapIndexToClassName.containsKey(secondVertex)){
			 this.addVertex(secondVertex);
		 }
		 weightOfGraph = listOfArrays.get(hashMapIndexToClassName.get(firstVertex));
		 weightOfGraph.addNextTo(hashMapIndexToClassName.get(secondVertex));
  }
    //add vertex
	 
	 
	 public void addVertex(E toBeAdded){
		 hashMapIndexToClassName.put(toBeAdded, keyForMap);
		 listOfArrays.add(keyForMap, new Vertex(toBeAdded));
		 keyForMap++;
	 }
	 
	 
	 public String generateTop(E startingPoint)  throws  cycleDetectExcept, wrongClassExcept{
		 stackForSearch = new Stack<String>();
		 result = new StringBuilder();
		    if (!hashMapIndexToClassName.containsKey(startingPoint)) {
		      throw new wrongClassExcept();
		    }
		    
		   
		    for (Vertex i : listOfArrays) {
		    	//enhanced for loop because we don't need counter
		      i.setIsDiscovered(false);
		      i.setIsFinished(false);
		    }
		    
		    
		    this.depthFirstSearch(hashMapIndexToClassName.get(startingPoint));
		  
		    while (!stackForSearch.empty()) {
		      result.append(stackForSearch.pop() + "    ,  ");
		    }
		    return result.toString();
		  }
	  // Depth first search traversal
	  public void depthFirstSearch(int startingPoint) throws cycleDetectExcept {
	    if (listOfArrays.get(startingPoint).getIsDiscovered()) {
	      throw new cycleDetectExcept();
	    }
	    
	    if (listOfArrays.get(startingPoint).getIsFinished()) {
	      return;
	    }
	    
	    listOfArrays.get(startingPoint).setIsDiscovered(true);
	    for (int i = 0; i < listOfArrays.get(startingPoint).getList().size(); i++) 
	    {
	      depthFirstSearch((Integer) listOfArrays.get(startingPoint).getList().get(i));
	    }
	    listOfArrays.get(startingPoint).setIsFinished(true);
	    stackForSearch.push(listOfArrays.get(startingPoint).toString());
	  }

		
	 
    class Vertex {
    	
        private LinkedList<Integer> linkedListOfArrays;
        private E nameClass;
        private boolean isDiscovered;
        private boolean isFinished;

        public Vertex (E name) {
            this.nameClass = name;
            linkedListOfArrays = new LinkedList<Integer>();
            isDiscovered = false;
            isFinished = false;
        
        }
        
        public void addNextTo(int nextToItem){
        	linkedListOfArrays.add(nextToItem);
        	
        }
        public String listToString(){
        	return "(" + linkedListOfArrays + ")";
        }
        
        public String graphToString() {
            return nameClass.toString();
        }
        public void setIsDiscovered(boolean discovered) {
  	      this.isDiscovered = discovered;
  	    }
        public void setIsFinished(boolean finished) {
  	      this.isFinished = finished;
  	    }
        public boolean getIsFinished() {
  	      return isFinished;
  	    }
        public boolean getIsDiscovered() {
  	      return isDiscovered;
  	    }
        public String toString(){
        	return nameClass.toString();
        }
        public LinkedList getList() {
  	      return linkedListOfArrays;
  	    }
    }
   
   
   
   
 /*
    public String toString() {
        String result = "";
        for (int i = 0; i < example.length; i++) {
            result += (i + "===>" + example[i]);

        }
        return result;

    }
   
}
    //create Linkedlist object
     * 
     */
 
   
      





    }


