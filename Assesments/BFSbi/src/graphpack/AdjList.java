/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 */

public interface AdjList {
   // adjacency lists with hidden index.
   int next(); // return next element (from that currently pointed to by index)
   int first();      // return first of adj list
   boolean beyond(); // checks if index is beyond largest vertex number
}
