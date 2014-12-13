package cgm.Material;

import cgm.Hit;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;


/**
 * The Class Material.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public abstract class Material {

	/**
	 * Color for.
	 *
	 * @param hit the hit
	 * @param world the world
	 * @return the my color
	 */
	public abstract MyColor colorFor(final Hit hit,final World world,final Tracer tracer);
	
	
	public abstract CelShadingMaterial getCelShadingMaterial();
	
	public abstract SingleColorMaterial getSingelColorMaterial();

}
