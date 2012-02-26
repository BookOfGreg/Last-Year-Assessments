/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 */
public class Edge {
  public int source;
  public int target;

  Edge(int v, int w){
      source = v;
      target = w;
  }
}
