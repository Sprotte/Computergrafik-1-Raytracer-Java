package cgm.Material;

import cgm.Hit;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Light.Light;
import cgm.Math.Point3;
import cgm.Math.Vector3;

/**
 * The Class OrenNayarMaterial. 
 * @author Sprotte and thanks to Micha
 * @version 1.0
 */
public class OrenNayarMaterial extends Material {

	
	/** The diffuse. */
	public final MyColor diffuse;
	
	/** The specular. */
	public final MyColor specular;

	
	/** The exponent. */
	public final int exponent;

	
	/**
	 * Instantiates a new oren nayar material.
	 *
	 * @param diffuse the diffuse
	 * @param specular the specular
	 * @param exponent the exponent
	 */
	public OrenNayarMaterial(final MyColor diffuse, final MyColor specular,
			final int exponent) {
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	
	/* (non-Javadoc)
	 * @see cgm.Material.Material#colorFor(cgm.Hit, cgm.World, cgm.Tracer)
	 */
	@Override
	public MyColor colorFor(final Hit hit, final World world, final Tracer tracer) {

		final MyColor color = this.diffuse.mul(world.ambientLight);

		final Point3 point = hit.ray.at(hit.t);
		final Vector3 e = hit.ray.direction.mul(-1);

		MyColor returnColor = world.backgroundColor;
		final double roughness = Math.pow(this.exponent, 2);
		final double a = 1.0f - 0.5f * (roughness / (roughness + 0.57f));
		final double b = 0.45f * (roughness / (roughness + 0.09f));

		for (Light light : world.lightList) {
			if (light.illuminates(point, world)) {
				final Vector3 lightVector = light.directionFrom(point).normalized();
				final double alpha = Math.max(Math.acos(e.dot(hit.normal)),
						Math.acos(lightVector.dot(hit.normal)));
				final double beta = Math.min(Math.acos(e.dot(hit.normal)),
						Math.acos(e.dot(hit.normal)));
				returnColor = this.diffuse.mul(hit.normal.dot(lightVector)).mul(a + b * Math.max(0, hit.normal.dot(lightVector))* Math.sin(alpha) * Math.tan(beta));
			}
		}

		return color.add(returnColor);

	}

	
	/* (non-Javadoc)
	 * @see cgm.Material.Material#getCelShadingMaterial()
	 */
	@Override
	public CelShadingMaterial getCelShadingMaterial() {
		return new CelShadingMaterial(this.diffuse);
	}

	
	/* (non-Javadoc)
	 * @see cgm.Material.Material#getSingelColorMaterial()
	 */
	@Override
	public SingleColorMaterial getSingelColorMaterial() {
		return new SingleColorMaterial(this.diffuse);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrenNayarMaterial [diffuse=" + diffuse + ", specular="
				+ specular + ", exponent=" + exponent + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diffuse == null) ? 0 : diffuse.hashCode());
		result = prime * result + exponent;
		result = prime * result
				+ ((specular == null) ? 0 : specular.hashCode());
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
		OrenNayarMaterial other = (OrenNayarMaterial) obj;
		if (diffuse == null) {
			if (other.diffuse != null)
				return false;
		} else if (!diffuse.equals(other.diffuse))
			return false;
		if (exponent != other.exponent)
			return false;
		if (specular == null) {
			if (other.specular != null)
				return false;
		} else if (!specular.equals(other.specular))
			return false;
		return true;
	}

	

}
