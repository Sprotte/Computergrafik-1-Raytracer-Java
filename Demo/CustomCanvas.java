package cgm.Aufgaben;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.JFrame;


/**
 * A custom canvas which paints a black image with an red diagonal line.
 * 
 * @author Sprotte Josef Gabi
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomCanvas extends Canvas {

	/** The image. */
	public BufferedImage image;
	
	/** The frame. */
	private final JFrame frame;

	/**
	 * Instantiates a new custom canvas. 
	 *
	 * @param frame for the canvas size
	 */
	public CustomCanvas(final JFrame frame) {

		this.frame = frame;

	}

	/* 
	 * Paints the image on the canvas.
	 */
	@Override
	public void paint(final Graphics g) {
		super.paint(g);
		// creates an image with the size of the frame 
		image = new BufferedImage(frame.getWidth(), frame.getHeight(),BufferedImage.TYPE_INT_RGB);
		// get the raster from the created image
		final WritableRaster raster = image.getRaster();
		// get the color model from the created image
		final ColorModel model = image.getColorModel();		
		// get the rgbRed int
		final int rgbRed = Color.RED.getRGB();
		// too loops to draw the red line 
		for (int i = 0; i < frame.getHeight(); i++) {
			for (int j = 0; j < frame.getWidth(); j++) {
				if (i == j) {
					// set the color of the pixel at position i, j to red
					raster.setDataElements(i, j,model.getDataElements(rgbRed, null));
				}
			}
		}
		// draws the image 
		g.drawImage(image, 0, 0, null);

	}
}
