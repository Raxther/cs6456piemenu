import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class PieButton extends JComponent implements ChangeListener {

	private PieButtonModel pieButtonModel;
	private JButton mainButton;
	public JButton[] hierarchButtons;
	private int degree;
	private int leafNodes;
	private PieMenu pieMenu;
	private boolean isExpanded;
	private boolean isExpanding;
	private boolean isContracting;
	double currentHierarchAngle;
	boolean isRollover;
	private double hierarchyRadius;
	private Timer hierarchyTimer;

	public PieButton(PieMenu pie, int nodes) {
		pieButtonModel = new PieButtonModel();
		hierarchyTimer = new Timer(10, new HierarchyAnimListener());
		currentHierarchAngle = 0;
		hierarchyRadius = 0;
		isRollover = false;
		setModel();
		buildButton();
		pieMenu = pie;
		leafNodes = nodes;
		isExpanded = false;
		isExpanding = false;
		isContracting = false;

		if (leafNodes > 0) {
			addHierarchButtons(leafNodes);
		}
		this.hideHierarchyButtons();
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
		mainButton.addActionListener(new MainButtonListener());
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
			// this.add(hierarchButton);
			hierarchButton.setBounds(0, 0, 44, 44);
			hierarchButtons[i] = hierarchButton;
		}
	}

	public void setHierarchyVisible(boolean show) {

		if (leafNodes > 0) {
			if (show == true) {
				isExpanding = true;
				if (!isExpanded) {
					isContracting = false;
					hierarchyTimer.start();
				}

				// isExpanded = true;
				for (int i = 0; i < hierarchButtons.length; i++) {

					// Set button visibility based on angles

					double diffX = hierarchButtons[i].getX() - this.getX();
					double diffY = hierarchButtons[i].getY() - this.getY();
					double currentHierarchAngle = Math.toDegrees(Math.atan2(
							Math.toRadians(diffX), Math.toRadians(diffY))) - 90;
					if (currentHierarchAngle < 0) {
						currentHierarchAngle = currentHierarchAngle + 360;
					}
					// First quadrant
					if (this.getDegree() >= 0 && this.getDegree() < 90) {
						if ((currentHierarchAngle >= 0 && currentHierarchAngle <= 91 + this
								.getDegree())
								|| (currentHierarchAngle >= 269 + this
										.getDegree() && currentHierarchAngle <= 360)) {
							hierarchButtons[i].setVisible(true);
						} else {
							hierarchButtons[i].setVisible(false);
						}
					}
					// Second quadrant
					else if (this.getDegree() >= 90 && this.getDegree() < 180) {
						if (currentHierarchAngle >= (0 + (this.getDegree() - 91))
								&& currentHierarchAngle <= 180 + (this
										.getDegree() - 89)) {
							hierarchButtons[i].setVisible(true);
						} else {
							hierarchButtons[i].setVisible(false);
						}
					}

					// Third quadrant
					else if (this.getDegree() >= 180 && this.getDegree() < 270) {
						if (currentHierarchAngle >= (0 + (this.getDegree() - 91))
								&& currentHierarchAngle <= 180 + (this
										.getDegree() - 89)) {
							hierarchButtons[i].setVisible(true);
						} else {
							hierarchButtons[i].setVisible(false);
						}
					}

					// Fourth quadrant
					else if (this.getDegree() >= 270 && this.getDegree() <= 360) {
						if ((currentHierarchAngle >= (this.getDegree() - 91) && currentHierarchAngle <= 360)
								|| (currentHierarchAngle >= 0 && currentHierarchAngle <= (this
										.getDegree() - 269))) {
							hierarchButtons[i].setVisible(true);
						} else {
							hierarchButtons[i].setVisible(false);
						}
					}
				}

			} else if (show == false) {
				isExpanding = false;
				if (isExpanded) {
					hierarchyTimer.start();
					isContracting = true;
				}
			}
		}
	}

	private void hideHierarchyButtons() {
		for (int i = 0; i < hierarchButtons.length; i++) {
			hierarchButtons[i].setVisible(false);
		}
		isExpanded = false;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void updateHierarchy() {
		if (leafNodes > 0) {
			// double currentHierarchAngle = 0;

			// Position hierarchical buttons
			for (int y = 0; y < hierarchButtons.length; y++) {

				// Spacing
				int hierarchSpacing = (360 / hierarchButtons.length);
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
				// Get current offset coordinates
				double currentHierarchXCoordinate = (25 + (hierarchButtons.length - 3)
						* hierarchyRadius)
						* currentHierarchXAngle;
				double currentHierarchYCoordinate = (25 + (hierarchButtons.length - 3)
						* hierarchyRadius)
						* currentHierarchYAngle;
				hierarchButtons[y].setBounds(parentButtonX
						+ (int) currentHierarchXCoordinate, parentButtonY
						- (int) currentHierarchYCoordinate, 44, 44);
				currentHierarchAngle += hierarchSpacing;
			}
		}
	}

	public void changeHierarchAngle(double delta) {
		currentHierarchAngle = currentHierarchAngle + delta;
		updateHierarchy();
	}

	public void setDegree(int d) {
		degree = d;
	}

	public int getDegree() {
		return (degree);
	}

	public void setRollover(boolean b) {
		mainButton.getModel().setRollover(b);
		isRollover = b;
	}

	public boolean isRollover() {
		return isRollover;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		repaint();
	}

	private class MainButtonListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			pieMenu.setHierarchyHidden();
			setHierarchyVisible(true);
		}
	}

	private class HierarchyAnimListener implements ActionListener {

		public void actionPerformed(final ActionEvent e) {
			if (isExpanding) {
				hierarchyRadius += 1.0;
				updateHierarchy();
				if (hierarchyRadius >= 10) {
					hierarchyTimer.stop();
					isExpanded = true;
					isExpanding = false;
				}
			}
			if (isContracting) {
				hierarchyRadius -= 1.0;
				updateHierarchy();
				if (hierarchyRadius <= 0) {
					hierarchyTimer.stop();
					isContracting = false;
					isExpanded = false;
					hideHierarchyButtons();
				}
			}
		}
	}

}
