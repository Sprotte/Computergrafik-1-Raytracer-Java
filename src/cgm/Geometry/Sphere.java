

package cgm.Geometry;

import cgm.Constants;
import cgm.Hit;
import cgm.Ray;
import cgm.Material.Material;
import cgm.Math.Normal3;
import cgm.Math.Point3;
import cgm.Texture.TexCoord2;
 
 
/**
 * The Class Sphere. 
 * 
 * @author Sprotte 
 * @version 1.0
 */
 
public class Sphere extends Geometry {
 
	/** The c. */
	public final Point3 c;
 
	/** The r. */
	public final double r;
 
	/**
	 * Instantiates a new sphere.
	 * 
	 * @param color the color
	 * @param c the center
	 * @param r the radius
	 * @throws will be thrown if the given argument was null
	 */
	public Sphere( final Material material) {
		super(material);
		
		this.c = new Point3(0, 0, 0);
		this.r = 1;
	}
 
	/*
	 * Calculated the Hitpoint with the Ray
	 * @throws will be thrown if the given argument was null
	 */
	@Override
	public Hit hit(final Ray ray) {
		
		if (ray == null) {
			throw new IllegalArgumentException("The Ray cannot be null!");
		}
 
		final double a = ray.direction.dot(ray.direction);
		final double b = ray.direction.dot(ray.origin.sub(c).mul(2));
		
		
		final double cn = (ray.origin.sub(c).dot(ray.origin.sub(c))) - (this.r*this.r);
 
		final double d = (b*b) - (4 * a * cn);
 
		if (d > 0) {
			final double t1 = (-b + Math.sqrt(d)) / (2 * a);
			final double t2 = (-b - Math.sqrt(d)) / (2 * a);			
            
            

			double t = Constants.EPSILON;
			
			if(t2 < Constants.EPSILON && t1 < Constants.EPSILON){
				t = Math.max(t1, t2);
			}
			if(t2 > Constants.EPSILON && t1 > Constants.EPSILON){
				t = Math.min(t1,t2);
			}
			if(t2 > Constants.EPSILON && t1 < Constants.EPSILON){
				t = t2;
			}

			if(t2 < Constants.EPSILON && t1 > Constants.EPSILON){
				t = t1;
			}

			if(t > Constants.EPSILON){
				final Normal3 normal = ray.at(t).sub(this.c).normalized().asNormal();
    			return new Hit(t, ray, this,normal,texFor(ray.at(t)));
			}			
			
			
		} 
 
			return null;
		
 
	}
	public TexCoord2 texFor(final Point3 point){
		if (point == null) {
			throw new IllegalArgumentException("The Point cannot be null!");
		}
		
		double teta = Math.acos(point.y);
		double phi   = Math.atan2(point.x, point.z);
		
		return  new TexCoord2(phi/(Math.PI*2), -teta/Math.PI);
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
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		long temp;
		temp = Double.doubleToLongBits(r);
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
		Sphere other = (Sphere) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
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
		return "Sphere [c=" + c + ", r=" + r + "]";
	}
	
	public Sphere asCelShading(){
		return new Sphere(this.material.getCelShadingMaterial());
	}
	public Sphere asSingelColor(){
		return new Sphere(this.material.getSingelColorMaterial());
	}
}