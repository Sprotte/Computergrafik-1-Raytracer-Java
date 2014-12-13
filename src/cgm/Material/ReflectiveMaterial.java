package cgm.Material;

import cgm.Hit;
import cgm.Ray;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Light.Light;
import cgm.Math.Point3;
import cgm.Math.Vector3;


/**
 * describes a reflective material.
 */
public class ReflectiveMaterial extends Material {

    /** the diffusing color. */
    public final MyColor diffuse;

    /** the specular color. */
    public final MyColor specular;

    /** the exponent. */
    public final int exponent;

    /** the reflection color. */
    public final MyColor reflection;

    /**
     * creates an instance of a phong material.
     *
     * @param diffuse the diffusing color
     * @param specular the specular color
     * @param exponent the exponent
     * @param reflection the reflection of the material
     */
    public ReflectiveMaterial(final MyColor diffuse, final MyColor specular, final int exponent, final MyColor reflection) {
    	if (diffuse == null) {
			throw new IllegalArgumentException("The Diffuse cannot be null!");
		}
		if (specular == null) {
			throw new IllegalArgumentException("The Specular cannot be null!");
		}	
		if (reflection == null) {
			throw new IllegalArgumentException("The reflection cannot be null!");
		}
	
        this.diffuse = diffuse;
        this.specular = specular;
        this.reflection = reflection;
        this.exponent = exponent;
    }

    /**
     * this method returns the color for one Hit-Object.
     *
     * @param hit the hit with an object
     * @param world the world
     * @param tracer function for raytracing
     * @return color for hit
     */
    public MyColor colorFor(final Hit hit, final World world, final Tracer tracer) {
    	if (hit == null) {
			throw new IllegalArgumentException("The Hit cannot be null!");
		}
		if (world == null) {
			throw new IllegalArgumentException("The World cannot be null!");
		}
		if (tracer == null) {
			throw new IllegalArgumentException("The Tracer cannot be null!");
		}
    	
    	MyColor color = world.ambientLight.mul(this.diffuse);
        final Point3 point = hit.ray.at(hit.t);
        final double cosinusPhi = hit.normal.dot(hit.ray.direction.mul(-1.0)) * 2;
        final Vector3 v = hit.ray.direction.mul(-1).normalized();
       
        for (final Light currentLight : world.lightList) {
            
            if (currentLight.illuminates(point, world)) {
                final Vector3 lightlVector = currentLight.directionFrom(point);
               
                final Vector3 reflectedVector = lightlVector.reflectedOn(hit.normal);
               
                final double maxNL = Math.max(0.0, hit.normal.dot(lightlVector));
                final double maxER = Math.pow(Math.max(0.0, reflectedVector.dot(v)), this.exponent);
                color = color.add(currentLight.color.mul(this.diffuse).mul(maxNL)).add(currentLight.color.mul(this.specular).mul(maxER));
            }
        }
        MyColor reflectedColor = tracer.colorFor(new Ray(point, hit.ray.direction.add(hit.normal.mul(cosinusPhi))));
        return color.add(reflection.mul((reflectedColor)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectiveMaterial that = (ReflectiveMaterial) o;

        if (exponent != that.exponent) return false;
        if (diffuse != null ? !diffuse.equals(that.diffuse) : that.diffuse != null) return false;
        if (reflection != null ? !reflection.equals(that.reflection) : that.reflection != null) return false;
        if (specular != null ? !specular.equals(that.specular) : that.specular != null) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = diffuse != null ? diffuse.hashCode() : 0;
        result = 31 * result + (specular != null ? specular.hashCode() : 0);
        result = 31 * result + exponent;
        result = 31 * result + (reflection != null ? reflection.hashCode() : 0);
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReflectiveMaterial{" +
                "diffuse=" + diffuse +
                ", specular=" + specular +
                ", exponent=" + exponent +
                ", reflection=" + reflection +
                '}';
    }
    
    /* (non-Javadoc)
     * @see cgm.Material.Material#getCelShadingMaterial()
     */
    @Override
	public CelShadingMaterial getCelShadingMaterial() {
		
		return new CelShadingMaterial(diffuse);
	}

	/* (non-Javadoc)
	 * @see cgm.Material.Material#getSingelColorMaterial()
	 */
	@Override
	public SingleColorMaterial getSingelColorMaterial() {
		// TODO Auto-generated method stub
		return new SingleColorMaterial(diffuse);
	}
}