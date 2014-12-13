package cgm.Texture;

import javax.imageio.ImageIO;

import cgm.Geometry.MyColor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Class InterpolatedImageTexture. 
 * @author Sprotte 
 * @version 1.0
 */
public class InterpolatedImageTexture implements Texture {

	/** The Constant baseURL. */
	public static final String baseURL = "Textures/";

	/** The color. */
	public final MyColor color;

	/** The image. */
	public BufferedImage image;

	/**
	 * Instantiates a new interpolated image texture.
	 * 
	 * @param color
	 *            the color
	 * @param path
	 *            the path to image
	 */
	public InterpolatedImageTexture(final MyColor color, final String path) {
		this.color = color;
		this.image = null;
		try {
			this.image = ImageIO.read(new File(baseURL + path));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cgm.Texture.Texture#getColor(double, double)
	 */
	@Override
	public MyColor getColor(final double u, final double v) {

		final double uu = ImageTexture.getRelativeCoord(u);
		final double vv = ImageTexture.getRelativeCoord(v);

		final double x = (this.image.getWidth() - 1) * uu;
		final double y = (this.image.getHeight() - 1)
				- ((image.getHeight() - 1) * vv);

		final double xInter = x - Math.floor(x);
		final double yInter = y - Math.floor(y);

		final MyColor colorA = ImageTexture.getColorOfPosition(this.image,
				(int) Math.floor(x), (int) Math.floor(y));
		final MyColor colorB = ImageTexture.getColorOfPosition(this.image,
				(int) Math.ceil(x), (int) Math.floor(y));
		final MyColor colorC = ImageTexture.getColorOfPosition(this.image,
				(int) Math.floor(x), (int) Math.ceil(y));
		final MyColor colorD = ImageTexture.getColorOfPosition(this.image,
				(int) Math.ceil(x), (int) Math.ceil(y));

		final MyColor colorAB = colorA.mul(1.0 - xInter)
				.add(colorB.mul(xInter));
		final MyColor colorCD = colorC.mul(1.0 - xInter)
				.add(colorD.mul(xInter));

		return colorAB.mul(1.0 - yInter).add(colorCD.mul(yInter));
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
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
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
		InterpolatedImageTexture other = (InterpolatedImageTexture) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
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
		return "InterpolatedImageTexture [color=" + color + ", image=" + image
				+ "]";
	}

}
