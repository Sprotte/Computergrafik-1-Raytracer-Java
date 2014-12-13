package cgm;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import cgm.Geometry.MyColor;
import cgm.Math.Mat3x3;


/**
 * The static Class for Edge Detection.
 * implents Graysclale convert, Median Filter and Sorbel Operation
 * 
 * @author Sprotte
 * 
 * @version 1.0
 */
public class EdgeDetection {

	/**
	 * creats the grayscale of the image
	 *
	 * @param image the image
	 * @return the graysacle image
	 * @throws will be thrown if the given argument was null
	 */
	public static BufferedImage creatGrayscale(final BufferedImage image) {
		if (image == null) {
			throw new IllegalArgumentException("The Image cannot be null!");
		}
		

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				final Color c = new Color(image.getRGB(i, j));
				final int red = (int) (c.getRed() * 0.299);
				final int green = (int) (c.getGreen() * 0.587);
				final int blue = (int) (c.getBlue() * 0.114);
				final Color newColor = new Color(red + green + blue, red
						+ green + blue, red + green + blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	/**
	 * Median filter or Median Blur
	 *
	 * @param image the image
	 * @return the blurred buffered image
	 * @throws will be thrown if the given argument was null
	 */
	public static BufferedImage medianFilter(final BufferedImage image) {
		if (image == null) {
			throw new IllegalArgumentException("The Image cannot be null!");
		}
		final BufferedImage returnImage = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());

		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				//creats an 3x3 matrix with the neighbours of a pixel
				final Mat3x3 matrix1 = new Mat3x3(image.getRGB(i - 1, j - 1),
						image.getRGB(i, j - 1), image.getRGB(i + 1, j - 1),
						image.getRGB(i - 1, j), image.getRGB(i, j),
						image.getRGB(i + 1, j), image.getRGB(i - 1, j + 1),
						image.getRGB(i, j + 1), image.getRGB(i + 1, j + 1));
				//fills the matrix in a array
				final double[] array = { matrix1.m11, matrix1.m12, matrix1.m13,
						matrix1.m21, matrix1.m22, matrix1.m23, matrix1.m31,
						matrix1.m32, matrix1.m33 };
				Arrays.sort(array);
				//finds the median of the array
				final double median;
				if (array.length % 2 == 0)
					median = ((double) array[array.length / 2] + (double) array[array.length / 2 - 1]) / 2;
				else
					median = (double) array[array.length / 2];

				final Color c = new Color((int) median);
				//sets the pixel to the value of the median
				returnImage.setRGB(i, j, c.getRGB());
				;
			}
		}

		return returnImage;

	}

	/**
	 * Sobel operation.
	 *
	 * @param image the image
	 * @return the buffered image
	 * @throws will be thrown if the given argument was null
	 */
	public static BufferedImage sobelOperation(final BufferedImage image) {
		if (image == null) {
			throw new IllegalArgumentException("The Image cannot be null!");
		}
		final BufferedImage returnImage = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());
		//the sobel matrizen
		final Mat3x3 sobelX = new Mat3x3(-1, -2, -1, 0, 0, 0, 1, 2, 1);
		final Mat3x3 sobelY = new Mat3x3(-1, 0, 1, -2, 0, 2, -1, 0, 1);
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				//creats an 3x3 matrix with the neighbours of a pixel
				final Mat3x3 pixelMatrix = new Mat3x3(
						image.getRGB(i - 1, j - 1), image.getRGB(i, j - 1),
						image.getRGB(i + 1, j - 1), image.getRGB(i - 1, j),
						image.getRGB(i, j), image.getRGB(i + 1, j),
						image.getRGB(i - 1, j + 1), image.getRGB(i, j + 1),
						image.getRGB(i + 1, j + 1));
				
				final double pixel_x = (sobelX.m11 * pixelMatrix.m11)
						+ (sobelX.m12 * pixelMatrix.m12)
						+ (sobelX.m13 * pixelMatrix.m13)
						+ (sobelX.m21 * pixelMatrix.m21)
						+ (sobelX.m22 * pixelMatrix.m22)
						+ (sobelX.m23 * pixelMatrix.m23)
						+ (sobelX.m31 * pixelMatrix.m31)
						+ (sobelX.m32 * pixelMatrix.m32)
						+ (sobelX.m33 * pixelMatrix.m33);

				final double pixel_y = (sobelY.m11 * pixelMatrix.m11)
						+ (sobelY.m12 * pixelMatrix.m12)
						+ (sobelY.m13 * pixelMatrix.m13)
						+ (sobelY.m21 * pixelMatrix.m21)
						+ (sobelY.m22 * pixelMatrix.m22)
						+ (sobelY.m23 * pixelMatrix.m23)
						+ (sobelY.m31 * pixelMatrix.m31)
						+ (sobelY.m32 * pixelMatrix.m32)
						+ (sobelY.m33 * pixelMatrix.m33);

				double val = Math.sqrt((pixel_x * pixel_x)
						+ (pixel_y * pixel_y));

				val = Math.max(0, val);
				val = Math.min(val, 255);
				if(val == 0.0){
					returnImage.setRGB(i, j,new MyColor(1, 1, 1).getIntFromColor());
				}else{
					returnImage.setRGB(i, j,new MyColor(0, 0, 0).getIntFromColor());
				}
				/*
				final Color color = new Color((int) val);
				returnImage.setRGB(i, j, color.getRGB());
				*/
				/*
				 * 
				 * double thisAngle = (Math.atan2(pixel_x, pixel_y)/3.14159) *
				 * 180.0;
				 * 
				 * MyColor myColor = new MyColor(0,0,0); if ( ( (thisAngle <
				 * 22.5) && (thisAngle > -22.5) ) || (thisAngle > 157.5) ||
				 * (thisAngle < -157.5) ) myColor = new MyColor (1,1,0); if ( (
				 * (thisAngle > 22.5) && (thisAngle < 67.5) ) || ( (thisAngle <
				 * -112.5) && (thisAngle > -157.5) ) ) myColor = new MyColor
				 * (0,1,0); if ( ( (thisAngle > 67.5) && (thisAngle < 112.5) )
				 * || ( (thisAngle < -67.5) && (thisAngle > -112.5) ) ) myColor
				 * = new MyColor (0,0,1); if ( ( (thisAngle > 112.5) &&
				 * (thisAngle < 157.5) ) || ( (thisAngle < -22.5) && (thisAngle
				 * > -67.5) ) ) myColor = new MyColor (1,0,0);
				 */
				// returnImage.setRGB(i, j, myColor.getIntFromColor());;
				
				;

			}
		}

		return creatGrayscale(returnImage);
	}

	/**
	 * Detect edges.
	 *
	 * @param image the image
	 * @return the buffered image
	 * @throws will be thrown if the given argument was null
	 */
	public static BufferedImage detectEdges(final BufferedImage image) {
		if (image == null) {
			throw new IllegalArgumentException("The Image cannot be null!");
		}

		return medianFilter(sobelOperation(image));

	}

}
