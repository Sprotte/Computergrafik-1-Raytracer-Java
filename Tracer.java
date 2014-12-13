package cgm;


import cgm.Geometry.MyColor;

public class Tracer {
	
	public int maxDepth;

	

	public final World world;

	public Tracer(World world, int maxDepth) {
		super();
		this.world = world;
		this.maxDepth = maxDepth;
	}
	
	/**
	 * Gets the color from the material of the taken object 
	 *
	 * @param ray the ray
	 * @return the color
	 * @throws will be thrown if the given argument was null
	 */
	public MyColor colorFor(final Ray ray) {
		
		if (ray == null) {
			throw new IllegalArgumentException("The ray cannot be null!");
		}
		if(maxDepth<=0){
			return world.backgroundColor;
		}else{
			Hit hit = world.hit(ray);
			if(hit != null ){
			return hit.geo.material.colorFor(hit, world, new Tracer(world, maxDepth-1));
			}else{
				return world.backgroundColor;
			}
		}

	}

}
