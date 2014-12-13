package cgm.Light;


import cgm.Ray;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Math.Point3;
import cgm.Math.Vector3;


/**
 * The Class PointLight.
 * 
 * @author Sprotte Gabi Josef
 * 
 * @version 1.0
 */
public class PointLight extends Light {

	/** The position. */
	public final Point3 position;

	/* (non-Javadoc)
	 * @see cgm.Light.Light#toString()
	 */
	@Override
	public String toString() {
		return "PointLight [position=" + position + "]";
	}

	/**
	 * Instantiates a new point light.
	 *
	 * @param color the color
	 * @param position the position
	 * @throws will be thrown if the given argument was null
	 */
	public PointLight(final MyColor color, final Point3 position,final boolean castShadow) {
		super(color, castShadow);
		if (position == null) {
			throw new IllegalArgumentException("The Position cannot be null!");
		}
		this.position = position;
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
			Ray r = new Ray(point, directionFrom(point));			
			double t1 = r.tOf( position );
			if (world.hit(r).t < t1) {
				return false;
			} else {
				return true;
			}
		}
		
		return true;

	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#directionFrom(cgm.Math.Point3)
	 */
	@Override
	public Vector3 directionFrom(final Point3 point) {
		if (point == null) {
			throw new IllegalArgumentException("The Point cannot be null!");
		}
		return position.sub(point).normalized();
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointLight other = (PointLight) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

}
