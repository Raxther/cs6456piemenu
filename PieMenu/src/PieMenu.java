import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

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
	
	private Arc2D.Float arc;

	public PieMenu() {
		buttonsTimer = new Timer(50, new ButtonsAnimListener());
		pieMenuModel = new PieMenuModel();
		setModel();
		arc = new Arc2D.Float(Arc2D.PIE); //ARC
		updateArcs(this.getWidth()/2, this.getHeight()/2);
		buildMenuButtons();
		updateUI();
		buttonsTimer.start();
	}

	private void buildMenuButtons() {
		int d = 0;
		for (int i = 0; i < pieButtons.length; i++) {
			pieButtons[i] = new PieButton(this, 2);
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
		double currentAngle = 0;

		// Calculate center, with offset
		int centerX = (getWidth() / 2) - 22;
		int centerY = (getHeight() / 2) - 22;
		
		for (int i = 0; i < pieButtons.length; i++) {
			// Get current angles (in radians)
			double currentXAngle = Math.cos(Math.toRadians(currentAngle));
			double currentYAngle = Math.sin(Math.toRadians(currentAngle));
			// Get current offset coordinates
			double currentXCoordinate = 80 * currentXAngle;
			double currentYCoordinate = 80 * currentYAngle;
			// Position buttons around circle
			pieButtons[i].setBounds(centerX + (int) currentXCoordinate, centerY
					- (int) currentYCoordinate, 44, 44);
			currentAngle += angularSpacing;

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
		arc.setFrame(x-80, y-80, 160, 160);
		arc.setAngleStart(0);
		arc.setAngleExtent(45);
	}
	
	public Arc2D.Float getArc() {
		return arc;
	}

	private void setButtonVisible(int b) {
		pieButtons[b].setVisible(true);
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
			if(index < pieButtons.length-1) {
				index++;
			} else {
				buttonsTimer.stop();
			}
		}
	}

}
