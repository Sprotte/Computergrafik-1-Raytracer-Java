package cgm.Texture;

import cgm.Geometry.MyColor;

/**
 * The Class SingleColorTexture. 
 * @author Sprotte 
 * @version 1.0
 */
public class SingleColorTexture implements Texture {

	/** The color. */
	public final MyColor color;

	/**
	 * Instantiates a new single color texture.
	 *
	 * @param c the c
	 */
	public SingleColorTexture(final MyColor c) {
		this.color = c;
	}

	/* (non-Javadoc)
	 * @see cgm.Texture.Texture#getColor(double, double)
	 */
	@Override
	public MyColor getColor(double u, double v) {

		return this.color;
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
		SingleColorTexture other = (SingleColorTexture) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SingleColorTexture [color=" + color + "]";
	}

}