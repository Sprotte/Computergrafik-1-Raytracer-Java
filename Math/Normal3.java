package cgm.Math;


/**
 *The class Normal3 represents the normal of a line
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class Normal3 {
	
	/** The x component as a double. */
	public final double x;
	
	/** The y component as a double. */
	public final double y;
	
	/** The z component as a double. */
	public final double z;		
	
	/**
	 * Instantiates a new normal from too vectors
	 *
	 * @param vectorA the vector a
	 * @param vectorB the vector b
	 * @throws will be thrown if one or both of the given arguments was null
	 */
	public Normal3(final Vector3 vectorA,final Vector3 vectorB) {
		if ((vectorA == null) || (vectorB == null)) {
		    throw new IllegalArgumentException("The vectors cannot be null!");
		  }
		
		this.x = vectorA.y * vectorB.z - vectorA.z * vectorB.y;
		this.y = vectorA.z * vectorB.x - vectorA.x * vectorB.z;
		this.z = vectorA.x * vectorB.y - vectorA.y * vectorB.x;			
	}
	
	/**
	 * Instantiates a new normal with three double
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Normal3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
	}

	/**
	 * Multiply the normal with a double and returns the result as an new normal. (scalar multiplication)
	 *
	 * @param value to multiply
	 * @return the result normal
	 */
	public Normal3 mul(final double value){
		return new Normal3( x * value , y * value , z * value );
	}
	
	/**
	 * Adds a normal and returns the result as an new normal.
	 *
	 * @param normal to add 
	 * @return the result normal
	 * @throws will be thrown if the given argument was null
	 */
	public Normal3 add(final Normal3 normal){
		if (normal == null) {
		    throw new IllegalArgumentException("The normal cannot be null!");
		  }
		
		return new Normal3( x + normal.x , y + normal.y , z + normal.z );
	}
	
	/**
	 * Calculated the scalar product from a normal and a vector.
	 *
	 * @param vector the vector for the scalar product
	 * @return the result double
	 * @throws will be thrown if the given argument was null
	 */
	public double dot(final Vector3 vector){
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }
		final double result  =  x * vector.x + y * vector.y + z * vector.z ;
		return result;
	}
	
	/**
	 * This method converts the normal to a vector3 object.
	 * @return
	 */
	public Vector3 asVector() {
		return new Vector3(x,y,z);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Normal3 \n"
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
		Normal3 other = (Normal3) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	

}
