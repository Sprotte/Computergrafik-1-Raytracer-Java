package cgm.Material;

import cgm.Hit;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Light.Light;
import cgm.Math.Vector3;


/**
 * The Class LambertMaterial.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class LambertMaterial extends Material {

	/** The color. */
	public final MyColor color;

	/**
	 * Instantiates a new lambert material.
	 *
	 * @param color the color
	 */
	public LambertMaterial(final MyColor color) {
		if (color == null) {
			throw new IllegalArgumentException("The Diffuse cannot be null!");
		}		
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see cgm.Material.Material#colorFor(cgm.Hit, cgm.World)
	 */
	@Override
	public MyColor colorFor(final Hit hit, final World world,final Tracer tracer) {
		if (hit == null) {
			throw new IllegalArgumentException("The Hit cannot be null!");
		}
		if (world == null) {
			throw new IllegalArgumentException("The World cannot be null!");
		}
		if (tracer == null) {
			throw new IllegalArgumentException("The Tracer cannot be null!");
		}
		MyColor returnColor = color.mul(world.ambientLight);

		for (Light light : world.lightList) {
			MyColor lightColor = light.color;
			if (light.illuminates(hit.ray.at(hit.t),world)) {
				final Vector3 lightVector = light.directionFrom(
						hit.ray.at(hit.t)).normalized();
				final double max = Math.max(0.0, lightVector.dot(hit.normal));

				returnColor = returnColor.add(color.mul(lightColor).mul(max));
			}
		}
		return returnColor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LambertMaterial [color=" + color + "]";
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
		LambertMaterial other = (LambertMaterial) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
	@Override
	public CelShadingMaterial getCelShadingMaterial() {
		
		return new CelShadingMaterial(color);
	}
	@Override
	public SingleColorMaterial getSingelColorMaterial() {
		
		return new SingleColorMaterial(color);
	}

}
