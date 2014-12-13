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
public class World {
	
	/** The background color. */
	public final MyColor backgroundColor;
	
	/** The ambiLight color. */
	public final MyColor ambientLight;

	/** The geo list. */
	public final ArrayList<Geometry> geoList;
	
	/** The light list. */
	public final ArrayList<Light> lightList;
	
	/** The light list. */
	public final double refractionIndex;

	/**
	 * Instantiates a new world.
	 *
	 * @param g the g
	 */
	public World(final ArrayList<Geometry> g,final ArrayList<Light> lights,final MyColor ambiLight,final double refractionIndex) {
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
		
		
		/*
		Set<Hit> hitted = new HashSet<Hit>();
        Hit hit = null;
        for (Geometry geo : geoList){
            hit = geo.hit(r);
            if(hit != null){
                hitted.add(hit);
            }
        }
        double t = Double.MAX_VALUE;
        for(Hit h : hitted){
            if(h.t < t){
                t = h.t;
                if (t < Constants.EPSILON){
                    t = Constants.EPSILON;
                }
                hit = h;
            }
        }
       if(t != Double.MAX_VALUE)
            return hit;

       return null;
    }
		*/
		final ArrayList<Hit> hits = new ArrayList<Hit>();
		for (Geometry geo : geoList) {
			if (geo.hit(r) != null) {
				hits.add(geo.hit(r));
			}
		}
		if (hits.isEmpty()) {
			return null;
		} else {
			double tmin = Double.MAX_VALUE;
			Hit returnHit = null;
			for (Hit hit : hits) {
				if (hit.t < tmin) {
					tmin = hit.t;
					if (tmin < Constants.EPSILON){
	                    tmin = Constants.EPSILON;
	                }
					returnHit = hit;
				}
			}
			return returnHit;
		}
		
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
		World other = (World) obj;
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
