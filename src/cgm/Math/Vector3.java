package cgm.Math;

/**
 * The Class Vector3 represents a mathematical vector with 3 values as three doubles.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class Vector3 {
	
	/** The x component as a double. */
	public final double x;
	
	/** The y component as a double. */
	public final double y;
	
	/** The z. component as a double. */
	public final double z;
	
	/** The magnitude. */
	public final double magnitude;
	
	
	/**
	 * Instantiates a new vector3 and calculated the magnitude.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Vector3(final double x,final double y,final double z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		magnitude = Math.sqrt( x * x + y * y + z * z );
		
	}
	
	/**
	 * Adds a vector and returns the results as a new vector3.
	 *
	 * @param vector to add
	 * @return the results vector
	 * @throws will be thrown if the given argument was null
	 */
	public Vector3 add(final Vector3 vector){
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }
		
		return new Vector3( x + vector.x , y + vector.y , z + vector.z );		
		
	}
	
	/**
	 * Adds a normal and returns the results as a new vector3.
	 *
	 * @param normal to add
	 * @return the result vector3
	 * @throws will be thrown if the given argument was null
	 */
	public Vector3 add(final Normal3 normal){
		if (normal == null) {
		    throw new IllegalArgumentException("The normal cannot be null!");
		  }
		
		return new Vector3( x + normal.x , y + normal.y , z + normal.z );
		
		
	}
	
	/**
	 * Subtracts a normal and returns the result as a new vector.
	 *
	 * @param normal to subtract
	 * @return the result vector
	 * @throws will be thrown if the given argument was null
	 */
	public Vector3 sub(final Normal3 normal){
		if (normal == null) {
		    throw new IllegalArgumentException("The normal cannot be null!");
		  }
		
		return new Vector3( x - normal.x , y - normal.y , z - normal.z );
		
		
	}
	
	/**
	 * Multiply the vector with a double and returns the result as an new vector3. (scalar multiplication)
	 *
	 * @param double to multiply
	 * @return the result vector
	 */
	public Vector3 mul(final double value){
		
		
		return  new Vector3( x * value , y * value , z * value );
		
	}
	
	/**
	 * Calculated the scalar product with a vector3 and returns the result as an new double.
	 *
	 * @param vektor3 to multiply
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
	 * Calculated the scalar product with a normal and returns the result as an new double.
	 *
	 * @param normal to multiply
	 * @return the result double
	 * @throws will be thrown if the given argument was null
	 */
	public double dot(final Normal3 normal){
		if (normal == null) {
		    throw new IllegalArgumentException("The normal cannot be null!");
		  }
		
		final double result  =  x * normal.x + y * normal.y + z * normal.z ;
		return result;
	}
	
	/**
	 * Returns the normalized vector of this object as a new vector3. 
	 *
	 * @return the result vector3
	 */
	public Vector3 normalized(){
		
		return new Vector3( x / magnitude , y / magnitude , z / magnitude );
		
	} 
	
	/**
	 * Returns this object as a new normal3.
	 *
	 * @return the normal
	 */
	public Normal3 asNormal(){
		
		return new Normal3(x, y, z);
		
	}
	
	/**
	 * Reflected this object at the given normal3 and returns the result as a new vector3.
	 *
	 * @param normal3 the normal3
	 * @return the result vector3
	 * @throws will be thrown if the given argument was null
	 */
	public Vector3 reflectedOn(final Normal3 normal){
		if (normal == null) {
		    throw new IllegalArgumentException("The normal cannot be null!");
		  }
		
		final double dot = dot(normal);
		return new Vector3( 2 * dot * normal.x - x , 2 * dot * normal.y - y , 2 * dot * normal.z - z );			
	}
	
	/**
	 * Calculated the cartesian product with a vector3.
	 *
	 * @param vector3 multiply
	 * @return the result vector3
	 * @throws will be thrown if the given argument was null
	 */
	public Vector3 x(final Vector3 vector){
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }
		
		return new Vector3(
												y * vector.z - z * vector.y,
												z * vector.x - x * vector.z,
												x * vector.y - y * vector.x
											);
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vector3 \n"
				+  x +"\n"
				+  y +"\n"
				+  z +"\n"
				+ "Magnitude: "+ magnitude +"\n"; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(magnitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Vector3 other = (Vector3) obj;
		if (Double.doubleToLongBits(magnitude) != Double
				.doubleToLongBits(other.magnitude))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
	

}
