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
	
	public PieMenu() {
		pieMenuModel = new PieMenuModel();
		setModel();
		updateUI();
	}

	public String getUIClassID() {
		return PieMenuUI.UI_CLASS_ID;
	}
	
	public void setModel() {
		if(pieMenuModel != null) {
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
