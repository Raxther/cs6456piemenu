import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TestApp extends JFrame {

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestApp frame = new TestApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public TestApp() {
		super();
		getContentPane().setLayout(new BorderLayout());
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
UIManager.put(PieMenuUI.UI_CLASS_ID, "BasicPieMenuUI");
		PieMenu pieMenu = new PieMenu();		
		//final JPanel panel = new JPanel();
		getContentPane().add(pieMenu, BorderLayout.CENTER);
		
	}
}
