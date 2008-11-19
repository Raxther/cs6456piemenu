import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
		MouseMotionListener, MouseWheelListener, KeyListener {

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
		pieMenu.addMouseWheelListener(this);
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
		((PieMenu) c).innerBoundingBox.setLocation((c.getWidth() / 2)
				- ((PieMenu) c).getRadius(), (c.getHeight() / 2)
				- ((PieMenu) c).getRadius());
		int menuCenterX = (c.getWidth() / 2);
		int menuCenterY = (c.getHeight() / 2);
		paintCircle(g2, c, ((PieMenu) c).innerBoundingBox);
		((PieMenu) c).updateArcs(menuCenterX, menuCenterY);
		g2.drawImage(blueButton, menuCenterX - (blueButton.getWidth() / 2),
				menuCenterY - (blueButton.getHeight() / 2), null);
	}

	private void paintCircle(Graphics2D g2, JComponent c, Rectangle box) {
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval((int) box.getX(), (int) box.getY(), ((PieMenu) c)
				.getDiameter(), ((PieMenu) c).getDiameter());
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		PieMenu pieMenu = (PieMenu) e.getComponent();
		if (notches < 0) {
			// Wheel UP:
			if (pieMenu.innerBoundingBox.contains(e.getPoint())) {
				pieMenu.changeInitAngle(10);
			} else {
				for (int i = 0; i < pieMenu.getPieButtons().length; i++) {
					if (pieMenu.getPieButtons()[i].isExpanded()) {
						// ROTATE the heirarchy of this Button
					}
				}
			}

		} else {
			// Wheel DOWN:
			if (pieMenu.innerBoundingBox.contains(e.getPoint())) {
				pieMenu.changeInitAngle(-10);
			} else {
				for (int i = 0; i < pieMenu.getPieButtons().length; i++) {
					if (pieMenu.getPieButtons()[i].isExpanded()) {
						// ROTATE the heirarchy of this Button
					}
				}
			}
		}
	}

}
