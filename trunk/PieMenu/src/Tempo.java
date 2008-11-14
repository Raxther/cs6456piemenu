import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Tempo extends JPanel {

	/**
	 * Create the panel
	 */
	public Tempo() {
		super();
		setLayout(null);

		final JButton button = new JButton();
		button.addActionListener(new ButtonActionListener());
		button.setText("New JButton");
		button.setBounds(56, 153, 106, 26);
		add(button);
		//
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent arg0) {
			
		}
	}

}
