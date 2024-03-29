import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 * 
 */

/**
 * @author Ivan & Luis
 * 
 */
public class BasicPieButtonUI extends PieButtonUI implements MouseListener,
		MouseMotionListener, KeyListener {

	//private BufferedImage blueButton;

	public BasicPieButtonUI() {
		//createImages();
	}

	/*
	 * 
	 */
	public static ComponentUI createUI(JComponent c) {
		return new BasicPieButtonUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#installUI(javax.swing.JComponent)
	 */
	public void installUI(JComponent c) {
		PieButton pieButton = (PieButton) c;
		pieButton.addMouseListener(this);
		pieButton.addMouseMotionListener(this);
		pieButton.addKeyListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#uninstallUI(javax.swing.JComponent)
	 */
	public void uninstallUI(JComponent c) {
		PieButton pieButton = (PieButton) c;
		pieButton.removeMouseListener(this);
		pieButton.removeMouseMotionListener(this);
		pieButton.removeKeyListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#paint(java.awt.Graphics,
	 * javax.swing.JComponent)
	 */
	public void paint(Graphics g, JComponent c) {
		//Graphics2D g2 = (Graphics2D) g;
		//g2.drawImage(blueButton, 0, 0, null);
	}
/*
	private void createImages() {
		String filename = "resources/blue_button.png";
		try {
			InputStream in = getClass().getResourceAsStream(filename);
			blueButton = ImageIO.read(in);
		} catch (IOException e) {
		}
	}*/

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//((PieButton)e.getComponent()).setHierarchyVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
