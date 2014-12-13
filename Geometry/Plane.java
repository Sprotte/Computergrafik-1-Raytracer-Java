package cgm.Geometry;

import cgm.Constants;
import cgm.Hit;
import cgm.Ray;
import cgm.Material.Material;
import cgm.Math.Normal3;
import cgm.Math.Point3;
import cgm.Texture.TexCoord2;

/**
 * The Class Plane.
 * 
 * @author Sprotte 
 * @version 1.0
 */
 
public class Plane extends Geometry {

	/** The known Point. */
	public final Point3 a = new Point3 (0,0,0);

	/** The Normal of the Plane*/
	public final Normal3 n = new Normal3(0,1,0);

	/**
	 * Instantiates a new plane.
	 *
	 * @param color the color
	 * @param a the known Point
	 * @param n the Normal of the Plane
	 * @throws will be thrown if the given argument was null
	 */
	public Plane(final Material material) {
		super(material);
		if (material == null) {
			throw new IllegalArgumentException("The Color cannot be null!");
		}
		if (a == null) {
			throw new IllegalArgumentException("The Point  of the Plane cannot be null!");
		}
		
	}

	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#hit(cgm.Ray)
	 * @throws will be thrown if the given argument was null
	 */
	@Override
	public Hit hit(final Ray r) {
		if (r == null) {
			throw new IllegalArgumentException("The Ray cannot be null!");
		}
		double t2 = r.direction.dot(n);
		if(t2 != 0.0) {
            double t = a.sub(r.origin).dot(n) / t2;
            if (t < Constants.EPSILON) {
                return null;
            }
            Point3 point = r.at(t);
            return new Hit(t, r, this, n,new TexCoord2(point.x, -point.z));
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
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
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
		Plane other = (Plane) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
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
		return "Plane [a=" + a + ", n=" + n + "]";
	}
	public Plane asCelShading(){
		return new Plane(material.getCelShadingMaterial());
	}
	public Plane asSingelColor(){
		return new Plane(material.getSingelColorMaterial());
	}

}
