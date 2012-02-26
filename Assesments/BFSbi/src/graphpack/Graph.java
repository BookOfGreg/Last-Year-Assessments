/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 *
 */
interface Graph {
  int numVerts();             // number of vertices
  int numEdges();             // number of edges
  boolean directed();         // directedness
  void insert(Edge e);        // insert the edge (no multi-edges)
  void remove(Edge e);        // remove the edge
  boolean edge(int v, int w); // check for edge
  AdjList getAdjList(int v);  // return adjacency list of given vertex
}

