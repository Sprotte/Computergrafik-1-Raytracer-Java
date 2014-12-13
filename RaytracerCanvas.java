package cgm;

import java.awt.Canvas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A custom canvas which the given image.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RaytracerCanvas extends Canvas {

	/** The image. */
	final public BufferedImage image;

	/**
	 * Instantiates a new custom canvas.
	 * 
	 * @param image
	 * @throws will
	 *             be thrown if the given argument was null
	 */
	public RaytracerCanvas(final BufferedImage image) {
		if (image == null) {
			throw new IllegalArgumentException("The Image cannot be null!");
		}

		this.image = image;

	}

	/*
	 * Paints the image on the canvas.
	 * 
	 * @throws will be thrown if the given argument was null
	 */
	@Override
	public void paint(final Graphics g) {
		if (g == null) {
			throw new IllegalArgumentException("The Graphics cannot be null!");
		}
		super.paint(g);
		g.drawImage(image, 0, 0, null);

	}
}
