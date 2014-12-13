package cgm.Light;



import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Math.Point3;
import cgm.Math.Vector3;


/**
 * The Class Light.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public abstract class Light {
	
	
	/** The color. */
	public final MyColor color;
	
	/** The bool wether the light casts a shadow. */
	public final boolean castShadow;

	/**
	 * Instantiates a new light.
	 *
	 * @param color the color
	 * @param castShadow the boolean
	 * @throws will be thrown if the given argument was null
	 */
	public Light(final MyColor color,final boolean castShadow) {
		if (color == null) {
			throw new IllegalArgumentException("The Color cannot be null!");
		}
		this.castShadow = castShadow;
		
		this.color = color;
	}
	
	/**
	 * Illuminates.
	 *
	 * @param point the point
	 * @return true, if successful
	 */
	public abstract boolean illuminates(final Point3 point, final World world);
	
	/**
	 * Direction from.
	 *
	 * @param point the point
	 * @return the vector3
	 */
	public abstract Vector3 directionFrom(final Point3 point);

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Light [color=" + color + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Light other = (Light) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	
	


}
