package graphpack;

/**
*
* @author gmyers
*/
public class TwoColour extends SimpleBFS{

	enum Bicolour {RED,BLUE,None};			// colour after processing
    Bicolour[] c2;						// array to test if bicolour graph
	boolean isBicolourable;
    TwoColour(GraphLL Gr) {
		super(Gr);
	}

	@Override
	protected void processVertex(int v) {
		// Method stub
		if (pi[v]==-1){//if root
			c2[v]=Bicolour.RED;
		}else{//if not root
			if (c2[v]==Bicolour.None){//if not coloured
				//Set opposite as parent
				if (c2[pi[v]]==Bicolour.RED){
					c2[v]=Bicolour.BLUE;
				}else{
					c2[v]=Bicolour.RED;
				}
			}else{//if coloured
				//Check if the colour you would match it to is the same as what it is. 
				if ((c2[pi[v]]==c2[v])){
					//Throw something to fail program
					isBicolourable=false;
				}else{
					//Do nothing
				}
			}
		}
	}
	
	@Override
	protected void processInit(int i){
		super.processInit(i);
		c2[i]=Bicolour.None;
	}

	public boolean bipartite(GraphLL g) {
		isBicolourable = true;
		search(g.getAdjList(0).first());
		if (isBicolourable){
			return true;
		}else{
			return false;
		}
	}

}
