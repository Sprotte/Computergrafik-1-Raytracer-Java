package cgm.Geometry;

import cgm.Hit;
import cgm.Ray;
import cgm.Material.Material;
import cgm.Math.Normal3;
import cgm.Math.Point3;
import cgm.Texture.TexCoord2;

/**
 * The Class Disk.
 * @author Sprotte 
 * @version 1.0
 */
public class Disk extends Geometry {

	/** The center. */
	public final Point3 center;
	
	/** The radius. */
	public final double radius;
	
	/** The normal. */
	public final Normal3 normal;

	/**
	 * Instantiates a new disk.
	 *
	 * @param color the color
	 * @param center the center
	 * @param radius the radius
	 * @param normal the normal
	 * @throws will be thrown if the given argument was null
	 */
	public Disk(final Material material,final Point3 center,final double radius,final Normal3 normal) {
		super(material);
		if (center == null) {
			throw new IllegalArgumentException("The Center of the Disk cannot be null!");
		}
		if (normal == null) {
			throw new IllegalArgumentException("The Normal of the Disk cannot be null!");
		}
		
		
		this.center = center;
		this.radius = radius;
		this.normal = normal;
	}

	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#hit(cgm.Ray)
	 * @throws will be thrown if the given argument was null
	 */
	@Override
	public Hit hit(final Ray r) {
		if (r == null) {
			throw new IllegalArgumentException("The Raycannot be null!");
		}		
		//Creats a Plane 
		final Plane plane = new Plane(material);
		//Find the hit with plane
		final Hit hit = plane.hit(r);
		if (hit != null) {
			if (hit.t <= 0) {
				
				return null;
			}

			final Point3 point = r.at(hit.t);
			//Checks wheter the point is in the disk
			if (center.sub(point).magnitude < radius * radius) {
				
				return new Hit(hit.t, r, this,hit.normal,new TexCoord2(point.x, -point.z));

			} else {
				return null;
			}
		}
		return null;

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
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + ((normal == null) ? 0 : normal.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
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
		Disk other = (Disk) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (normal == null) {
			if (other.normal != null)
				return false;
		} else if (!normal.equals(other.normal))
			return false;
		if (Double.doubleToLongBits(radius) != Double
				.doubleToLongBits(other.radius))
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
		return "Disk [center=" + center + ", radius=" + radius + ", normal="
				+ normal + "]";
	}

	@Override
	public Geometry asCelShading() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Geometry asSingelColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
