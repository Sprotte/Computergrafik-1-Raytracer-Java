package cgm.Geometry;

import cgm.Constants;
import cgm.Hit;
import cgm.Ray;
import cgm.Material.Material;
import cgm.Math.Mat3x3;
import cgm.Math.Normal3;
import cgm.Math.Point3;
import cgm.Math.Vector3;
import cgm.Texture.TexCoord2;

/**
 * The Class Triangle.
 * 
 * @author Sprotte 
 * @version 1.0
 */

public class Triangle extends Geometry {

	/** The a Corner */
	public final Point3 a;

	/** The b Corner */
	public final Point3 b;

	/** The c Corner */
	public final Point3 c;
	
	public final Normal3 normal;
	
	public final Normal3 an;
    public final Normal3 bn;
    public final Normal3 cn;

    
	public final TexCoord2 texCoordA;
	
	public final TexCoord2 texCoordB;
	
	public final TexCoord2 texCoordC;
	
	public Triangle(final Material material, final Point3 a, final Point3 b,
			final Point3 c, Normal3 an, Normal3 bn, Normal3 cn, final TexCoord2 texCoordA, final TexCoord2 texCoordB, final TexCoord2 texCoordC) {
		super(material);
		if (a == null) {
			throw new IllegalArgumentException(
					"The Point a of the Triangle cannot be null!");
		}
		if (b == null) {
			throw new IllegalArgumentException(
					"The Point b of the Triangle cannot be null!");
		}
		if (c == null) {
			throw new IllegalArgumentException(
					"The Point c of the Triangle cannot be null!");
		}
		this.a = a;
		this.b = b;
		this.c = c;
		this.an = an;
        this.bn = bn;
        this.cn = cn;
		normal = b.sub(a).x( c.sub(a)).normalized().asNormal();
		this.texCoordA = texCoordA;
		this.texCoordB = texCoordB;
		this.texCoordC = texCoordC;
	}

	@Override	
	public Hit hit(final Ray r) {
		if (r == null) {
		    throw new IllegalArgumentException("The Ray cannot be null!");
		  }

		final Mat3x3 matA = new Mat3x3( a.x - b.x, a.x - c.x, r.direction.x,
                a.y - b.y, a.y - c.y, r.direction.y,
                a.z - b.z, a.z - c.z, r.direction.z);


		final Vector3 vec = a.sub(r.origin);
		final double beta = matA.changeCol1(vec).determinant / matA.determinant;
		final double gamma = matA.changeCol2(vec).determinant / matA.determinant;
		final double t = matA.changeCol3(vec).determinant / matA.determinant;

		if (beta < 0.0 || gamma < 0.0 || (beta + gamma) > 1.0 || t <Constants.EPSILON) {
			return null;
		} else {
			final double alpha = 1 - beta - gamma;
            final Normal3 n = an.mul(alpha).add(bn.mul(beta)).add(cn.mul(gamma));
            final TexCoord2 texCoord = texCoordA.mul(alpha).add(texCoordB).mul(beta).add(texCoordC).mul(gamma);
			return new Hit(t, r, this, n,texCoord);
		} 
		
		
		
		/*
		final double aa = a.x - b.x;
		final double bb = a.x - c.x;
		final double cc = r.direction.x;
		final double dd = a.x - r.origin.x;

		final double ee = a.y - b.y;
		final double ff = a.y - c.y;
		final double gg = r.direction.y;
		final double hh = a.y - r.origin.y;

		final double ii = a.z - b.z;
		final double jj = a.z - c.z;
		final double kk = r.direction.z;
		final double ll = a.z - r.origin.z;

		final double mm = ff * kk - gg * jj;
		final double nn = gg * ll - hh * kk;
		final double oo = hh * jj - ff * ll;
		final double pp = gg * ii - ee * kk;
		final double qq = ee * jj - ff * ii;
		final double rr = ee * ll - hh * ii;

		final double tt = hh * kk - gg * ll;
		final double uu = ff * ll - hh * jj;
		final double vv = hh * ii - ee * ll;

		final double ss = aa * mm + bb * pp + cc * qq;

		final double beta = (dd * mm + bb * nn + cc * oo) / ss;

		if (beta < Double.MIN_VALUE) {
			return null;
		}
		double gamma = (aa * tt + dd * pp + cc * rr) / ss;
		if (gamma < Double.MIN_VALUE) {
			return null;
		}
		if (beta + gamma > 1) {
			return null;
		}
		final double t = (aa * uu + bb * vv + dd * qq) / ss;

		if (t < Double.MIN_VALUE) {
			return null;
		} else {
			
			return new Hit(t, r, this,normal);

		}
		
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
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triangle other = (Triangle) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
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
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
	
	public Triangle asCelShading(){
		return new Triangle(material.getCelShadingMaterial(),this.a,this.b,this.c,this.an,this.bn,this.cn,this.texCoordA,this.texCoordB,this.texCoordC);
	}
	public Triangle asSingelColor(){
		return new Triangle(material.getSingelColorMaterial(),this.a,this.b,this.c,this.an,this.bn,this.cn,this.texCoordA,this.texCoordB,this.texCoordC);
	}
}
