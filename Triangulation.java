package capstone;

import java.util.Vector;

public class Triangulation {
	Vector<Edge> trisEdges = new Vector<Edge>();
	Vector<Edge> possEdges = new Vector<Edge>();
	double n = 0;
	int num = 0;

	//triangulation constructor
	public Triangulation(Vector<Edge> AllPotEdges) {
		possEdges=AllPotEdges;
		

	}
	
	public void findTriang(){
		

		for(Edge wEdge : possEdges) {
			
			if( edgeCrossesEdges(trisEdges, wEdge)==false ) {
				
					
					trisEdges.add(wEdge);
					
					System.out.println(" Edge Added: "+ wEdge);
			
			}
			
		}

	}
	
	
	//boolean to test next edge for triangulation against other edges within triangulation
    public static boolean edgeCrossesEdges(Vector<Edge> vecAll, Edge vea) {
    	for(Edge edg : vecAll) {  	
    		
    				if( doLinesIntersect.cross(vea,edg) ) {
//    					
//    					if(doLinesIntersect.distance(vea.getPoint1(), vea.getPoint2())<doLinesIntersect.distance(edg.getPoint1(),edg.getPoint2())) {
//    						
//    						System.out.println("Cannot add edge:" +vea +" \nEdges: " +vea + " and " + edg +" cross");
//    						
//    						vecAll.remove(edg);
//    						if(edgeCrossesEdges(vecAll, vea)==false){
//    							return false;
//    						}
//    						
//    					}
    					
    					return true;
    				}
			
    		}    	
    	
    	return false;	
    }
    
    //return triangulation edges
    public Vector<Edge> returnTriEdge(){
    	return trisEdges;
    }
    public double totalWeight() {
    	for(Edge edgesTri : trisEdges) {
    		num++;
    		n += doLinesIntersect.distance(edgesTri.getPoint1(), edgesTri.getPoint2());
    	}
    	System.out.println("Number of edges: " + num);
    	return n;
    }
    
	public int returnSize() {
		return trisEdges.size();
	}
}
