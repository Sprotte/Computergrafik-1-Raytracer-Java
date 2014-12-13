package cgm.Texture;

import javax.imageio.ImageIO;

import cgm.Geometry.MyColor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Class ImageTexture. 
 * @author Sprotte 
 * @version 1.0
 */
public class ImageTexture implements Texture {

	/** the path. */
	public static final String basePath = "Textures/";
	
	/** the color for the texture. */
	public final MyColor color;

	/** the image for the texture. */
	public BufferedImage image;

	/**
	 * the texture.
	 *
	 * @param color the color
	 * @param path the path
	 */
	public ImageTexture(final MyColor color, final String path) {
		this.color = color;
		this.image = null;
		try {
			this.image = ImageIO.read(new File(basePath + path));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/* (non-Javadoc)
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
		ImageTexture other = (ImageTexture) obj;
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

	/* (non-Javadoc)
	 * @see cgm.Texture.Texture#getColor(double, double)
	 */
	@Override
	public MyColor getColor(final double u, final double v) {
		final double coord1 = ImageTexture.getRelativeCoord(u);
		final double coord2 = ImageTexture.getRelativeCoord(v);
		final double x = (this.image.getWidth() - 1) * coord1;
		final double y = (this.image.getHeight() - 1)
				- ((image.getHeight() - 1) * coord2);
		return ImageTexture.getColorOfPosition(this.image, (int) Math.round(x),
				(int) Math.round(y));
	}

	/**
	 * Gets the relative coord.
	 *
	 * @param in the in
	 * @return the relative coord
	 */
	public static double getRelativeCoord(final double in) {
		double out = in % 1.0;
		if (out < 0.0) {
			out = out + 1.0;
		}
		return out;
	}

	/**
	 * Gets the color of position.
	 *
	 * @param image the image
	 * @param x the x
	 * @param y the y
	 * @return the color of position
	 */
	public static MyColor getColorOfPosition(final BufferedImage image,
			final int x, final int y) {
		final java.awt.Color c = new java.awt.Color(image.getRGB(x, y));
		return new MyColor(c);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImageTexture [color=" + color + ", image=" + image + "]";
	}

}
