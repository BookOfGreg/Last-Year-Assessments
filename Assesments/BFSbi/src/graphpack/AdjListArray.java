/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 */
public class AdjListArray implements AdjList {

    private int i;     // the invisible index
    // -1 signifies a vertex that does not exist
    private int v; // vertex for which an object of this class will give adj. list
    private Graph Gr; // graph in which we are considering this adj. list

    // construct adjacency list for vertex w in graph G
    // Note: initialize invisible index off adj. list
    AdjListArray(int w, Graph G){ v = w; i = -1; Gr = G;}

    // Note 1.: i stays at -1, if list is empty
    // Note 2.: we are implictly using entire vertex set as basis of adj. list,
    // and just scanning it for those defined edge objects
    public int next(){
        int k;
        for (k=i+1; k < Gr.numVerts(); k++)
          if (Gr.edge(v, k) == true) {i=k;}
          else {k=-1;}
        return k;
      }

    // get the first vertex on adjacency list
    // returns -1 if none
    public int first(){ i = -1; return next(); }

    // check if is beyond vertices in adjacency list
    public boolean beyond() { return i >= Gr.numVerts(); }
}
