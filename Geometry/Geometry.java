package cgm.Geometry;

import cgm.Hit;
import cgm.Ray;
import cgm.Material.Material;

/**
 * The Class Geometry.
 * 
 * @author Sprotte 
 * @version 1.0
 */

public abstract class Geometry {
	
	/** The material. */
	public final Material material;

	/**
	 * Instantiates a new geometry.
	 *
	 * @param material the material
	 */
	public Geometry(final Material material) {
		if (material == null) {
			throw new IllegalArgumentException(
					"The Material of the Geometrie cannot be null!");
		}
		this.material = material;
	}

	// abstract hit method
	/**
	 * Hit.
	 *
	 * @param r the r
	 * @return the hit
	 */
	public abstract Hit hit(final Ray r);

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((material == null) ? 0 : material.hashCode());
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
		Geometry other = (Geometry) obj;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Geometry [material=" + material + "]";
	}
	public abstract Geometry asCelShading();
	public abstract Geometry asSingelColor();
	
	
}
