package cgm.Material;

import cgm.Hit;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Light.Light;
import cgm.Math.Point3;
import cgm.Texture.Texture;

/**
 * The Class NightDayMaterial. 
 * @author Sprotte
 * @version 1.0
 */
public class NightDayMaterial extends Material {

	/** The texture night. */
	public final Texture textureNight;
    
    /** The texture day. */
    public final Texture textureDay;
    
	/**
	 * Instantiates a new night day material.
	 *
	 * @param textureDay the texture day
	 * @param textureNight the texture night
	 */
	public NightDayMaterial(final Texture textureDay, final Texture textureNight) {
		this.textureDay= textureDay;
		this.textureNight = textureNight;
	}
	
	/* (non-Javadoc)
	 * @see cgm.Material.Material#colorFor(cgm.Hit, cgm.World, cgm.Tracer)
	 */
	public MyColor colorFor(final Hit hit, final World world, final Tracer tracer) {
        Point3 pointHit = hit.ray.at(hit.t);
        for (Light light : world.lightList) {
            if (light.illuminates(pointHit, world)) {
                return this.textureDay.getColor(hit.texCoord.u, hit.texCoord.v);
            } else {
                return this.textureNight.getColor(hit.texCoord.u, hit.texCoord.v);
            }
        }
        return this.textureDay.getColor(hit.texCoord.u, hit.texCoord.v);
    }

	/* (non-Javadoc)
	 * @see cgm.Material.Material#getCelShadingMaterial()
	 */
	@Override
	public CelShadingMaterial getCelShadingMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cgm.Material.Material#getSingelColorMaterial()
	 */
	@Override
	public SingleColorMaterial getSingelColorMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((textureDay == null) ? 0 : textureDay.hashCode());
		result = prime * result
				+ ((textureNight == null) ? 0 : textureNight.hashCode());
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
		NightDayMaterial other = (NightDayMaterial) obj;
		if (textureDay == null) {
			if (other.textureDay != null)
				return false;
		} else if (!textureDay.equals(other.textureDay))
			return false;
		if (textureNight == null) {
			if (other.textureNight != null)
				return false;
		} else if (!textureNight.equals(other.textureNight))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NightDayMaterial [textureNight=" + textureNight
				+ ", textureDay=" + textureDay + "]";
	}

}
