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
public class BFSDriver {
    public static void main(String[] args)
      {
        System.out.println("Running BFSDriver");
        int V, s, dir;
        boolean direct;
        GraphLL G;
        Scanner input;

        // boring, but ugly input-output done 
        input = new Scanner(System.in);
        System.out.println("Enter file name to scan and press Return");
        String myFileStr = input.next();
        System.out.println("Enter source vertex number, s, with -1<s<2001");
        s = input.nextInt();
        // You will need to set the directory myDir to where the graph file is kept
        // I'm working on a mac.
        // You may want some windows path e.g. C:\\...
        // See http://download.oracle.com/javase/6/docs/api/java/io/File.html
        File myDir  = new File("/Users/mjcollin/Graphs");
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
            SimpleBFS environ = new SimpleBFS(G);
            environ.search(s);
            InputOutput.display(G);
            environ.printPid();
          }
        }
        else {
            System.out.println("File Not Found. Aborting.");
        }

        return;
      }

}
