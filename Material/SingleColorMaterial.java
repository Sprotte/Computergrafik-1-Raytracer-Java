package cgm.Material;

import cgm.Hit;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Texture.Texture;

/**
 * The Class SingleColorMaterial.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class SingleColorMaterial extends Material {

	/** The color. */
	public final MyColor color;
	
	/**
	 * The texture of the material
	 */
	private final Texture tex;
	
	/**
	 * Instantiates a new single color material.
	 *
	 * @param color the color
	 */
	public SingleColorMaterial(final MyColor color) {
		if (color == null) {
			throw new IllegalArgumentException("The Diffuse cannot be null!");
		}
		this.color = color;
		this.tex = null;
	}
	
	public SingleColorMaterial(final Texture texture) {
		this.tex = texture;
		this.color=null;
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
		
		if(tex != null) {
			return this.tex.getColor(hit.texCoord.u, hit.texCoord.v);
		}
			return color;
	
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
		SingleColorMaterial other = (SingleColorMaterial) obj;
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
		
		return this;
	}
	

	

}
