package cgm.Texture;


/**
 * The Class TexCoord2. 
 * @author Sprotte 
 * @version 1.0
 */
public class TexCoord2 {

	/** The u. */
	public double u;
	
	/** The v. */
	public double v;

	/**
	 * Instantiates a new tex coord2.
	 *
	 * @param u the u
	 * @param v the v
	 */
	public TexCoord2(final double u, final double v) {

		this.u = u;
		this.v = v;

	}

	/**
	 * Adds the.
	 *
	 * @param t the t
	 * @return the tex coord2
	 */
	public TexCoord2 add(final TexCoord2 t) {
		return new TexCoord2(u + t.u, v + t.v);
	}

	/**
	 * Mul.
	 *
	 * @param n the n
	 * @return the tex coord2
	 */
	public TexCoord2 mul(final double n) {
		return new TexCoord2(u * n, v * n);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(u);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(v);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		TexCoord2 other = (TexCoord2) obj;
		if (Double.doubleToLongBits(u) != Double.doubleToLongBits(other.u))
			return false;
		if (Double.doubleToLongBits(v) != Double.doubleToLongBits(other.v))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TexCoord2 [u=" + u + ", v=" + v + "]";
	}

}
