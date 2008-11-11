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
		//Testing button at angle 0
		pieButtons[0].setBounds(this.getWidth() / 2 + 80,
				this.getHeight() / 2 - 22, 200, 200);
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
