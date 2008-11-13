import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComponent;
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

	public PieMenu() {
		pieMenuModel = new PieMenuModel();
		setModel();
		buildMenuButtons();
		updateUI();
	}

	private void buildMenuButtons() {
		int d = 0;
		for (int i = 0; i < pieButtons.length; i++) {
			pieButtons[i] = new PieButton();
			pieButtons[i].setDegree(d);
			this.add(pieButtons[i]);
			d += 45;
		}
		updateButtons();
	}

	public void updateButtons() {
            double angularSpacing = (double)360/(double)pieButtons.length;
            double currentAngle = 0;
	     
            //Calculate center, with offset
            int centerX = (getWidth() / 2) - 22;
            int centerY = (getHeight() / 2) - 22;
            for(int i = 0; i < pieButtons.length; i++)
            {
                //Get current angles (in radians)
                double currentXAngle = Math.cos(Math.toRadians(currentAngle));
                double currentYAngle = Math.sin(Math.toRadians(currentAngle));
                //Get current offset coordinates
                double currentXCoordinate = 80 * currentXAngle;
                double currentYCoordinate = 80 * currentYAngle;
                //Position buttons around circle
		pieButtons[i].setBounds(centerX + (int)currentXCoordinate, centerY - (int)currentYCoordinate, 45, 45);
                currentAngle += angularSpacing;
            }

            
   
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

}
