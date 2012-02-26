/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 */
public class GraphMatrix implements Graph {
    private boolean direct;      // directedness
    private int Vindex, Eindex;  // integers for indexing tasks
    private boolean adjMat[][];  // array holding adjacency matrix

    GraphMatrix(int V, boolean flag)
      {
        direct = flag;
        Vindex = V;
        Eindex = 0;
        adjMat = new boolean[V][V];
        // Remember that Java auto-initializes all entries of array to false
      }

    public int numVerts() { return Vindex; }

    public int numEdges() { return Eindex; }

    public boolean directed() { return direct; }

    public boolean edge(int v, int w){ return adjMat[v][w]; }

    public AdjList getAdjList(int v){ return new AdjListArray(v, this); }

    public void insert(Edge e)
      { int v = e.source;
        int w = e.target;
        if (adjMat[v][w] == false) {
          Eindex++;
          adjMat[v][w] = true;
          if (!direct) { adjMat[w][v] = true; }
        }
      }

    public void remove(Edge e)
      { int v = e.source;
        int w = e.target;
        if (adjMat[v][w] == true) {
          Eindex--;
          adjMat[v][w] = false;
          if (!direct) { adjMat[w][v] = false; }
        }
      }
    
}
