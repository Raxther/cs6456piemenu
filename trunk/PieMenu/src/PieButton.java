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
	public JButton[] hierarchButtons;
	private int degree;
	private int leafNodes;

	public PieButton(int nodes) {
		pieButtonModel = new PieButtonModel();
		setModel();
		buildButton();
		leafNodes = nodes;
		if (leafNodes > 0) {
			addHierarchButtons(leafNodes);
		}
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

	private void addHierarchButtons(int numButtons) {
		hierarchButtons = new JButton[numButtons];
		for (int i = 0; i < hierarchButtons.length; i++) {
			JButton hierarchButton = new JButton();
			hierarchButton.setIcon(new ImageIcon(getClass().getResource(
					"resources/dark_blue_button.png")));
			hierarchButton.setRolloverIcon(new ImageIcon(getClass()
					.getResource("resources/yellow_button.png")));
			hierarchButton.setFocusPainted(false);
			hierarchButton.setContentAreaFilled(false);
			hierarchButton.setBorderPainted(false);

			this.add(hierarchButton);
			hierarchButton.setBounds(0, 0, 44, 44);
			hierarchButtons[i] = hierarchButton;
		}
	}

	public void updateHierarchy() {
		if (leafNodes > 0) {
			double currentHierarchAngle = 0;
			// Position hierarchical buttons
			for (int y = 0; y < hierarchButtons.length; y++) {

				// Spacing - currently hard coded to 55 degrees
				int hierarchSpacing = 55;
				// Get coordinates of parent button
				int parentButtonX = this.getX();
				int parentButtonY = this.getY();
				// Get current angles (in radians)
				double currentHierarchXAngle = Math.cos(Math.toRadians(this
						.getDegree()
						+ currentHierarchAngle));
				double currentHierarchYAngle = Math.sin(Math.toRadians(this
						.getDegree()
						+ currentHierarchAngle));
				// Get current offset coordinates (50 pixel maximum offset - for
				// two buttons, otherwise need more)
				double currentHierarchXCoordinate = 50 * currentHierarchXAngle;
				double currentHierarchYCoordinate = 50 * currentHierarchYAngle;
				hierarchButtons[y].setBounds(parentButtonX
						+ (int) currentHierarchXCoordinate, parentButtonY
						- (int) currentHierarchYCoordinate, 44, 44);
				currentHierarchAngle += hierarchSpacing;
			}
		}
	}

	public void setDegree(int d) {
		degree = d;
	}

	public int getDegree() {
		return (degree);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		repaint();
	}

}
