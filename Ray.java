package cgm;

import cgm.Math.Point3;
import cgm.Math.Vector3;

/**
 * The Class Ray represents a Ray with an origin and a direction *
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class Ray {

	/** The origin. */
	public final Point3 origin;

	/** The direction. */
	public final Vector3 direction;

	/**
	 * Instantiates a new ray.
	 * 
	 * @param origin
	 *            the origin
	 * @param direction
	 *            the direction
	 */
	public Ray(final Point3 origin, final Vector3 direction) {
		if (origin == null) {
			throw new IllegalArgumentException(
					"The Origin of the Ray cannot be null!");
		}
		if (direction == null) {
			throw new IllegalArgumentException(
					"The Direction of the Ray cannot be null!");
		}

		this.origin = origin;
		this.direction = direction;

	}

	/**
	 * Calculated the Point of the Ray at the given t
	 * 
	 * @param value
	 *            the value
	 * @return the point3
	 */
	public Point3 at(final double value) {

		final Point3 result = origin.add(direction.mul(value));
		return result;
	}

	/**
	 * Calculated the t of the Ray at the given point
	 * 
	 * @param point
	 *            the point
	 * @return the double
	 * @throws will
	 *             be thrown if the given argument was null
	 */
	public double tOf(final Point3 point) {
		if (point == null) {
			throw new IllegalArgumentException("The Point cannot be null!");
		}

		final double result = (point.sub(origin).dot(direction))
				/ direction.dot(direction);
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Ray other = (Ray) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ray [origin=" + origin + ", direction=" + direction + "]";
	}

}
