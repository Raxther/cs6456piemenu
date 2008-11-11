import java.awt.Dimension;

import javax.swing.ImageIcon;
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
public class PieButton extends JComponent implements ChangeListener {

	private PieButtonModel pieButtonModel;
	private JButton mainButton;

	public PieButton() {
		pieButtonModel = new PieButtonModel();
		setModel();
		buildButton();
		updateUI();
	}

	public String getUIClassID() {
		return PieButtonUI.UI_CLASS_ID;
	}

	public void setModel() {
		if (pieButtonModel != null) {
			pieButtonModel.removeChangeListener(this);
		}
		pieButtonModel.addChangeListener(this);
	}

	public PieButtonModel getModel() {
		return pieButtonModel;
	}

	public void setUI(PieMenuUI ui) {
		super.setUI(ui);
	}

	public void updateUI() {
		setUI((PieButtonUI) UIManager.getUI(this));
		invalidate();
	}

	private void buildButton() {
		mainButton = new JButton();
		mainButton.setIcon(new ImageIcon(getClass().getResource(
				"resources/dark_blue_button.png")));
		mainButton.setRolloverIcon(new ImageIcon(getClass().getResource(
				"resources/yellow_button.png")));
		mainButton.setFocusPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setBorderPainted(false);

		this.add(mainButton);
		mainButton.setBounds(0, 0, 44, 44);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		repaint();

	}

}