package cgm.Camera;

import cgm.Ray;
import cgm.Math.Point3;
import cgm.Math.Vector3;

/**
 * The Class OrthograpicCamera creats a camera with a scale factor.
 * @author Sprotte
 * @version 1.0
 */
public class OrthograpicCamera extends Camera {

	// The scale factor
	public final double s;

	/**
	 * Instantiates a new orthograpic camera.
	 *
	 * @param e the position
	 * @param g the gaze direction
	 * @param t the up-vector
	 * @param s the scale factor
	 * @throws will be thrown if the given argument was null
	 */
	public OrthograpicCamera(final Point3 e,final Vector3 g,final Vector3 t,final double s) {		
		super(e, g, t);
		this.s = s;
		
	}

	/* (non-Javadoc)
	 * @see cgm.Camera.Camera#rayFor(int, int, int, int)
	 */
	@Override
	public Ray rayFor(final int width1,final int height1,final int x1,final int y1) {

		final double x = (double) x1;
		final double y = (double) y1;

		final double width = (double) width1;
		final double height = (double) height1;

		final double a = width / height;

		final Vector3 d = super.w.mul(-1);

		final Point3 o = e.add(u.mul(
				a * (s * ((x - ((width - 1) / 2)) / (width - 1)))).add(
				v.mul(s * ((y - ((height - 1) / 2)) / (height - 1)))));

		final Ray result = new Ray(o, d);

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
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(s);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrthograpicCamera other = (OrthograpicCamera) obj;
		if (Double.doubleToLongBits(s) != Double.doubleToLongBits(other.s))
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
		return "OrthograpicCamera [s=" + s + "]";
	}

}
