package cgm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;









import cgm.Camera.Camera;
import cgm.Camera.PerspectiveCamera;
import cgm.Geometry.Geometry;
import cgm.Geometry.MyColor;
import cgm.Geometry.Node;
import cgm.Geometry.ShapeFromFile;
import cgm.Light.DirectionalLight;
import cgm.Light.Light;
import cgm.Light.PointLight;
import cgm.Material.LambertMaterial;
import cgm.Material.PhongMaterial;
import cgm.Math.Point3;
import cgm.Math.Vector3;

/**
 * The Class Gui.
 */
@SuppressWarnings("unused")
public class Gui {

	/** The bar. */
	private final JMenuBar bar;

	/** The data menu. */
	private final JMenu dataMenu;
	/** The renderMenu. */
	private final JMenu renderMenu;
	/** The ObjLoaderMenu. */
	private final JMenu objLoader;

	/** The normalMenu. */
	private final JMenuItem normalMenu;
	/** The gaussianMenu. */
	private final JMenuItem gaussianMenu;
	/** The sobelMenu. */
	private final JMenuItem sobelMenu;
	/** The sobelMenu. */
	private final JMenuItem celShadingMenu;
	/** The greyMenu. */
	private final JMenuItem greyMenu;
	/** The imageMenu. */
	private final JMenuItem imageMenu;
	/** The saveMenu. */
	private final JMenuItem saveMenu;
	/** The loadMenu. */
	private final JMenuItem loadMenu;
	/** The loadMenu. */
	private final JMenuItem objLoadMenu;
	/** The frame. */
	final JFrame frame;

	/** The world. */
	
	private final World world;

	/** The camera. */
	
	private final Camera camera;

	/** The Constant WIDTH. */
	private static final int WIDTH = 1280;

	/** The Constant HEIGHT. */
	private static final int HEIGHT = 1024;

	/** The ray tracer. */
	private final Raytracer rayTracer;

	/** The canvas. */
	private RaytracerCanvas canvas;

	/** The displayed image. */
	private BufferedImage displayedImage;

	/**
	 * Instantiates a new gui.
	 * 
	 * @param world
	 *            the world
	 * @param camera
	 *            the camera
	 */
	public Gui(final World world, final Camera camera) {
		this.world = world;
		this.camera = camera;

		rayTracer = new Raytracer(world, camera, WIDTH, HEIGHT);

		displayedImage = rayTracer.createImage(camera, 0);

		// creates a JMenuBar
		bar = new JMenuBar();
		// creates a JMenu
		dataMenu = new JMenu("File");

		// creates a JMenuItem
		loadMenu = new JMenuItem("Open");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		loadMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				displayedImage = load();
				frame.setSize(displayedImage.getWidth(),
						displayedImage.getHeight());
				canvas = new RaytracerCanvas(displayedImage);
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();

			}
		});
		// adds the JMenüItem to the JMenu
		dataMenu.add(loadMenu);

		// creates a JMenuItem
		saveMenu = new JMenuItem("Speichern");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		saveMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// called the save method an catch the io exception
				try {
					save();
				} catch (IOException e1) {
					System.out.print("Fehler beim speichern des Bildes!!!");
				}
			}
		});
		// adds the JMenüItem to the JMenu
		dataMenu.add(saveMenu);

		renderMenu = new JMenu("Renderer");

		imageMenu = new JMenuItem("Image");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		imageMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas = new RaytracerCanvas(displayedImage);
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();

			}
		});
		// adds the JMenüItem to the JMenu
		renderMenu.add(imageMenu);

		// creates a JMenuItem
		normalMenu = new JMenuItem("Normal");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		normalMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas = new RaytracerCanvas(rayTracer.createImage(camera, 1));
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();

			}
		});
		// adds the JMenüItem to the JMenu
		renderMenu.add(normalMenu);

		greyMenu = new JMenuItem("Greyscale");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		greyMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas = new RaytracerCanvas(EdgeDetection
						.creatGrayscale(displayedImage));
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();

			}
		});
		// adds the JMenüItem to the JMenu
		renderMenu.add(greyMenu);

		gaussianMenu = new JMenuItem("Median Filter");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		gaussianMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas = new RaytracerCanvas(EdgeDetection
						.medianFilter(EdgeDetection
								.creatGrayscale(displayedImage)));
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();

			}
		});
		// adds the JMenüItem to the JMenu
		renderMenu.add(gaussianMenu);

		sobelMenu = new JMenuItem("Sobel Operator");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		sobelMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				canvas = new RaytracerCanvas(EdgeDetection
						.detectEdges(EdgeDetection.creatGrayscale(rayTracer
								.createImage(camera, 1))));
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();

			}
		});
		// adds the JMenüItem to the JMenu
		renderMenu.add(sobelMenu);

		celShadingMenu = new JMenuItem("Cel Shading");
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		celShadingMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas = new RaytracerCanvas(rayTracer.createCelshading());
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();
			}
		});
		// adds the JMenüItem to the JMenu
		renderMenu.add(celShadingMenu);
		
		
		// creates a JMenuItem
		objLoadMenu = new JMenuItem("Load");
		objLoader = new JMenu("Obj Loader");

		
		// adds actionListener with overwritten actionPerformed to the JMenuItem
		objLoadMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				final JFileChooser chooser = new JFileChooser();
				
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);				
				chooser.setMultiSelectionEnabled(false);
				int exitVal = chooser.showOpenDialog(null);

				if (exitVal == JFileChooser.APPROVE_OPTION) {
					
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
							
							ArrayList<Geometry> geos = new ArrayList<Geometry>();
							ArrayList<Light> lights = new ArrayList<Light>();
							String path = chooser.getSelectedFile().getAbsolutePath();
							ShapeFromFile test = new ShapeFromFile(path, new LambertMaterial(new MyColor(1, 1, 1)));
							
							Node testnode =  test.OBJLoader();
							
							
							Camera cam = new PerspectiveCamera(new Point3(10, 15, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
							//Camera cam = new PerspectiveCamera(new Point3(0.2, 0.3, 0.2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);
							
							Light directionalLight = new DirectionalLight(new MyColor(1, 1, 1), (new Vector3(0, 1, 0)),false);
					
							PointLight pointLight = new PointLight(new MyColor(1, 1, 1), new Point3(3, 3, 3),true);
							lights.add(directionalLight);
							
							geos.add(testnode);
							World world = new World(geos, lights, new MyColor(0.25,0.25,0.25), 1.0);
							new Gui(world, cam);

						
					
				}
				
				
				
				frame.setSize(displayedImage.getWidth(),
						displayedImage.getHeight());
				canvas = new RaytracerCanvas(displayedImage);
				frame.getContentPane().removeAll();

				frame.getContentPane().add(canvas);
				frame.revalidate();
				frame.getContentPane().repaint();

			}
		});
		objLoader.add(objLoadMenu);
		
		// adds the JMenu to the JMenuBar
		bar.add(dataMenu);
		// adds the JMenu to the JMenuBar
		bar.add(renderMenu);
		bar.add(objLoader);
		frame = new JFrame("Fenster");
		// set the size
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// adds the JMenuBar to the frame
		frame.setJMenuBar(bar);

		
		// adds the JMenüItem to the JMenu
		dataMenu.add(loadMenu);

		canvas = new RaytracerCanvas(displayedImage);

		frame.getContentPane().add(canvas);

		frame.setVisible(true);

	}

	/**
	 * Save.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void save() throws IOException {

		// opens a file chooser
		final JFileChooser chooser = new JFileChooser();
		// set multiselection to false
		chooser.setMultiSelectionEnabled(false);

		// shows the SaveDialog
		int result = chooser.showSaveDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			File file = (chooser.getSelectedFile());
			if (!file.getName().toLowerCase().endsWith(".png")) {
				file = new File(file.getCanonicalPath() + ".png");
			}
			// write the image as png in the selected file

			ImageIO.write(canvas.image, "PNG", file);
		} else {
			System.out.print("Fehler");
		}

	}

	/**
	 * the method loads the image and it will open in a suitably window.
	 * 
	 * @return the buffered image
	 */
	public BufferedImage load() {

		// opens a file chooser
		final JFileChooser chooser = new JFileChooser();
		// set a filter to the chooser for image files
		final FileFilter filter = new FileNameExtensionFilter("Image Files",
				ImageIO.getReaderFileSuffixes());
		chooser.setFileFilter(filter);
		// hides all files filter
		chooser.setAcceptAllFileFilterUsed(false);
		// set multiselection to false
		chooser.setMultiSelectionEnabled(false);
		int exitVal = chooser.showOpenDialog(null);

		if (exitVal == JFileChooser.APPROVE_OPTION) {
			boolean ungültig = true;
			while (ungültig) {
				try {
					// creates a buffered image with the selected file
					final BufferedImage image = ImageIO.read(chooser
							.getSelectedFile());
					// creates a frame
					return image;

					// catch the io exception
				} catch (IOException e) {
					System.out.println("Fehler beim Laden des Bildes!");
				}
			}
		}
		return null;
	}

}
