package cgm.Geometry;

import java.util.ArrayList;

import cgm.Constants;
import cgm.Hit;
import cgm.Ray;
import cgm.Material.SingleColorMaterial;
import cgm.Math.Transform;

/**
 * The Class Node.
 */
public class Node extends Geometry {
	
	/** The transform. */
	public Transform transform;	
	
	/** The geometries. */
	public ArrayList<Geometry> geometries;
	


	/**
	 * Instantiates a new node.
	 *
	 * @param t the t
	 * @param g the g
	 */
	public Node(final Transform t, final ArrayList<Geometry> g) {
		super(new SingleColorMaterial(new MyColor(0,0,0)));
		this.transform = t;
		this.geometries = g;
	}
	
	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#hit(cgm.Ray)
	 */
	public Hit hit(final Ray r) {
		  Ray transformedRay = transform.mul( r );
	        double t = Double.MAX_VALUE;
	        Hit lowHit = null;

	        for( Geometry geometry : geometries ){

	            Hit hit = geometry.hit( transformedRay );
	            if( hit == null ) continue;

	            if( hit.t < t && hit.t > Constants.EPSILON){

	                t = hit.t;
	                lowHit = hit;
	            }
	        }

	        if( lowHit == null ) return null;
	        else return new Hit( lowHit.t, r, lowHit.geo, transform.mul( lowHit.normal ),lowHit.texCoord);
	}

	
	
	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#toString()
	 */
	@Override
	public String toString() {
		return "Node [transform=" + transform + ", geometries=" + geometries
				+ "]";
	}

	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((geometries == null) ? 0 : geometries.hashCode());
		result = prime * result
				+ ((transform == null) ? 0 : transform.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (geometries == null) {
			if (other.geometries != null)
				return false;
		} else if (!geometries.equals(other.geometries))
			return false;
		if (transform == null) {
			if (other.transform != null)
				return false;
		} else if (!transform.equals(other.transform))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#asCelShading()
	 */
	@Override
	public Geometry asCelShading() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#asSingelColor()
	 */
	@Override
	public Geometry asSingelColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
}

