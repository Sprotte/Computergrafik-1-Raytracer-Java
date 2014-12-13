package cgm.Math;

import cgm.Ray;

/**
 * This class conducts the transformation with a unit-matrix. 
 * @author Sprotte
 * @version 1.0 
 * 
 */
public class Transform {
	/**
	 * The matrix
	 */
	public final Mat4x4 m;
	/**
	 * The inverse matrix of m.
	 */
	public final Mat4x4 i;

	/**
	 * the public Constructor initializes the transformation with a unit-matrix
	 */
	public Transform() {
		m = new Mat4x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);

		i = m;
	}

	/**
	 * the private constructor initalizes the transformation of the matrix m and
	 * i.
	 * 
	 * @param m
	 * @param i
	 */
	private Transform(final Mat4x4 m, final Mat4x4 i) {
		this.m = m;
		this.i = i;
	}

	/**
	 * This method appends a translate transformation by given factors to an
	 * existing transformation and returns a new transformation object.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return Transform
	 */

	public Transform translate(final Point3 v) {
		final Transform transform = new Transform(new Mat4x4(1, 0, 0, v.x, 0,
				1, 0, v.y, 0, 0, 1, v.z, 0, 0, 0, 1), new Mat4x4(1, 0, 0, -v.x,
				0, 1, 0, -v.y, 0, 0, 1, -v.z, 0, 0, 0, 1));

		return new Transform(m.mul(transform.m), transform.i.mul(i));
	}

	/**
	 * This method appends a scaled transformation by given factors for the axis
	 * to an existing transformation and returns a new transformation object.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return Transform
	 */

	public Transform scale(final Point3 v) {
		final Transform transform = new Transform(new Mat4x4(v.x, 0, 0, 0, 0,
				v.y, 0, 0, 0, 0, v.z, 0, 0, 0, 0, 1), new Mat4x4(1.0 / v.x, 0,
				0, 0, 0, 1.0 / v.y, 0, 0, 0, 0, 1.0 / v.z, 0, 0, 0, 0, 1));

		return new Transform(m.mul(transform.m), transform.i.mul(i));
	}

	/**
	 * This method appends a rotation around the x axis to the transformation
	 * and returns a new transformation object.
	 * 
	 * @param angle
	 *            The angle of the rotation in radians.
	 * @return Transform
	 */

	public Transform rotateX(double a) {
		Transform transform = new Transform(new Mat4x4(1, 0, 0, 0, 0,
				Math.cos(a), -Math.sin(a), 0, 0, Math.sin(a), Math.cos(a), 0,
				0, 0, 0, 1), new Mat4x4(1, 0, 0, 0, 0, Math.cos(a),
				Math.sin(a), 0, 0, -Math.sin(a), Math.cos(a), 0, 0, 0, 0, 1));

		return new Transform(m.mul(transform.m), transform.i.mul(i));
	}

	/**
	 * This method appends a rotation around the y axis to the transformation
	 * and returns a new transformation object.
	 * 
	 * @param angle
	 *            The angle of the rotation in radians.
	 * @return Transform
	 */

	public Transform rotateY(double a) {
		Transform transform = new Transform(new Mat4x4(Math.cos(a), 0,
				Math.sin(a), 0, 0, 1, 0, 0, -Math.sin(a), 0, Math.cos(a), 0, 0,
				0, 0, 1), new Mat4x4(Math.cos(a), 0, -Math.sin(a), 0, 0, 1, 0,
				0, Math.sin(a), 0, Math.cos(a), 0, 0, 0, 0, 1));
		return new Transform(m.mul(transform.m), transform.i.mul(i));
	}

	/**
	 * This method appends a rotation around the z axis to the transformation
	 * and returns a new transformation object.
	 * 
	 * @param angle
	 *            The angle of the rotation in radians.
	 * @return
	 */

	public Transform rotateZ(double a) {
		Transform transform = new Transform(new Mat4x4(Math.cos(a),
				-Math.sin(a), 0, 0, Math.sin(a), Math.cos(a), 0, 0, 0, 0, 1, 0,
				0, 0, 0, 1), new Mat4x4(Math.cos(a), Math.sin(a), 0, 0,
				-Math.sin(a), Math.cos(a), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1));

		return new Transform(m.mul(transform.m), transform.i.mul(i));
	}

	/**
	 * The method conducts the transformation of the ray.
	 * 
	 * @param ray
	 * @return transformed ray
	 */
	public Ray mul(final Ray ray) {
		return new Ray(i.mul(ray.origin), i.mul(ray.direction));
	}

	/**
	 * The method conducts the retransformation of the normal.
	 * 
	 * @param n
	 * @return
	 */
	public Normal3 mul(Normal3 n) {
		return i.transposed().mul(n.asVector()).normalized().asNormal();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i == null) ? 0 : i.hashCode());
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transform other = (Transform) obj;
		if (i == null) {
			if (other.i != null)
				return false;
		} else if (!i.equals(other.i))
			return false;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transform [m=" + m + ", i=" + i + "]";
	}

}
