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
	
	private PieMenuModel pieButtonModel;
	
	public PieButton() {
		pieButtonModel = new PieMenuModel();
		setModel();
		updateUI();
	}

	public String getUIClassID() {
		return PieMenuUI.UI_CLASS_ID;
	}
	
	public void setModel() {
		if(pieButtonModel != null) {
			pieButtonModel.removeChangeListener(this);
		}
		pieButtonModel.addChangeListener(this);
	}
	
	public PieMenuModel getModel() {
		return pieButtonModel;
	}
	
	public void setUI(PieMenuUI ui) {
		super.setUI(ui);
	}

	public void updateUI() {
		setUI((PieButtonUI) UIManager.getUI(this));
		invalidate();
	}
	
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		repaint();
		
	}

}
