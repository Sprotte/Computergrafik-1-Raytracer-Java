/**
 * 
 */
package cgm.Camera;

import cgm.Ray;
import cgm.Math.Point3;
import cgm.Math.Vector3;

/**
 * The Class Camera 
 * @author Sprotte 
 * @version 1.0
 */
public abstract class Camera {

	/** The e the position. */
	public final Point3 e;
	
	/** The g the gaze direction */
	public final Vector3 g;
	
	/** The t the up-factor */
	public final Vector3 t;
	
	/** The u new x axis */
	public final Vector3 u;
	
	/** The v new y axis */
	public final Vector3 v;
	
	/** The w new z axis. */
	public final Vector3 w;

	/**
	 * Instantiates a new camera.
	 *
	 * @param e the position
	 * @param g the gaze direction
	 * @param t the up vector
	 * @throws will be thrown if the given argument was null
	 */
	public Camera(final Point3 e,final Vector3 g,final Vector3 t) {
		if (e == null) {
			throw new IllegalArgumentException("The Point  e cannot be null!");
		}
		if (g == null) {
			throw new IllegalArgumentException("The Vector g cannot be null!");
		}
		if (t == null) {
			throw new IllegalArgumentException("The Vector t cannot be null!");
		}
		
		
		this.e = e;
		this.g = g;
		this.t = t;

		w = new Vector3(-(g.x / g.magnitude), -(g.y / g.magnitude),
				-(g.z / g.magnitude));
       
		u = new Vector3(t.x(w).x / t.x(w).magnitude, t.x(w).y
				/ t.x(w).magnitude, t.x(w).z / t.x(w).magnitude);
		
		v = new Vector3(w.x(u).x, w.x(u).y, w.x(u).z);
		
	}

	/**
	 * Ray for.
	 *
	 * @param width the width
	 * @param height the height
	 * @param x the x
	 * @param y the y
	 * @return the ray
	 */
	public abstract Ray rayFor(final int width,final int height,final int x,final int y);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		result = prime * result + ((g == null) ? 0 : g.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		result = prime * result + ((v == null) ? 0 : v.hashCode());
		result = prime * result + ((w == null) ? 0 : w.hashCode());
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
		Camera other = (Camera) obj;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		if (g == null) {
			if (other.g != null)
				return false;
		} else if (!g.equals(other.g))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		if (u == null) {
			if (other.u != null)
				return false;
		} else if (!u.equals(other.u))
			return false;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		if (w == null) {
			if (other.w != null)
				return false;
		} else if (!w.equals(other.w))
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
		return "Camera [e=" + e + ", g=" + g + ", t=" + t + ", u=" + u + ", v="
				+ v + ", w=" + w + "]";
	}

}
