package cgm.Geometry;

/**
 * The Class Color represents a Color
 * 
 * @author Sprotte 
 * @version 1.0
 */
public class MyColor {

	/** The r. */
	public final double r;
	
	/** The g. */
	public final double g;
	
	/** The b. */
	public final double b;

	/**
	 * Instantiates a new color.
	 *
	 * @param r the r
	 * @param g the g
	 * @param b the b
	 */
	public MyColor( double r, double g, double b) {
		
		if (r > 1) r = 1.0;
		if (r < 0) r = 0.0;
		if (g > 1) g = 1.0;
		if (g < 0) g = 0.0;
		if (b > 1) b = 1.0;
		if (b < 0) b = 0.0;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public MyColor (java.awt.Color color) {
        this.r = color.getRed() / 255.0;
        this.g = color.getGreen() / 255.0;
        this.b = color.getBlue() / 255.0;
    }

	/**
	 * Adds the.
	 *
	 * @param color the color
	 * @return the color
	 * @throws will be thrown if the given argument was null
	 */
	public MyColor add(final MyColor color) {
		if (color == null) {
			throw new IllegalArgumentException("The Color cannot be null!");
		}

		
		return new MyColor(r + color.r, g + color.g, b + color.b);
	}

	/**
	 * Sub.
	 *
	 * @param color the color
	 * @return the color
	 * @throws will be thrown if the given argument was null
	 */
	public MyColor sub(final MyColor color) {
		if (color == null) {
			throw new IllegalArgumentException("The Color cannot be null!");
		}

		 
		return  new MyColor(r - color.r, g - color.g, b - color.b);
	}

	/**
	 * Mul.
	 *
	 * @param color the color
	 * @return the color
	 * @throws will be thrown if the given argument was null
	 */
	public MyColor mul(final MyColor color) {
		if (color == null) {
			throw new IllegalArgumentException("The Color cannot be null!");
		}

		
		return new MyColor(r * color.r, g * color.g, b * color.b);
	}

	/**
	 * Mul.
	 *
	 * @param value the value
	 * @return the color
	 */
	public MyColor mul(final double value) {

		
		return new MyColor(r * value, g * value, b * value);
	}
	
	

	/**
	 * Gets the int from color.
	 *
	 * @return the int rgb from the color
	 */
	public int getIntFromColor() {		
		
		int rot = new Double(r * 255).intValue();
		int grün = new Double(g * 255).intValue();
		int blau = new Double(b * 255).intValue();

		rot = (rot << 16) & 0x00FF0000;
		grün = (grün << 8) & 0x0000FF00;
		blau = blau & 0x000000FF;

		return 0xFF000000 | rot | grün | blau;
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
		long temp;
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MyColor other = (MyColor) obj;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
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
		return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

}
