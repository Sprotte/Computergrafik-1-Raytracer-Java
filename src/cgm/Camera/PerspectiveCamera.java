package cgm.Camera;

import cgm.Ray;
import cgm.Math.Point3;
import cgm.Math.Vector3;

/**
 * The Class Perspective Camera creats a camera with an angle.
 * @author Sprotte
 * @version 1.0
 */
public class PerspectiveCamera extends Camera {

	/** The angle. */
	public final double angle;

	/**
	 * Instantiates a new perspective camera.
	 *
	 * @param e the position
	 * @param g the gaze direction
	 * @param t the up-vector	 * 
	 * @param angle the angle
	 * 
	 */
	public PerspectiveCamera(final Point3 e,final Vector3 g,final Vector3 t,final double angle) {
		super(e, g, t);
		this.angle = angle;

	}

	/* (non-Javadoc)
	 * @see cgm.Camera.Camera#rayFor(int, int, int, int)
	 */
	@Override
	public Ray rayFor(final int width,final int height,final int x,final int y) {

		final Vector3 r = w.mul(-1).mul((height / 2) / Math.tan(angle / 2))
				.add(u.mul((x - ((width - 1) / 2))))
				.add(v.mul((y - ((height - 1) / 2))));

		final Vector3 d = r.normalized();

		final Point3 o = super.e;

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
		temp = Double.doubleToLongBits(angle);
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
		PerspectiveCamera other = (PerspectiveCamera) obj;
		if (Double.doubleToLongBits(angle) != Double
				.doubleToLongBits(other.angle))
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
		return "PerspectiveCamera [angle=" + angle + "]";
	}

}
