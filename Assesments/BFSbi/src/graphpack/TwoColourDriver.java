/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author mjcollin
 */
public class TwoColourDriver {

        public static void main(String[] args)
      {
        int V, dir;
        boolean direct;
        GraphLL G;
        Scanner input;

        input = new Scanner(System.in);
        System.out.println("Enter file name to scan and press Return");
        String myFileStr = input.next();
        File myDir  = new File("/Users/mjcollin/Graphs"); // TODO Re-set this.
        File myFile = new File(myDir, myFileStr);

        if (myFile.exists()){
          dir = InputOutput.scanDirectedness(myFile);
          // set undirected if file has a 0 at the top
          direct = dir == 0 ? false : true;

          V = InputOutput.scanV(myFile); // get number of vertices from file

          G = new GraphLL(V, direct); // construct the graph

          // now read in edge data from file
          boolean b = InputOutput.scanSimple(G, myFile);

          if (!b){
              System.out.println("Some Problem with input file or supplied V");}
          else if (V > 2001){
            System.out.println("Too many vertices");
          }
          else {
        	  TwoColour tc = new TwoColour(G);
        	  System.out.println(tc.bipartite(G));
        	  // Create object of class TwoColour with param. G
        	  // Search (and colour) using a method of that class
        	  // Give the output you want e.g.
        	  //  -  could print results as table of coloured verts
        	  //     (e.g. extend table from output of BFSDriver.java)
        	  // For assessment just print true if bipartite or false if not.
          }
        }
        else {
            System.out.println("File Not Found. Aborting.");
        }

        return;
      }

}
