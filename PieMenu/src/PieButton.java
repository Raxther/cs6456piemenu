import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private PieMenu pieMenu;
	private boolean isExpanded;

	public PieButton(PieMenu pie, int nodes) {
		pieButtonModel = new PieButtonModel();
		setModel();
		buildButton();
		pieMenu = pie;
		leafNodes = nodes;
		if (leafNodes > 0) {
			addHierarchButtons(leafNodes);
		}
		setHierarchyVisible(false);
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
			//this.add(hierarchButton);
			hierarchButton.setBounds(0, 0, 44, 44);
			hierarchButtons[i] = hierarchButton;
		}
	}

	public void setHierarchyVisible(boolean show) {
		if (leafNodes > 0) {
			for (int i = 0; i < hierarchButtons.length; i++) {
				hierarchButtons[i].setVisible(show);
			}
		}
		isExpanded = show;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void updateHierarchy() {
		if (leafNodes > 0) {
                        
			double currentHierarchAngle = 0;
                       /* if(hierarchButtons.length == 2)
                        {
                            currentHierarchAngle = -45;
                        }
                        else if(hierarchButtons.length == 3)
                        {
                            currentHierarchAngle = -60;
                        }
                        else if(hierarchButtons.length == 4)
                        {
                            currentHierarchAngle = -67.5;
                        }
                        else if(hierarchButtons.length == 5)
                        {
                            currentHierarchAngle = -71.25;
                        }
                        else if(hierarchButtons.length == 6)
                        {
                            currentHierarchAngle = -73.125; 
                        }
                        else if(hierarchButtons.length == 7)
                        {
                            currentHierarchAngle = -74.0625; 
                        }
                        else if(hierarchButtons.length == 8)
                        {
                            currentHierarchAngle = -74.53125; 
                        } */
			// Position hierarchical buttons
			for (int y = 0; y < hierarchButtons.length; y++) {

				// Spacing 
				int hierarchSpacing = (360/hierarchButtons.length);
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
                                //Get current offset coordinates                
				double currentHierarchXCoordinate = (20 + (hierarchButtons.length -4) * 10) * currentHierarchXAngle;
				double currentHierarchYCoordinate = (20 + (hierarchButtons.length -4) * 10) * currentHierarchYAngle;
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

	private class MainButtonListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			pieMenu.setHierarchyHidden();
			setHierarchyVisible(true);
		}
	}

}
