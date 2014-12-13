package cgm.Material;

import cgm.Hit;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Light.Light;
import cgm.Math.Normal3;
import cgm.Math.Point3;
import cgm.Math.Vector3;


// TODO: Auto-generated Javadoc
/**
 * The Class SingleColorMaterial.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class CelShadingMaterial extends Material {

	/** The color. */
	public final MyColor color;
	
	/**
	 * Instantiates a new single color material.
	 *
	 * @param color the color
	 */
	public CelShadingMaterial(final MyColor color) {
		if (color == null) {
			throw new IllegalArgumentException("The Diffuse cannot be null!");
		}
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see cgm.Material.Material#colorFor(cgm.Hit, cgm.World)
	 */
	@Override
	public MyColor colorFor(final Hit hit,final World world,final Tracer tracer) {
		if (hit == null) {
			throw new IllegalArgumentException("The Hit cannot be null!");
		}
		if (world == null) {
			throw new IllegalArgumentException("The World cannot be null!");
		}
		if (tracer == null) {
			throw new IllegalArgumentException("The Tracer cannot be null!");
		}
		
		final Normal3 normale = hit.normal;
		final Point3 schnittpunkt = hit.ray.at(hit.t);
		MyColor returnColor = color.mul(world.ambientLight);
		for (Light li : world.lightList) {
		if (li.illuminates(schnittpunkt, world)) {
		final Vector3 l = li.directionFrom(schnittpunkt).normalized();
		double c = normale.dot(l);
		if(c < 0) c = 0.2;
		if(c > 0 && c <= 0.2) c = 0.2;
		if(c > 0.2 && c <= 0.4) c = 0.4;
		if(c > 0.4 && c <= 0.5) c = 0.5;
		if(c > 0.5 && c <= 0.7) c = 0.7;
		if(c > 0.7 && c <= 0.9) c = 0.9;
		if(c > 0.9 && c <= 1.1) c = 1.1;
		returnColor = returnColor.add(color.mul(li.color).mul(c));
		}
		}
		
		return returnColor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SingleColorMaterial [color=" + color + "]";
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
		CelShadingMaterial other = (CelShadingMaterial) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	@Override
	public CelShadingMaterial getCelShadingMaterial() {
		
		return this;
	}
	@Override
	public SingleColorMaterial getSingelColorMaterial() {
		
		return new SingleColorMaterial(color);
	}
	

	

}
