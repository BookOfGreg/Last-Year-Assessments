/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 */
//interface GraphIO {
//  void scanEZ(Graph G);
//  void scan(Graph G);
//  void show(Graph G);
//}
abstract class GraphIO {
  abstract void scanEZ(Graph G);
  abstract void scan(Graph G);
  abstract void show(Graph G);
}
