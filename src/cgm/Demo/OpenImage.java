package cgm.Aufgaben;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;



/**
 * The Class OpenImage loads and image an places it in a JFrame
 * 
 * @author Sprotte 
 * 
 * @version 1.0
 */
public class OpenImage {

	/**
	 * The main method. 
	 * 
	 * @param Args
	 *            the arguments
	 */
	public static void main(final String[] Args) {
		load();
	}

	/**
	 * the method loads the image and it will open in a suitably window
	 */
	public static void load() {
		
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
					final JFrame frame = new JFrame("Fenster");
					// set the frame size to the image size
					frame.setSize(image.getWidth(), image.getHeight());
					// add the image as imageIcon to the content pane of the frame
					
					frame.getContentPane().add(new JLabel(new ImageIcon(image)));
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					ungültig = false;
				// catch the io exception
				} catch (IOException e) {
					System.out.println("Fehler beim Laden des Bildes!");
				}
			}
		}
	}
}
