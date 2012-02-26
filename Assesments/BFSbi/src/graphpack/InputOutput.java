/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

import java.io.File;

/**
 *
 * @author mjcollin
 */
public class InputOutput {
    
 // Method to output list of all edges
 // Not currently needed
 //static Edge[] listEdges(Graph G)
 // { int Eindex = 0;
 //   Edge[] edgelist = new Edge[G.numEdges()];
 //   for (int u = 0; u < G.numVerts(); u++)
 //     {
 //       AdjList A = G.getAdjList(v);
 //       for (int v = A.first(); !A.beyond(); v = A.next())
 //         if (G.directed() || u < v)
 //           edgelist[Eindex++] = new Edge(v, w);
 //    }
 //   return edgelist;
 // }

// Method to print all adjacency lists of graph
static void display(Graph G)
  {
    // System.out.println("vertex: adjacency list");
    // System.out.println("----------------------");
    for (int s = 0; s < G.numVerts(); s++)
      {
        System.out.print(s + ": ");
        AdjList A = G.getAdjList(s);
        for (int t = A.first(); !A.beyond(); t = A.next())
          { System.out.print(t + " "); }
        System.out.println("");
      }
  }

// Simple scan of file to add edges to graph
static boolean scanSimple(Graph G, File F)
  {
    int v = -1;
    int w = -1;
    String vStr;
    String wStr;
    InputScan In = new InputScan(F);
    boolean noProblem = true;

 // Ditch the first two lines of input
    In.dropLine();
    In.dropLine();
// scan in edges
//    for (In.initialize(); noProblem && !In.isEmpty(); ) {
//    In.initialize();
    while (noProblem && !In.isEmpty()){
        vStr = In.getNextString();
        wStr = In.getNextString();
        try {
          v = Integer.parseInt(vStr);
          w = Integer.parseInt(wStr);
        }
        catch (NumberFormatException Nf){
          System.out.println("File not in correct format. ");
        }

        if (v > G.numVerts() || w > G.numVerts()){
           System.out.println("V supplied not big enough. Aborting.");
           noProblem = false;
        }
        else if (v < 0 || w < 0 ){
           System.out.println("File does not have appropriate vertices");
           noProblem = false;
        }
        else { G.insert(new Edge(v, w)); }
    }
    return noProblem;
  }

 // scan for directedness, then num verts, then edges
static int scanDirectedness(File F)
  {
    int  d = -1;
    String dStr;
    InputScan In = new InputScan(F);

  // Deal with the directedness flag from the file
    dStr = In.getNextString();
    d = Integer.parseInt(dStr);
    if (!(d==0 || d==1)){d = -1;}
    return d;
  }

 // scan for directedness, then num verts, then edges
static int scanV(File F)
  {
    int  V = -1;
    String VStr;
    InputScan In = new InputScan(F);

   // Now deal with the number of vertices flag from the file
    In.dropLine();
    VStr = In.getNextString();
    V = Integer.parseInt(VStr);
    return V;
  }
}



