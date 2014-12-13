package cgm.Texture;

import cgm.Geometry.MyColor;

/**
 * The Interface Texture. 
 * @author Sprotte 
 * @version 1.0
 */
public interface Texture {	
	
	/**
	 * Gets the color.
	 *
	 * @param u the u
	 * @param v the v
	 * @return the color
	 */
	public MyColor getColor(final double u, final double v);
}