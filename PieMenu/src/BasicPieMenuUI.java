import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Arc2D;
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
		// Set anti-aliasing
		RenderingHints rh = g2.getRenderingHints();
		rh.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);

		((PieMenu) c).updateButtons();
		((PieMenu) c).innerBoundingBox.setLocation((c.getWidth() / 2)
				- ((PieMenu) c).getRadius(), (c.getHeight() / 2)
				- ((PieMenu) c).getRadius());
		int menuCenterX = (c.getWidth() / 2);
		int menuCenterY = (c.getHeight() / 2);
		paintCircle(g2, c, ((PieMenu) c).innerBoundingBox);
		paintInnerArcs(g2, c);
		paintLines(g2, c);
		// ((PieMenu) c).updateArcs();
		g2.drawImage(blueButton, menuCenterX - (blueButton.getWidth() / 2),
				menuCenterY - (blueButton.getHeight() / 2), null);
	}

	private void paintCircle(Graphics2D g2, JComponent c, Rectangle box) {
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval((int) box.getX(), (int) box.getY(), ((PieMenu) c)
				.getDiameter(), ((PieMenu) c).getDiameter());
	}

	/*
	 * Testing the Pie Menu Buttons arcs
	 */
	private void paintInnerArcs(Graphics2D g2, JComponent c) {
		Arc2D.Float[] arcs = ((PieMenu) c).getPieButtonsArcs();
		for (int i = 0; i < arcs.length; i++) {
			g2.setColor(new Color(215, 100 + (i * 15), 0 + (i * 10)));
			g2.fill(arcs[i]);
		}
	}

	// Method for painting lines from parent button to hierarchical children
	private void paintLines(Graphics2D g2, JComponent c) {
		g2.setColor(Color.DARK_GRAY);

		for (int i = 0; i < ((PieMenu) c).getPieButtons().length; i++) {
			// 22 pixel offset for current buttons
			int currentPieButtonX = ((PieMenu) c).getPieButtons()[i].getX() + 22;
			int currentPieButtonY = ((PieMenu) c).getPieButtons()[i].getY() + 22;
			for (int y = 0; y < ((PieMenu) c).getPieButtons()[i].hierarchButtons.length; y++) {
				// Draw lines only if visible
				if (((PieMenu) c).getPieButtons()[i].hierarchButtons[y]
						.isVisible() == true) {
					// Draw line w/ 22 pixel offset for each button..
					g2.drawLine(currentPieButtonX, currentPieButtonY,
							((PieMenu) c).getPieButtons()[i].hierarchButtons[y]
									.getX() + 22,
							((PieMenu) c).getPieButtons()[i].hierarchButtons[y]
									.getY() + 22);
				}
			}
		}

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
		Arc2D arc[] = ((PieMenu) e.getComponent()).getPieButtonsArcs();
		PieButton[] pieButton = ((PieMenu) e.getComponent()).getPieButtons();
		for (int i = 0; i < arc.length; i++) {
			if (arc[i].contains(e.getPoint())) {
				pieButton[i].setRollover(true);
			} else {
				pieButton[i].setRollover(false);
			}
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
						pieMenu.getPieButtons()[i].changeHierarchAngle(5);
						pieMenu.getPieButtons()[i].setHierarchyVisible(true);
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
						pieMenu.getPieButtons()[i].changeHierarchAngle(-5);
						pieMenu.getPieButtons()[i].setHierarchyVisible(true);
					}
				}
			}
		}
	}

}
