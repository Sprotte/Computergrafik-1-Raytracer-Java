package cgm.Aufgaben;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The Class ImageSaver open a frame with a custom canvas.
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 * 
 */
public class ImageSaver {

	/** The frame. */
	final JFrame frame;

	/** The bar. */
	final JMenuBar bar;

	/** The data menu. */
	final JMenu dataMenu;

	/** The save menu. */
	final JMenuItem saveMenu;

	/** The canvas. */
	final CustomCanvas canvas;

	/**
	 * Instantiates a new image saver and builds the GUI.
	 * 
	 * @param height  the height of the frame
	 * @param width   the width	of the frame
	 */
	public ImageSaver(final int height, final int width) {

		// creates a JMenuBar
		bar = new JMenuBar();
		// creates a JMenu
		dataMenu = new JMenu("Datei");
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
		// adds the JMenu to the JMenuBar
		bar.add(dataMenu);
		
		// creates a frame
		frame = new JFrame("Fenster");
		// set the size 
		frame.setSize(height, width);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// adds the JMenuBar to the frame
		frame.setJMenuBar(bar);
		// creates a CustomCanvas 
		canvas = new CustomCanvas(frame);
		// adds the CustomCanvas to the contentPane of the frame
		frame.getContentPane().add(canvas);

		frame.setVisible(true);
		// adds a CompontentListener to listen for frame resize events
		frame.addComponentListener(new ReSizeListener());

	}

	/**
	 * Save the image as a png
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
	 * The listener interface for receiving reSize events. The class that is
	 * interested in processing a reSize event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addReSizeListener<code> method. When
	 * the reSize event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see ReSizeEvent
	 */
	public class ReSizeListener implements ComponentListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
		 * ComponentEvent)
		 */
		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
		 * ComponentEvent)
		 */
		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub

		}

		/*
		 * 
		 * Calls the repaint of the canvas if the window has been resized
		 * 
		 */
		@Override
		public void componentResized(ComponentEvent arg0) {
			// called the repaint of the canvas
			canvas.repaint();

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
		 * ComponentEvent)
		 */
		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
