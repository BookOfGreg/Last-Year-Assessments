/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 */
public class GraphLL implements Graph {

    private boolean direct;     // directedness
    private int VIndex, EIndex; // integers for use as indices


    private class Node
      { int val;
        Node succ;
	Node(int v, Node n) { val = v; succ = n; }
      }

    private Node adjls[];

    GraphLL(int V, boolean flag)
      {
        direct = flag;
        VIndex = V;
        EIndex = 0;
        adjls = new Node[V];
      }

    public boolean directed() { return direct; }

    public void setDirected(boolean b){ direct = b;}

    public int numVerts() { return VIndex; }

    public int numEdges() { return EIndex; }

    // scan for edge presence
    public boolean edge(int v, int w){
      boolean notFound = true;
      Node n = adjls[v];
      while (notFound && n!=null){
          if (n.val == w){notFound = false;}
          n = n.succ;
      }
      return notFound;
    }

    public void insert(Edge e){
        int v = e.source;
        int w = e.target;
        adjls[v] = new Node(w, adjls[v]);
        EIndex++;
        if (!direct) { adjls[w] = new Node(v, adjls[w]); }
      }

    // auxilliary for remove method
    // search for w in adj list of v, delete if found
    private boolean searchDel(int v, int w){
      boolean notFound = true;
      Node base = adjls[v];
      Node scout  = adjls[v];
      while (notFound && scout != null){
          if (scout.val == w){
              notFound = false;
              if (scout != base){ base.succ = scout.succ;}
              else {adjls[v] = adjls[v].succ;}
          }
          if (scout != base){ base = base.succ; }
          scout  = scout.succ;
      }
      return !notFound;
    }

    // Remove edge
    public void remove(Edge e){
      int v = e.source;
      int w = e.target;
      if (searchDel(v, w)){
          EIndex-- ;
          if (!direct) { searchDel(w,v); }
      }
    }

    public AdjList getAdjList(int v){ return new AdjLinkedList(v); }
     
    private class AdjLinkedList implements AdjList {
      private int v;
      private Node node;
    
      AdjLinkedList(int v) { this.v = v; node = null; }
    
      public int next(){
        int u = -1;
        if (node != null) {
          node = node.succ;
          if (node != null){ u = node.val;}
        }
        return u;
      }
    
      public boolean beyond() { return node == null; }

      public int first(){
          int u = -1;
          node = adjls[v];
          if (node != null){ u = node.val;}
          return u;
      }
    }

}
