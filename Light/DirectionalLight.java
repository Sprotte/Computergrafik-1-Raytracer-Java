package cgm.Light;

import cgm.Ray;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Math.Point3;
import cgm.Math.Vector3;


/**
 * The Class DirectionalLight.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class DirectionalLight extends Light {

	/** The direction. */
	public Vector3 direction;

	/* (non-Javadoc)
	 * @see cgm.Light.Light#toString()
	 */
	@Override
	public String toString() {
		return "DirectionalLight [direction=" + direction + "]";
	}

	/**
	 * Instantiates a new directional light.
	 *
	 * @param color the color
	 * @param direction the direction
	 * @throws will be thrown if the given argument was null
	 */
	public DirectionalLight(final MyColor color, final Vector3 direction,final boolean castShadow) {
		super(color, castShadow);		
		if (direction == null) {
			throw new IllegalArgumentException("The Direction cannot be null!");

		}
		
		
		this.direction = direction;
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#illuminates(cgm.Math.Point3)
	 */
	@Override
	public boolean illuminates(final Point3 point,final World world) {
		if (point == null) {
			throw new IllegalArgumentException("The Point cannot be null!");
		}
		if (world == null) {
			throw new IllegalArgumentException("The World cannot be null!");
		}

		if (castShadow) {			
			if(world.hit(new Ray(point, directionFrom(point))) == null){
				return true;
			} 	
			else{
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#directionFrom(cgm.Math.Point3)
	 */
	@Override
	public Vector3 directionFrom(final Point3 point) {
		return direction.mul(-1);
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectionalLight other = (DirectionalLight) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		return true;
	}

}
