package capstone;

/**
 * A class that holds a bunch of primitive operations for use in geometric
 * applications.
 * 
 * @author
 *
 */
public class doLinesIntersect {

	/* ******************************************************* */
	/* ***********Auxiliary routines for use.*************************** */
	/* ******************************************************* */

	/* ******************************************************* */
	private static int sign(long x) {
		if (x == 0)
			return (0);
		else if (x < 0)
			return (-1);
		else
			return (1);
	}

	/* ******************************************************* */
	/* *******************actual routines******************************** */
	/* ************************************************************** */

	/*****************************************/
	/*
	 * determines the crossproduct of two vectors from the origin. The vectors are
	 * represented as points.
	 */
	private static double crossproduct(Point pt1, Point pt2) {
		return (((long) (pt1.getX() * pt2.getY())) - ((long) (pt2.getX() * pt1.getY())));
	}

	/* ************************************************************** */
	/*
	 * determines the dotproduct of two vectors from the origin. The vectors are
	 * represented as points.
	 */
	private static double dotproduct(Point pt1, Point pt2) {
		return (((long) (pt1.getX() * pt2.getX())) + ((long) (pt1.getY() * pt2.getY())));
	}

	/* ******************************************************* */
	/*
	 * Determines if segment (p1,p2) crosses the line of segment (p3,p4). From
	 * Cormen, Leiserson and Rivest, pg 889-890
	 */
	/* Assumes non-zero length segments */
	private static boolean cross(Point p1, Point p2, Point p3, Point p4) {
		int sign1, sign2;
		Point v31, v21, v41;

		v31 = new Point(p3.getX() - p1.getX(), p3.getY() - p1.getY());

		v21 = new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		v41 = new Point(p4.getX() - p1.getX(), p4.getY() - p1.getY());
		sign1 = sign((((long) v31.getX()) * v21.getY()) - (((long) v21.getX()) * v31.getY()));
		sign2 = sign((((long) v41.getX()) * v21.getY()) - (((long) v21.getX()) * v41.getY()));
		if ((sign1 == 0) || (sign2 == 0))
			return (true);
		else
			return (sign1 != sign2);
	}

	/* ******************************************************** */
	/*
	 * Determines if segment (p1,p2) intesects segment (p3,p4). From discussion in
	 * Cormen, Leiserson and Rivest, pg 889-890
	 */
	/* Assumes non-zero length segments */
	private static boolean intersect(Point p1, Point p2, Point p3, Point p4) {

		long x1hat, x2hat, x3hat, x4hat;
		long y1hat, y2hat, y3hat, y4hat;

		x1hat = Math.min(p1.getX(), p2.getX());
		x2hat = Math.max(p1.getX(), p2.getX());
		x3hat = Math.min(p3.getX(), p4.getX());
		x4hat = Math.max(p3.getX(), p4.getX());

		y1hat = Math.min(p1.getY(), p2.getY());
		y2hat = Math.max(p1.getY(), p2.getY());
		y3hat = Math.min(p3.getY(), p4.getY());
		y4hat = Math.max(p3.getY(), p4.getY());

		/* If bounding rectangles intersect */
		if ((x2hat >= x3hat) && (x4hat >= x1hat) && (y2hat >= y3hat) && (y4hat >= y1hat))
			return (cross(p1, p2, p3, p4) && cross(p3, p4, p1, p2));
		else
			return (false);
	}

	/******************************************************** */
	/* determines if a (p1,p2) crosses (p3,p4) in the middle */
	/* has problems with overlapping segments with common endpoint. */
	private static boolean crossmiddle(Point p1, Point p2, Point p3, Point p4)

	{
		return (intersect(p1, p2, p3, p4) /* they cross */
				&& !p1.equals(p3) /* and don't share an endpoint */
				&& !p1.equals(p4) && !p2.equals(p3) && !p2.equals(p4));
	}

	/**
	 * Determines if the two edges cross each other. Edges are defined to "cross" if
	 * the interior of the edge overlap. In particular, if the edges share an
	 * endpoint but no other points, then that is okay. EG: (1,1) -- (2,2) and (1,1)
	 * --- (1-2) do not "cross" in this context. However, (1,1) --- (2,2) and (1,0)
	 * --- (1,3) do cross because (1,1) is not an endpoint of the second edge.
	 * 
	 * @param e1 one of the edges
	 * @param e2 the other edge
	 * @return true if the two edges cross, subject to conditions above; false
	 *         otherwise
	 */
	public static boolean cross(Edge e1, Edge e2) {
		return crossmiddle(e1.getPoint1(), e1.getPoint2(), e2.getPoint1(), e2.getPoint2());
	}

	/******************************************************** */
	/**
	 * Compute the Euclidean distance between the two points.
	 * 
	 * @param p1 one of the points
	 * @param p2 the other point
	 * @return the length of the edge, as Euclidean distance
	 */
	public static double distance(Point p1, Point p2) {
		double diffx, diffy;

		diffx = p1.getX() - p2.getX();
		diffy = p1.getY() - p2.getY();

		return ((double) (Math.sqrt((double) (diffx * diffx + diffy * diffy))));
	}

	/******************************************************** */
	public static double distsqr(Point p1, Point p2) {
		double diffx, diffy;

		diffx = p1.getX() - p2.getX();
		diffy = p1.getY() - p2.getY();

		return ((double) (diffx * diffx + diffy * diffy));
	}

	/* *************************************** */
	/**
	 * Determines whether p lies to the left of the ray defined by a, b.
	 * 
	 * @param p the point in question
	 * @param a the starting point of the ray
	 * @param b the end point of the ray
	 * @return true if p lies to the left; false otherwise
	 */
	public static boolean onLeft(Point p, Point a, Point b) {
		return ((long) (p.getX() - a.getX()) * (long) (b.getY() - a.getY()) < (long) (p.getY() - a.getY())
				* (long) (b.getX() - a.getX()));
	}

	/* *************************************** */
	/**
	 * Determines whether p lies to the right of the ray defined by a, b.
	 * 
	 * @param p the point in question
	 * @param a the starting point of the ray
	 * @param b the end point of the ray
	 * @return true if p lies to the right; false otherwise
	 */
	public static boolean onRight(Point p, Point a, Point b) {
		return ((long) (p.getX() - a.getX()) * (long) (b.getY() - a.getY()) > (long) (p.getY() - a.getY())
				* (long) (b.getX() - a.getX()));

	}

	/* *************************************** */
	/**
	 * Determines whether the three points lie on the same line.
	 * 
	 * @param p1 one of the points
	 * @param p2 one of the points
	 * @param p3 one of the points
	 * @return true if the three points lie on the same line; false otherwise
	 */
	public static boolean collinear(Point p, Point a, Point b) {
		return ((long) (p.getX() - a.getX()) * (long) (b.getY() - a.getY()) == (long) (p.getY() - a.getY())
				* (long) (b.getX() - a.getX()));

	}
}