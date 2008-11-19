import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 */

/**
 * @author Ivan & Luis
 * 
 */
public class PieMenu extends JComponent implements ChangeListener {

	private PieMenuModel pieMenuModel;
	private PieButton[] pieButtons = new PieButton[8];
	private Timer buttonsTimer;
	private double initAngle;
	private int diameter;
	private int radius;
	public Rectangle innerBoundingBox;

	private Arc2D.Float arc;

	public PieMenu() {
		buttonsTimer = new Timer(50, new ButtonsAnimListener());
		initAngle = 0;
		pieMenuModel = new PieMenuModel();
		setModel();
		arc = new Arc2D.Float(Arc2D.PIE); // ARC
		updateArcs(this.getWidth() / 2, this.getHeight() / 2);
		diameter = 160;
		radius = diameter / 2;
		innerBoundingBox = new Rectangle(this.getWidth() / 2 - diameter / 2,
				this.getHeight() / 2 - diameter / 2, diameter, diameter);
		buildMenuButtons();
		updateUI();
		buttonsTimer.start();
	}

	private void buildMenuButtons() {
		int d = 0;
		for (int i = 0; i < pieButtons.length; i++) {
			pieButtons[i] = new PieButton(this, 12);
			pieButtons[i].setDegree(d);
			for (int y = 0; y < pieButtons[i].hierarchButtons.length; y++) {
				this.add(pieButtons[i].hierarchButtons[y]);
			}
			this.add(pieButtons[i]);
			d += (360 / pieButtons.length);
			pieButtons[i].setVisible(false);
		}
		updateButtons();
	}

	public void updateButtons() {
		double angularSpacing = (double) 360 / (double) pieButtons.length;

		// Calculate center, with offset
		int centerX = (getWidth() / 2) - 22;
		int centerY = (getHeight() / 2) - 22;

		for (int i = 0; i < pieButtons.length; i++) {
			// Get current angles (in radians)
			double currentXAngle = Math.cos(Math.toRadians(initAngle));
			double currentYAngle = Math.sin(Math.toRadians(initAngle));
			// Get current offset coordinates
			double currentXCoordinate = 80 * currentXAngle;
			double currentYCoordinate = 80 * currentYAngle;
			// Position buttons around circle
			pieButtons[i].setBounds(centerX + (int) currentXCoordinate, centerY
					- (int) currentYCoordinate, 44, 44);
			initAngle += angularSpacing;
			pieButtons[i].updateHierarchy();
		}

	}

	public void setHierarchyHidden() {
		for (int i = 0; i < pieButtons.length; i++) {
			if (pieButtons[i].isExpanded()) {
				pieButtons[i].setHierarchyVisible(false);
			}
		}
	}

	public void updateArcs(int x, int y) {
		arc.setFrame(x - 80, y - 80, 160, 160);
		arc.setAngleStart(0);
		arc.setAngleExtent(45);
	}

	public Arc2D.Float getArc() {
		return arc;
	}

	public void changeInitAngle(double delta) {

		initAngle = initAngle + delta;
		// Update degree for hierarchical buttons
		for (int i = 0; i < pieButtons.length; i++) {
                        
			int currentDegree = pieButtons[i].getDegree();
                        int updatedDegree = currentDegree + (int) delta;
                        
                        if(updatedDegree > 360)
                        {
                            pieButtons[i].setDegree(updatedDegree - 360);
                        }
                        else if(updatedDegree < 0)
                        {
                            pieButtons[i].setDegree(updatedDegree + 360);
                        }
                        else
                        {
                            pieButtons[i].setDegree(updatedDegree);
                        }
                            

		}
		updateButtons();
	}

	private void setButtonVisible(int b) {
		pieButtons[b].setVisible(true);
	}

	public int getDiameter() {
		return diameter;
	}

	public int getRadius() {
		return radius;
	}
	
	public PieButton[] getPieButtons() {
		return pieButtons;
	}

	public String getUIClassID() {
		return PieMenuUI.UI_CLASS_ID;
	}

	public void setModel() {
		if (pieMenuModel != null) {
			pieMenuModel.removeChangeListener(this);
		}
		pieMenuModel.addChangeListener(this);
	}

	public PieMenuModel getModel() {
		return pieMenuModel;
	}

	public void setUI(PieMenuUI ui) {
		super.setUI(ui);
	}

	public void updateUI() {
		setUI((PieMenuUI) UIManager.getUI(this));
		invalidate();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		repaint();

	}

	private class ButtonsAnimListener implements ActionListener {
		int index = 0;

		public void actionPerformed(final ActionEvent arg0) {
			setButtonVisible(index);
			if (index < pieButtons.length - 1) {
				index++;
			} else {
				buttonsTimer.stop();
			}
		}
	}

}
