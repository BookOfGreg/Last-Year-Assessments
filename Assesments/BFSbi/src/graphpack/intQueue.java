/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphpack;

/**
 *
 * @author mjcollin
 */
public class intQueue {
    private Integer heid;  // integer element on this linked list implementation
    private intQueue tail; // remainder of queue

    //construct empty queue
    public intQueue(){
        heid = null;
        tail = null;
    }

    //construct singleton queue
    public intQueue(int v){
        heid = v;
        tail = null;
    }

    // Enqueue new element at tail
    // matches version in Cormen et al. graphs chapter
    public void enqueue(int v){
        if(this.isEmpty()){
           heid = v;
        }
        else {
          intQueue currNode = this;
          while ( currNode.tail != null){
             currNode = currNode.tail;
          }
          currNode.tail = new intQueue(v);
        }
    }
    
    // Dequeue head of queue
    // Matches version used by Cormen et al. in graph chapter
    public void deQueue(){
        if (this == null || tail == null || heid == null){
            heid = null;
            tail = null;
        }
        else {
          heid = tail.head();      // set first to first elt of new tail
          tail = tail.getTail(); // update to new tail
        }
    }

    // Return head of queue. Do not modify queue.
    // Matches version in Cormen et al. graphs chapter. 
    public Integer head(){
        if (this == null){
            return null;
        }
            else {
                return heid;
            }
         }

    // Check if queue is empty
    public boolean isEmpty(){ return heid == null && tail == null;}

    // get tail of queue
    private intQueue getTail(){
        if (this == null){
            return null;
        }
        else {
            return tail;
        }
    }

    // some paranoia about null pointers may be detectable
}
