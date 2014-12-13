package cgm;

import java.util.ArrayList;

import cgm.Geometry.MyColor;
import cgm.Geometry.Geometry;
import cgm.Light.Light;

/**
 * The Class World hold the the geometry in arraylist.
 * @author Sprotte
 * 
 * @version 1.0
 */
public class CopyOfWorld {
	
	/** The background color. */
	public final MyColor backgroundColor;
	
	/** The ambiLight color. */
	public final MyColor ambientLight;

	/** The geo list. */
	public final ArrayList<Geometry> geoList;
	
	/** The light list. */
	public final ArrayList<Light> lightList;
	
	/** The light list. */
	public final int refractionIndex;

	/**
	 * Instantiates a new world.
	 *
	 * @param g the g
	 */
	public CopyOfWorld(final ArrayList<Geometry> g,final ArrayList<Light> lights,final MyColor ambiLight,final int refractionIndex) {
		backgroundColor = new MyColor(0, 0, 0);
		geoList = g;
		lightList = lights;
		this.ambientLight = ambiLight;
		this.refractionIndex = refractionIndex;
	}

	/**
	 * Iterates through all the geometries and gives the hit back
	 *
	 * @param r the ray
	 * @return the hit
	 * @throws will be thrown if the given argument was null
	 */
	public Hit hit(final Ray r) {
		if (r == null) {
			throw new IllegalArgumentException("The Ray cannot be null!");
		}
		ArrayList<Hit> hitted = new ArrayList<Hit>();
        Hit hit = null;
        double minimalHit = Double.MAX_VALUE;
        for (Geometry geometry : geoList) {
            hit = geometry.hit(r);
            if (hit != null)
                hitted.add(hit);
        }

        for (Hit h : hitted) {
            if (h.t < minimalHit) {
                minimalHit = h.t;

                if (minimalHit < Constants.EPSILON) {
                    minimalHit = Constants.EPSILON;
                }
                hit = h;
            }
        }
        return hit;

	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((backgroundColor == null) ? 0 : backgroundColor.hashCode());
		result = prime * result + ((geoList == null) ? 0 : geoList.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		CopyOfWorld other = (CopyOfWorld) obj;
		if (backgroundColor == null) {
			if (other.backgroundColor != null)
				return false;
		} else if (!backgroundColor.equals(other.backgroundColor))
			return false;
		if (geoList == null) {
			if (other.geoList != null)
				return false;
		} else if (!geoList.equals(other.geoList))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "World [backgroundColor=" + backgroundColor + ", geoList="
				+ geoList + "]";
	}

}
