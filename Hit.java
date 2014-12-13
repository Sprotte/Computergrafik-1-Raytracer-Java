/*
 * 
 */
package cgm;

import cgm.Geometry.Geometry;
import cgm.Math.Normal3;
import cgm.Texture.TexCoord2;

/**
 * The Class Hit represents a hit with an Object *
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class Hit {

	/** The t. */
	final public double t;

	/** The ray. */
	final public Ray ray;

	/** The geo. */
	final public Geometry geo;
	
	/** The normal. */
	final public Normal3 normal;

	final public TexCoord2 texCoord;
	/**
	 * Instantiates a new hit.
	 * 
	 * @param t the t
	 * @param raythe ray
	 * @param geo the geo
	 * @throws will be thrown if the given argument was null
	 */
	public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 normal, final TexCoord2 texCoord) {
		if (ray == null) {
			throw new IllegalArgumentException("The Ray cannot be null!");
		}
		if (geo == null) {
			throw new IllegalArgumentException("The Geometry cannot be null!");

		}
		if (normal == null) {
			throw new IllegalArgumentException("The Normal cannot be null!");
			
		}
		if (texCoord == null) {
			throw new IllegalArgumentException("The TexCoord cannot be null!");
			
		}

		this.t = t;
		this.ray = ray;
		this.geo = geo;
		this.normal = normal;
		this.texCoord = texCoord;
		
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
		result = prime * result + ((geo == null) ? 0 : geo.hashCode());
		result = prime * result + ((ray == null) ? 0 : ray.hashCode());
		long temp;
		temp = Double.doubleToLongBits(t);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hit other = (Hit) obj;
		if (geo == null) {
			if (other.geo != null)
				return false;
		} else if (!geo.equals(other.geo))
			return false;
		if (ray == null) {
			if (other.ray != null)
				return false;
		} else if (!ray.equals(other.ray))
			return false;
		if (Double.doubleToLongBits(t) != Double.doubleToLongBits(other.t))
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
		return "Hit [t=" + t + ", ray=" + ray + ", geo=" + geo + "]";
	}

}
