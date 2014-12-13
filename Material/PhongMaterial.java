package cgm.Material;

import cgm.Hit;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Light.Light;
import cgm.Math.Normal3;
import cgm.Math.Point3;
import cgm.Math.Vector3;


/**
 * The Class PhongMaterial.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class PhongMaterial extends Material {

	/**
	 * Instantiates a new phong material.
	 *
	 * @param diffuse the diffuse
	 * @param specular the specular
	 * @param exponent the exponent
	 */
	public PhongMaterial(final MyColor diffuse, final MyColor specular,
			final int exponent) {
		if (diffuse == null) {
			throw new IllegalArgumentException("The Diffuse cannot be null!");
		}
		if (specular == null) {
			throw new IllegalArgumentException("The Specular cannot be null!");
		}
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	/** The diffuse. */
	public final MyColor diffuse;
	
	/** The specular. */
	public final MyColor specular;
	
	/** The exponent. */
	public final int exponent;

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

		final Normal3 normal = hit.normal;

		final Point3 point = hit.ray.at(hit.t);

		MyColor color = world.ambientLight.mul(diffuse);

		final Vector3 e = (hit.ray.direction.mul(-1)).normalized();

		MyColor lightColor = new MyColor(0, 0, 0);

		for (Light light : world.lightList) {

			if (light.illuminates(hit.ray.at(hit.t),world)) {

				final Vector3 lightVector = light.directionFrom(point)
						.normalized();
				final Vector3 reflectedVector = lightVector.reflectedOn(normal);

				final double maxNL = Math.max(0.0, lightVector.dot(normal));
				final double maxER = Math.pow(
						Math.max(0.0, reflectedVector.dot(e)), exponent);

				lightColor = lightColor
						.add(light.color.mul(diffuse).mul(maxNL)).add(
								light.color.mul(specular).mul(maxER));

				color = color.add(lightColor);

			}

		}

		return color;
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
		PhongMaterial other = (PhongMaterial) obj;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PhongMaterial [diffuse=" + diffuse + ", specular=" + specular
				+ ", exponent=" + exponent + "]";
	}
	
	@Override
	public CelShadingMaterial getCelShadingMaterial() {
		
		return new CelShadingMaterial(diffuse);
	}
	@Override
	public SingleColorMaterial getSingelColorMaterial() {
		
		return new SingleColorMaterial(diffuse);
	}

}
