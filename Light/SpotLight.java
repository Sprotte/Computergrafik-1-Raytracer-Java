package cgm.Light;


import cgm.Hit;
import cgm.Ray;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Math.Point3;
import cgm.Math.Vector3;


/**
 * The Class SpotLight.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class SpotLight extends Light {

	/** The position. */
	public final Point3 position;
	
	/** The direction. */
	public final Vector3 direction;
	
	/** The half angle. */
	public final double halfAngle;

	/**
	 * Instantiates a new spot light.
	 *
	 * @param color the color
	 * @param position the position
	 * @param direction the direction
	 * @param halfAngle the half angle
	 * @throws will be thrown if the given argument was null
	 */
	public SpotLight(final MyColor color, final Point3 position,
			final Vector3 direction, double halfAngle,final boolean castShadow) {
		super(color, castShadow);
		if (position == null) {
			throw new IllegalArgumentException("The Position cannot be null!");
		}
		if (direction == null) {
			throw new IllegalArgumentException("The Direction cannot be null!");
		}		
		this.position = position;
		this.direction = direction;
		this.halfAngle = halfAngle;
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#toString()
	 */
	@Override
	public String toString() {
		return "SpotLight [position=" + position + ", direction=" + direction
				+ ", halfAngle=" + halfAngle + "]";
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
		if (Math.sin(position.sub(point).normalized().x(direction).magnitude) <= halfAngle) {
			
			Hit hit = world.hit(new Ray(point, directionFrom(point)));
			if (hit != null) {
				double t1 = (position.sub(point).magnitude)	/ (directionFrom(point).magnitude);
				if (hit.t < t1) {
					return false;
				} else {
					return true;
				}
			}
			if (hit == null) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#directionFrom(cgm.Math.Point3)
	 */
	@Override
	public Vector3 directionFrom(final Point3 point) {
		if (point == null) {
			throw new IllegalArgumentException("The Point cannot be null!");
		}
		return direction.mul(-1.0);
	}

	/* (non-Javadoc)
	 * @see cgm.Light.Light#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		long temp;
		temp = Double.doubleToLongBits(halfAngle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SpotLight other = (SpotLight) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (Double.doubleToLongBits(halfAngle) != Double
				.doubleToLongBits(other.halfAngle))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

}
