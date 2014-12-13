package cgm.Geometry;


import java.util.ArrayList;
import java.util.HashSet;

import cgm.Constants;
import cgm.Hit;
import cgm.Ray;
import cgm.Material.Material;
import cgm.Math.Point3;
import cgm.Math.Transform;


/**
 * The Class AxisAlignedBox.
 * 
 * @author Sprotte 
 * @version 1.0
 */
 
public class AxisAlignedBox extends Geometry {

	/** The lbf. */
	public final Point3 lbf = new Point3(-0.5, -0.5, -0.5);
	
	/** The run. */
	public final Point3 run= new Point3(0.5, 0.5,0.5);
	
	private final Node far;
	private final Node right;
	private final Node left;
	private final Node bottom;	
	private final Node top;
	private final Node front;
	
	/**
	 * Instantiates a new axis aligned box.
	 *
	 * @param material the material
	 * @param lbf the lbf
	 * @param run the run
	 * @throws will be thrown if the given argument was null
	 */
	public AxisAlignedBox(final Material material) {
		super(material);
		final ArrayList<Geometry> geos = new ArrayList<Geometry>();
		geos.add(new Plane(material));
		left   = new Node(new Transform().translate(this.lbf).rotateZ(Math.PI/2), geos);
		bottom = new Node(new Transform().translate(this.lbf).rotateX(Math.PI), geos);
		far    = new Node(new Transform().translate(this.lbf).rotateZ(Math.PI).rotateX(-Math.PI/2), geos);
		right  = new Node(new Transform().translate(this.run).rotateZ(-Math.PI/2), geos);
		top    = new Node(new Transform().translate(this.run), geos);
		front  = new Node(new Transform().translate(this.run).rotateZ(Math.PI).rotateX(Math.PI/2), geos);
		
		
		 
	}

	/* (non-Javadoc)
	 * @see cgm.Geometry.Geometry#hit(cgm.Ray)
	 * @throws will be thrown if the given argument was null
	 */
	@Override
	public Hit hit(final Ray r) {
		if (r == null) {
		    throw new IllegalArgumentException("The Ray cannot be null!");
		  }
		
		final Hit[] xHits = new Hit[] {left.hit(r), right.hit(r)};
		final Hit[] yHits = new Hit[] {top.hit(r), bottom.hit(r)};
		final Hit[] zHits = new Hit[] {front.hit(r), far.hit(r)};

		final HashSet<Hit> hits = new HashSet<Hit>();
		
		for (int i = 0; i < 2; i++) {
			if(xHits[i] != null) {
				final Point3 p = r.at(xHits[i].t);
				if(p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) hits.add(xHits[i]);
			}
		}
		
		for (int i = 0; i < 2; i++) {
			if(yHits[i] != null) {
				final Point3 p = r.at(yHits[i].t);
				if(p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) hits.add(yHits[i]);
			}	
		}
		
		for (int i = 0; i < 2; i++) {
			if(zHits[i] != null) {
				final Point3 p = r.at(zHits[i].t);
				if(p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) hits.add(zHits[i]);
			}
		}
		
		
		double t = Double.MAX_VALUE;
		Hit returnHit = null;
		
		for ( Hit hit : hits ){
			if( hit == null ) continue;
				if( hit.t < t && t > 0 && hit.t > Constants.EPSILON ){
				t = hit.t;
				returnHit = hit;
			}
		}
		return returnHit;
		
		
		/*
		if (r == null) {
			throw new IllegalArgumentException("The Ray cannot be null!");
		}
		
		//Calculated all visible planes
		final ArrayList<Plane> planes = new ArrayList<Plane>();
		if (r.origin.sub(run).dot(new Normal3(1, 0, 0)) >= 0) {
			planes.add(new Plane(this.material, run, new Normal3(1, 0, 0)));
		}
		if (r.origin.sub(run).dot(new Normal3(0, 1, 0)) >= 0) {
			planes.add(new Plane(this.material, run, new Normal3(0, 1, 0)));

		}
		if (r.origin.sub(run).dot(new Normal3(0, 0, 1)) >= 0) {
			planes.add(new Plane(this.material, run, new Normal3(0, 0, 1)));

		}
		
		if (r.origin.sub(lbf).dot(new Normal3(-1, 0, 0)) >= 0) {
			planes.add(new Plane(this.material, lbf, new Normal3(-1, 0, 0)));

		}
		if (r.origin.sub(lbf).dot(new Normal3(0, -1, 0)) >= 0) {
			planes.add(new Plane(this.material, lbf, new Normal3(0, -1, 0)));

		}
		if (r.origin.sub(lbf).dot(new Normal3(0, 0, -1)) >= 0) {
			planes.add(new Plane(this.material, lbf, new Normal3(0, 0, -1)));

		}
		//Checks all visible planes on hits
		final ArrayList<Hit> hits = new ArrayList<Hit>();
		for (Plane pl : planes) {
			hits.add(pl.hit(r));
		}
		
		

		double tmax = 0.0001;
		Hit hit = null;
		// If there are hits the hit with the smallest t is the right hit
		if (hits.isEmpty() == false) {
			
			
			for (final Hit h : hits) {
	            if (hit == null || h.t < hit.t) {
	                hit = h;
	            }
	        }

			if (hit != null) {
				final Point3 point = r.at(hit.t+0.015);
				Plane plane = (Plane) hit.geo;
				if (plane.n.equals(new Normal3(1, 0, 0))) {
					if (run.y >= point.y && run.z >= point.z
							&& lbf.y <= point.y && lbf.z <= point.z) {
						return new Hit(hit.t, r, this,hit.normal);
					}
				}
				if (plane.n.equals(new Normal3(-1, 0, 0))) {
					if (run.y >= point.y && run.z >= point.z
							&& lbf.y <= point.y && lbf.z <= point.z) {
						return new Hit(hit.t, r, this,hit.normal);
					}
				}

				if (plane.n.equals(new Normal3(0, 0, 1))) {
					if (run.x >= point.x && run.y >= point.y
							&& lbf.x <= point.x && lbf.y <= point.y) {
						return new Hit(hit.t, r, this,hit.normal);
					}
				}
				if (plane.n.equals(new Normal3(0, 0, -1))) {
					if (run.x >= point.x && run.y >= point.y
							&& lbf.x <= point.x && lbf.y <= point.y) {
						return new Hit(hit.t, r, this,hit.normal);
					}
				}

				if (plane.n.equals(new Normal3(0, 1, 0))) {
					if (run.x >= point.x && run.z >= point.z
							&& lbf.x <= point.x && lbf.z <= point.z) {
						return new Hit(hit.t, r, this,hit.normal);
					}
				}
				if (plane.n.equals(new Normal3(0, -1, 0))) {
					if (run.x >= point.x && run.z >= point.z
							&& lbf.x <= point.x && lbf.z <= point.z) {
						return new Hit(hit.t, r, this,hit.normal);
					}
				}

			}

		}
		return null;
		
		*/

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
		result = prime * result + ((lbf == null) ? 0 : lbf.hashCode());
		result = prime * result + ((run == null) ? 0 : run.hashCode());
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
		AxisAlignedBox other = (AxisAlignedBox) obj;
		if (lbf == null) {
			if (other.lbf != null)
				return false;
		} else if (!lbf.equals(other.lbf))
			return false;
		if (run == null) {
			if (other.run != null)
				return false;
		} else if (!run.equals(other.run))
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
		return "AxisAlignedBox [lbf=" + lbf + ", run=" + run + "]";
	}
	public AxisAlignedBox asCelShading(){
		return new AxisAlignedBox(this.material.getCelShadingMaterial());
	}
	public AxisAlignedBox asSingelColor(){
		return new AxisAlignedBox(this.material.getSingelColorMaterial());
	}

}
