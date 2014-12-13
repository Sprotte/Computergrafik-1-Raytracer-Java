package cgm.Math;


/**
 * The Class Mat3x3 represents a mathematical 3 x 3 matrix as nine doubles.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class Mat3x3 {

	/** The m11. */
	public final double m11;
	
	/** The m12. */
	public final double m12;
	
	/** The m13. */
	public final double m13;
	
	/** The m21. */
	public final double m21;
	
	/** The m22. */
	public final double m22;
	
	/** The m23. */
	public final double m23;
	
	/** The m31. */
	public final double m31;
	
	/** The m32. */
	public final double m32;
	
	/** The m33. */
	public final double m33;

	/** The determinant. */
	public final double determinant;

	/**
	 * Instantiates a new mat3x3 and calculated the determinant.
	 *
	 * @param m11 the m11
	 * @param m12 the m12
	 * @param m13 the m13
	 * @param m21 the m21
	 * @param m22 the m22
	 * @param m23 the m23
	 * @param m31 the m31
	 * @param m32 the m32
	 * @param m33 the m33
	 */
	public Mat3x3(final double m11, final double m12, final double m13,
			final double m21, final double m22, final double m23,
			final double m31, final double m32, final double m33) {

		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;

		determinant = (m11*m22*m33 + m12*m23*m31 + m13*m21*m32)-(m13*m22*m31 + m11*m23*m32 + m12*m21*m33);
	}

	/**
	 * Multiply the matrix with a matrix and returns the result as an new matrix. 
	 *
	 * @param matrix to multiply
	 * @return the result matrix
	 * @throws will be thrown if the given argument was null
	 */
	public Mat3x3 mul(final Mat3x3 matrix) {
		if (matrix == null) {
		    throw new IllegalArgumentException("The matrix cannot be null!");
		  }	

		return new Mat3x3(
				(m11 * matrix.m11 + m21 * matrix.m12 + m31 * matrix.m13),
				(m12 * matrix.m11 + m22 * matrix.m12 + m32 * matrix.m13),
				(m13 * matrix.m11 + m23 * matrix.m12 + m33 * matrix.m13),

				(m11 * matrix.m21 + m21 * matrix.m22 + m31 * matrix.m23),
				(m12 * matrix.m21 + m22 * matrix.m22 + m32 * matrix.m23),
				(m13 * matrix.m21 + m23 * matrix.m22 + m33 * matrix.m23),

				(m11 * matrix.m31 + m21 * matrix.m32 + m31 * matrix.m33),
				(m12 * matrix.m31 + m22 * matrix.m32 + m32 * matrix.m33),
				(m13 * matrix.m31 + m23 * matrix.m32 + m33 * matrix.m33));
		
	}

	/**
	 * Multiply the matrix with a vector3 and returns the result as an new vector3. 
	 *
	 * @param vector3 to multiply
	 * @return the result vector3
	 * @throws will be thrown if the given argument was null
	 */
	public Vector3 mul(final Vector3 vector) {
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }

		return new Vector3(
				(m11 * vector.x + m21 * vector.y + m31 * vector.z),
				(m12 * vector.x + m22 * vector.y + m32 * vector.z),
				(m13 * vector.x + m23 * vector.y + m33 * vector.z));
		
	}

	/**
	 * Multiply the matrix with a point3 and returns the result as an new point3. 
	 *
	 * @param point3 to multiply
	 * @return the result point3
	 * @throws will be thrown if the given argument was null
	 */
	public Point3 mul(final Point3 point) {
		if (point == null) {
		    throw new IllegalArgumentException("The point cannot be null!");
		  }

		return new Point3(
				(m11 * point.x + m21 * point.y + m31* point.z),
				(m12 * point.x + m22 * point.y + m32 * point.z),
				(m13 * point.x + m23 * point.y + m33 * point.z));
		
	}

	/**
	 * Changes the first column with a vector3.
	 *
	 * @param the replacement vector
	 * @return the result mat3x3
	 * @throws will be thrown if the given argument was null
	 */
	public Mat3x3 changeCol1(final Vector3 vector) {
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }

			
		return new Mat3x3(vector.x,this.m12,this.m13,  vector.y,this.m22,this.m23, vector.z,this.m32,this.m33);
		
		
	}

	/**
	 * Changes the second column with a vector3.
	 *
	 * @param the replacement vector
	 * @return the result mat3x3
	 * @throws will be thrown if the given argument was null
	 */
	public Mat3x3 changeCol2(final Vector3 vector) {
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }
				
		return new Mat3x3(this.m11, vector.x,this.m13, this.m21,vector.y,this.m23,this.m31,vector.z,this.m33);
		
		
	}

	/**
	 ** Changes the third column with a vector3.
	 *
	 * @param the replacement vector
	 * @return the result mat3x3
	 * @throws will be thrown if the given argument was null
	 */
	public Mat3x3 changeCol3(final Vector3 vector) {
		if (vector == null) {
		    throw new IllegalArgumentException("The vector cannot be null!");
		  }
				
		return new Mat3x3(this.m11,this.m12,vector.x,this.m21,this.m22, vector.y,this.m31,this.m32,vector.z);
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mat3x3 \n" + m11 + "  " + m21 + "  " + m31 + "\n" + m12 + "  "
				+ m22 + "  " + m32 + "\n" + m13 + "  " + m23 + "  " + m33
				+ "\n" + "Determinate: " + determinant + "\n";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(determinant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m11);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m12);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m13);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m21);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m22);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m23);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m31);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m32);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m33);
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
		Mat3x3 other = (Mat3x3) obj;
		if (Double.doubleToLongBits(determinant) != Double
				.doubleToLongBits(other.determinant))
			return false;
		if (Double.doubleToLongBits(m11) != Double.doubleToLongBits(other.m11))
			return false;
		if (Double.doubleToLongBits(m12) != Double.doubleToLongBits(other.m12))
			return false;
		if (Double.doubleToLongBits(m13) != Double.doubleToLongBits(other.m13))
			return false;
		if (Double.doubleToLongBits(m21) != Double.doubleToLongBits(other.m21))
			return false;
		if (Double.doubleToLongBits(m22) != Double.doubleToLongBits(other.m22))
			return false;
		if (Double.doubleToLongBits(m23) != Double.doubleToLongBits(other.m23))
			return false;
		if (Double.doubleToLongBits(m31) != Double.doubleToLongBits(other.m31))
			return false;
		if (Double.doubleToLongBits(m32) != Double.doubleToLongBits(other.m32))
			return false;
		if (Double.doubleToLongBits(m33) != Double.doubleToLongBits(other.m33))
			return false;
		return true;
	}

}
