import java.awt.Dimension;

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
		if(pieButtonModel != null) {
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
		JButton button = new JButton();
		this.add(button);
		button.setBounds(0, 0, 44, 44);
		//this.setPreferredSize(new Dimension(44,44));
	}
	
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		repaint();
		
	}

}
