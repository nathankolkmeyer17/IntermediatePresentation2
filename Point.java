package capstone;
/**
 * 
 * @author nkolk
 * class to create and utilize point throughout
 */
public class Point {
	
	 private int x;
     
	 private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object aPoint) {
		Point other = (Point)aPoint;
		return this.getX() == other.getX() && this.getY() == other.getY();
	}
	
	public String toString() {
		return "("+ x + "," + y+")";
	}
	
}
