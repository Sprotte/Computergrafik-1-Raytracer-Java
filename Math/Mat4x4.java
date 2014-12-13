package cgm.Math;

/**
 * The Class Mat3x3 represents a mathematical 3 x 3 matrix as nine doubles.
 * 
 * @author Sprotte
 * 
 * @version 1.0
 */
public class Mat4x4 {

	/** The m11. */
	public final double m11;

	/** The m12. */
	public final double m12;

	/** The m13. */
	public final double m13;

	/** The m14. */
	public final double m14;

	/** The m21. */
	public final double m21;

	/** The m22. */
	public final double m22;

	/** The m23. */
	public final double m23;

	/** The m24. */
	public final double m24;

	/** The m31. */
	public final double m31;

	/** The m32. */
	public final double m32;

	/** The m33. */
	public final double m33;

	/** The m34. */
	public final double m34;

	/** The m41. */
	public final double m41;

	/** The m42. */
	public final double m42;

	/** The m43. */
	public final double m43;

	/** The m44. */
	public final double m44;

	/**
	 * Instantiates a new mat3x3 and calculated the determinant.
	 * 
	 * @param m11
	 *            the m11
	 * @param m12
	 *            the m12
	 * @param m13
	 *            the m13
	 * @param m21
	 *            the m21
	 * @param m22
	 *            the m22
	 * @param m23
	 *            the m23
	 * @param m31
	 *            the m31
	 * @param m32
	 *            the m32
	 * @param m33
	 *            the m33
	 */
	public Mat4x4(final double m11, final double m12, final double m13,
			final double m14, final double m21, final double m22,
			final double m23, final double m24, final double m31,
			final double m32, final double m33, final double m34,
			final double m41, final double m42, final double m43,
			final double m44) {

		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m14 = m14;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m24 = m24;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
		this.m34 = m34;
		this.m41 = m41;
		this.m42 = m42;
		this.m43 = m43;
		this.m44 = m44;

	}

	/**
	 * Multiply the matrix with a matrix and returns the result as an new
	 * matrix.
	 * 
	 * @param matrix
	 *            to multiply
	 * @return the result matrix
	 * @throws will
	 *             be thrown if the given argument was null
	 */
	public Mat4x4 mul(final Mat4x4 m) {
		if (m == null) {
			throw new IllegalArgumentException("The matrix cannot be null!");
		}

		return new Mat4x4(
				m11 * m.m11 + m12 * m.m21 + m13 * m.m31 + m14 * m.m41, m11
						* m.m12 + m12 * m.m22 + m13 * m.m32 + m14 * m.m42, m11
						* m.m13 + m12 * m.m23 + m13 * m.m33 + m14 * m.m43, m11
						* m.m14 + m12 * m.m24 + m13 * m.m34 + m14 * m.m44, m21
						* m.m11 + m22 * m.m21 + m23 * m.m31 + m24 * m.m41, m21
						* m.m12 + m22 * m.m22 + m23 * m.m32 + m24 * m.m42, m21
						* m.m13 + m22 * m.m23 + m23 * m.m33 + m24 * m.m43, m21
						* m.m14 + m22 * m.m24 + m23 * m.m34 + m24 * m.m44, m31
						* m.m11 + m32 * m.m21 + m33 * m.m31 + m34 * m.m41, m31
						* m.m12 + m32 * m.m22 + m33 * m.m32 + m34 * m.m42, m31
						* m.m13 + m32 * m.m23 + m33 * m.m33 + m34 * m.m43, m31
						* m.m14 + m32 * m.m24 + m33 * m.m34 + m34 * m.m44, m41
						* m.m11 + m42 * m.m21 + m43 * m.m31 + m44 * m.m41, m41
						* m.m12 + m42 * m.m22 + m43 * m.m32 + m44 * m.m42, m41
						* m.m13 + m42 * m.m23 + m43 * m.m33 + m44 * m.m43, m41
						* m.m14 + m42 * m.m24 + m43 * m.m34 + m44 * m.m44);

	}

	/**
	 * Multiply the matrix with a vector3 and returns the result as an new
	 * vector3.
	 * 
	 * @param vector3
	 *            to multiply
	 * @return the result vector3
	 * @throws will
	 *             be thrown if the given argument was null
	 */
	public Vector3 mul(final Vector3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		return new Vector3(vector.x * this.m11 + vector.y * this.m12 + vector.z
				* this.m13, vector.x * this.m21 + vector.y * this.m22
				+ vector.z * this.m23, vector.x * this.m31 + vector.y
				* this.m32 + vector.z * this.m33);

	}

	/**
	 * Multiply the matrix with a point3 and returns the result as an new
	 * point3.
	 * 
	 * @param point3
	 *            to multiply
	 * @return the result point3
	 * @throws will
	 *             be thrown if the given argument was null
	 */
	public Point3 mul(final Point3 point) {
		if (point == null) {
			throw new IllegalArgumentException("The point cannot be null!");
		}

		return new Point3(point.x * this.m11 + point.y * this.m12 + point.z
				* m13 + m14, point.x * this.m21 + point.y * this.m22 + point.z
				* m23 + m24, point.x * this.m31 + point.y * this.m32 + point.z
				* m33 + m34);

	}

	public Mat4x4 transposed() {
		return new Mat4x4(m11, m21, m31, m41, m12, m22, m32, m42, m13, m23,
				m33, m43, m14, m24, m34, m44);
	}

}
