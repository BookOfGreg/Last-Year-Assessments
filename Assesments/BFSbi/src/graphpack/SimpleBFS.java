/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;


/**
 *
 * @author mjcollin
 */
public class SimpleBFS {

    public GraphLL G;
    enum Tricolour {WHITE, BLACK, GREY}; // The search colours
    int[] pi;                            // parent relation
    int[] d;                             // distance attribute
    Tricolour[] colour;                  // array to hold colour of vertices
    int u;                               // vertex currently being processed
    intQueue Q = new intQueue();         // queue of grey integers

    // constructor for objects of this class
    SimpleBFS(GraphLL Gr){
       G = Gr;
       pi = new int[G.numVerts()];
       d = new int[G.numVerts()];  
       colour = new Tricolour[G.numVerts()];
    }

    // main BFS method for an object G of this class
    //As a point, this is more Breadth First Traversal to Searching for anything.
    public void search(int s){
        
        initSearch();
        colour[s] = Tricolour.GREY;
        pi[s]=-1;
        d[s]=0;
        Q.enqueue(s);
        // Put your BFS loop code here!
        while (!Q.isEmpty()){
        	u = Q.head();
        	Q.deQueue();
        	processVertex(u);
        	processEarlyVertex(u); //this is here for no reason
        	colour[u] = Tricolour.BLACK;
        	AdjList A = G.getAdjList(u);
        	int v = A.next();
        	while (!A.beyond()){
        		if (colour[v]==Tricolour.BLACK){
        			//processed
        			processVertex(v);
        		}
        		if (colour[v]==Tricolour.GREY){
        			//discovered
        			processVertex(v);
        		}
        		if (colour[v]==Tricolour.WHITE){
        			//undiscovered
        			colour[v] = Tricolour.GREY; //make discovered
        			Q.enqueue(v);				//add next to queue
        			pi[v]=u;					//set parent
        			d[v]=d[u]+1;				//set height
        			processVertex(v);
        		}
        		v = A.next();
        	}
        	colour[u]=Tricolour.BLACK;
        	processLateVertex(u); //this is here for no reason
        }
        
            // For future use, insert the following at the
            // appropriate points in the code
            // EARLY. (omitted) process vertex early if you want
            // EDGE.  (omitted) process edge if you want
            // LATE.  (omitted) process vertex late if you want
        
    }
    
    /**
     * Allows access to the inner search(traversal) loop
     * @param v The node identifier
     */
    protected void processVertex(int v) {
		// Method stub
		
	}

    
	protected void processLateVertex(int u2) {
		// Method stub
		
	}

	
	protected void processEarlyVertex(int u2) {
		// Method stub
		
	}

	// initialisation for search
    // -1 means `does not exist'
    protected void initSearch(){
          int i;
          for (i=0; i<G.numVerts(); i++){
        	  processInit(i);
          }
    }
    
    //access to inner initSearch
    protected void processInit(int i){
    	colour[i] = Tricolour.WHITE;
        pi[i] = -1;
    }

    // a method for printing output (ugly boshed together thing)
    public void printPid(){
        System.out.println("v     :     pi[v] :   d[v]   :  colour[v]");
        for (int i=0; i<G.numVerts(); i++){
            System.out.println(
                    i     + "     :     " +
                    pi[i] + "     :     " +
                    d[i]  + "       :     " +
                    colour[i].toString());
        }
    }

}
