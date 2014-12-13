package cgm;


import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


import java.util.ArrayList;


import java.util.concurrent.Executors;


import cgm.Camera.Camera;
import cgm.Geometry.Geometry;
import cgm.Geometry.MyColor;
import cgm.Math.Normal3;


/**
 * The Class Raytracer represents the main class which creats the Image with the
 * world.
 *
 * @author Sprotte 
 * @version 1.0
 */
public class Raytracer {

	/** The world. */
	private final World world;
	/** The world. */
	private final World worldCelShading;
	/** The world. */
	private final World worldSingleColor;

	
	/** The camera. */
	private final Camera camera;
	
	/** The width. */
	private final int width;
	
	/** The height. */
	private final int height;

	
	/**
	 * Instantiates a new raytracer.
	 *
	 * @param world the world
	 * @param camera the camera
	 * @param width the width
	 * @param height the height
	 * @throws will be thrown if the given argument was null
	 */
	public Raytracer(final World world, final Camera camera, final int width,
			final int height) {
		if (world == null) {
			throw new IllegalArgumentException("The world cannot be null!");
		}
		if (camera == null) {
			throw new IllegalArgumentException("The camera cannot be null!");
		}
		
		this.world = world;
		
		ArrayList<Geometry> singleColorGeo = new ArrayList<Geometry>();
		ArrayList<Geometry> celShadingGeo = new ArrayList<Geometry>();
		
		for(Geometry geo : world.geoList){
			singleColorGeo.add(geo.asSingelColor());
			celShadingGeo.add(geo.asCelShading());
		}
		worldCelShading = new World(celShadingGeo, world.lightList, world.ambientLight, world.refractionIndex);
		worldSingleColor = new World(singleColorGeo, world.lightList, world.ambientLight, world.refractionIndex);
		this.camera = camera;
		this.width = width;
		this.height = height;
		

	}

	/**
	 * Creates the image
	 *	Modus 0 for creating the image with the color given from the material 
	 *	Modus 1 for creating the image with one color for every normal of every geometry in the scene
	 *	Modus 2 for creating the image in Cel Shading
	 * @param camera the camera
	 * @param modus the modus
	 * @return the buffered image
	 * @throws will be thrown if the given argument was null
	 */
	public BufferedImage createImage(final Camera camera,final int modus) {		
		if (camera == null) {
			throw new IllegalArgumentException("The camera cannot be null!");
		}
		if (modus < 0 || modus > 3) {
			throw new IllegalArgumentException("Only 0 for normal Raytracing or 1 for displaying the normals of the image");
		}
		long zstVorher;
		long zstNachher;
		zstVorher = System.currentTimeMillis();
		
		Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		 BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		final WritableRaster raster = image.getRaster();
		// get the color model from the created image
		final ColorModel model = image.getColorModel();		
		Ray ray;
		
		/*
		
		for (int i = 0; i < image.getWidth(); i++) {
	        final int ii =i;
	        executor.execute(new Runnable() {
	            public void run() {
	                for (int j = 0; j < image.getHeight(); j++) {
	                    raster.setDataElements(ii, image.getHeight()-j-1, model.getDataElements(
	                            getPixelColor(width,height,ii,j,world,camera),0, null));
	                }
	            }
	        });
	    }
		
		*/
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				ray = camera.rayFor(width, height, i, j);
				//created the image with the colors given from the materials  
				if (modus == 0) {
					raster.setDataElements(i, height - j - 1, model
							.getDataElements(getColor(ray).getIntFromColor(),
									null));
				}
				//created the image with colors for each normal from every object
				if (modus == 1) {
					raster.setDataElements(i, height - j - 1, model
							.getDataElements(getColorNormal(ray)
									.getIntFromColor(), null));
				}
				if (modus == 3) {
					raster.setDataElements(i, height - j - 1, model
							.getDataElements(getColorCelShading(ray)
									.getIntFromColor(), null));
				}

			}
		}
		
		if (modus == 2) {
			image = createCelshading();
		
				
			
		}
		zstNachher = System.currentTimeMillis();
		System.out.println("Zeit benötigt: " + ((zstNachher - zstVorher)) + " sec");
		return image;
		/*
		
		executor.shutdown();
		try {
		    executor.awaitTermination(100, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    // what should happen if timeout was hit
		}
		
		zstNachher = System.currentTimeMillis();
		System.out.println("Zeit benötigt: " + ((zstNachher - zstVorher)) + " sec");
		return image;
		*/
		
	}
	
	public BufferedImage createCelshading(){
		BufferedImage returnImage = createImage(camera, 3);
		BufferedImage edgeImage = EdgeDetection.sobelOperation(createImage(camera, 1));
		for (int i = 0; i < returnImage.getWidth(); i++) {
			for (int j = 0; j < returnImage.getHeight(); j++) {
				if(edgeImage.getRGB(i, j)==-16777216){
					returnImage.setRGB(i, j, edgeImage.getRGB(i, j));					
				}
				else{
					returnImage.setRGB(i, j, returnImage.getRGB(i, j));
				}
				
			}}
		return returnImage;
		
	}
	/**
	 * Gets the color from the material of the taken object 
	 *
	 * @param ray the ray
	 * @return the color
	 * @throws will be thrown if the given argument was null
	 */
	public MyColor getColor(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The ray cannot be null!");
		}
		final Hit hit = world.hit(ray);		
		if (hit != null) {

			return hit.geo.material.colorFor(hit, world,new Tracer(world,6));
			
		} else {
			return world.backgroundColor;
		}

	}

	/**
	 * Gets the color from every normal of the taken geometry multiplied by the hash code 
	 * to make each color unique 
	 *
	 * @param ray the ray
	 * @return the color normal
	 * @throws will be thrown if the given argument was null
	 */
	public MyColor getColorNormal(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The ray cannot be null!");
		}
		/*
		 * final Hit hit = world.hit(ray);
		 * 
		 * if (hit != null) { return new Color(hit.normal.x, hit.normal.y,
		 * hit.normal.z); } else { return world.backgroundColor; }
		 */

		final Hit hit = worldSingleColor.hit(ray);

		if (hit != null) {
			double a = hit.geo.hashCode();
			if (a < 0) {
				a = a * -1;
			}
			while (a > 1) {
				a = a / 2;
			}

			if (hit.normal.equals(new Normal3(1, 0, 0))) {
				return new MyColor(a, 1, 1);
			}
			if (hit.normal.equals(new Normal3(0, 1, 0))) {
				return new MyColor(a, 0, a);

			}
			if (hit.normal.equals(new Normal3(0, 0, 1))) {
				return new MyColor(1, a, a);

			}

			if (hit.normal.equals(new Normal3(-1, 0, 0))) {
				return new MyColor(a, 1, 0);

			}
			if (hit.normal.equals(new Normal3(0, -1, 0))) {
				return new MyColor(a, 1, a);

			}
			if (hit.normal.equals(new Normal3(0, 0, -1))) {
				return new MyColor(a, 0, a);

			} else {

				return new MyColor(0, a, 1);

			}

		} else {
			return world.backgroundColor;
		}

	}
	public MyColor getColorCelShading(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The ray cannot be null!");
		}
		final Hit hit = worldCelShading.hit(ray);		
		if (hit != null) {

			return hit.geo.material.colorFor(hit, worldCelShading,new Tracer(world,6));
			
		} else {
			return world.backgroundColor;
		}

	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camera == null) ? 0 : camera.hashCode());
		result = prime * result + height;
		result = prime * result + width;
		result = prime * result + ((world == null) ? 0 : world.hashCode());
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
		Raytracer other = (Raytracer) obj;
		if (camera == null) {
			if (other.camera != null)
				return false;
		} else if (!camera.equals(other.camera))
			return false;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		if (world == null) {
			if (other.world != null)
				return false;
		} else if (!world.equals(other.world))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Raytracer [world=" + world + ", camera=" + camera + ", width="
				+ width + ", height=" + height + "]";
	}

	
	/*
	 * public BufferedImage createImage(final Camera camera,final int modus) {
		long zstVorher;
		long zstNachher;
		zstVorher = System.currentTimeMillis();
		final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		if (camera == null) {
			throw new IllegalArgumentException("The camera cannot be null!");
		}
		if (modus < 0 || modus > 1) {
			throw new IllegalArgumentException("Only 0 for normal Raytracing or 1 for displaying the normals of the image");
		}

		final BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		final WritableRaster raster = image.getRaster();
		// get the color model from the created image
		final ColorModel model = image.getColorModel();
		
		for (int i = 0; i < image.getWidth(); i++) {
	        final int ii =i;
	        executor.execute(new Runnable() {
	            public void run() {
	                for (int j = 0; j < image.getHeight(); j++) {
	                    raster.setDataElements(ii, image.getHeight()-j-1, model.getDataElements(
	                            getPixelColor(width,height,ii,j,world,camera),0, null));
	                }
	            }
	        });
	    }
		executor.shutdown();
		try {
		    executor.awaitTermination(100, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    // what should happen if timeout was hit
		}
		
		zstNachher = System.currentTimeMillis();
		System.out.println("Zeit benötigt: " + ((zstNachher - zstVorher)) + " sec");
		return image;
		
	}
	*/
	public static float[] getPixelColor(final int width,final int height,
	        final int x,final int y,final World world,final Camera camera){
	    final Hit hit = world.hit(camera.rayFor(width,height, x, y));
	    if(hit != null){
	        return new float[]{(float) hit.geo.material.colorFor(hit,world,new Tracer(world, 6)).r,
	                (float) hit.geo.material.colorFor(hit,world,new Tracer(world, 6)).g,(float) hit.geo.material.colorFor(hit,world,new Tracer(world, 6)).b, 1};
	    } else {
	        return new float[]{(float) world.backgroundColor.r, (float) world.backgroundColor.g, (float) world.backgroundColor.b, 1};
	    }
	}
	 
}
