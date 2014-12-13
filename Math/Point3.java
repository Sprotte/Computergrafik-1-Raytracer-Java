package cgm.Math;


/**
 *The class Point3 represents a image point in 3d space as three doubles.
 * 
 * @author Sprotte
 * 
 * @version 1.1
 * 
 */
public class Point3 {
	
	/** The x component as a double.*/
	public final double x;
	
	/** The y component as a double.*/
	public final double y;
	
	/** The z component as a double.*/
	public final double z;	
	
	/**
	 * Instantiates a new point with three doubles.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Point3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	/**
	 * Subtracts a point and returns the result as a new vector.
	 *
	 * @param point to subtract
	 * @return the result vector
	 * @throws will be thrown if the given argument was null
	 */
	public Vector3 vector3(final Point3 point){
		if (point == null) {
		    throw new IllegalArgumentException("The point cannot be null!");
		  }
		
		return new Vector3(x - point.x , y - point.y , z - point.z );
	}
	
	/**
	 * Subtracts a vector and returns the result as a new point.
	 *
	 * @param vector to subtract
	 * @return the result point
	 * @throws will be thrown if the given argument was null
	 */
	public Point3 sub(final Vector3 vector){
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }
		
		return new Point3(x - vector.x , y - vector.y , z - vector.z );		
	}
	
	/**
	 * Adds a vector and returns the result as a new point.
	 *
	 * @param vector to add
	 * @return the result point
	 * @throws will be thrown if the given argument was null
	 */
	public Point3 add(final Vector3 vector){
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }
		
		return new Point3(x + vector.x , y + vector.y , z + vector.z );		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point3 \n"
				+  x +"\n"
				+  y +"\n"
				+  z +"\n";				
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3 other = (Point3) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

	public Vector3 sub(Point3 c) {
		// TODO Auto-generated method stub
		return new Vector3(x - c.x , y - c.y , z - c.z );		
	}


}
