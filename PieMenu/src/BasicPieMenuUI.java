import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
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
public class BasicPieMenuUI extends PieMenuUI implements MouseListener,
		MouseMotionListener, KeyListener {

	private BufferedImage blueButton;

	public BasicPieMenuUI() {
		createImages();
	}

	/*
	 * 
	 */
	public static ComponentUI createUI(JComponent c) {
		return new BasicPieMenuUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#installUI(javax.swing.JComponent)
	 */
	public void installUI(JComponent c) {
		PieMenu pieMenu = (PieMenu) c;
		pieMenu.addMouseListener(this);
		pieMenu.addMouseMotionListener(this);
		pieMenu.addKeyListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#uninstallUI(javax.swing.JComponent)
	 */
	public void uninstallUI(JComponent c) {
		PieMenu pieMenu = (PieMenu) c;
		pieMenu.removeMouseListener(this);
		pieMenu.removeMouseMotionListener(this);
		pieMenu.removeKeyListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#paint(java.awt.Graphics,
	 * javax.swing.JComponent)
	 */
	public void paint(Graphics g, JComponent c) {
		Graphics2D g2 = (Graphics2D) g;
		((PieMenu) c).updateButtons();
		int menuCenterX = (c.getWidth() / 2);
		int menuCenterY = (c.getHeight() / 2);
		paintCircle(g2, c, menuCenterX, menuCenterY);
		((PieMenu) c).updateArcs(menuCenterX, menuCenterY);
		g2.drawImage(blueButton, menuCenterX - (blueButton.getWidth() / 2),
				menuCenterY - (blueButton.getHeight() / 2), null);
	}

	private void paintCircle(Graphics2D g2, JComponent c, int x, int y) {
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval(x - 80, y - 80, 160, 160);
		g2.setColor(Color.ORANGE);
		g2.fill(((PieMenu) c).getArc());
	}

	private void createImages() {
		String filename = "resources/blue_button.png";
		try {
			InputStream in = getClass().getResourceAsStream(filename);
			blueButton = ImageIO.read(in);
		} catch (IOException e) {
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void mouseMoved(MouseEvent e) {
		if (((PieMenu) (e.getComponent())).getArc().contains(e.getPoint())) {
			System.out.println("Arc test");
		}
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
