import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * 
 */

/**
 * @author Ivan & Luis
 * 
 */
public class PieMenuModel {
	protected transient ChangeEvent changeEvent = null;
	protected EventListenerList listenerList = new EventListenerList();

	public PieMenuModel() {

	}

	public void addChangeListener(ChangeListener li) {
		listenerList.add(ChangeListener.class, li);
	}

	public void removeChangeListener(ChangeListener li) {
		listenerList.remove(ChangeListener.class, li);
	}

	public ChangeListener[] getChangeListeners() {
		return (ChangeListener[]) listenerList
				.getListeners(ChangeListener.class);
	}

	protected void fireChange() {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ChangeListener.class) {
				if (changeEvent == null) {
					changeEvent = new ChangeEvent(this);
				}
				((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
			}
		}
	}

}
