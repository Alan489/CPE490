import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;


public class err {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					err window = new err();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public err() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 0, 0));
		frame.getContentPane().setLayout(null);
		
		JLabel lblErrorOccuredPlease = new JLabel("Error occured.\r\nPlease check IP and port numbers.");
		lblErrorOccuredPlease.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblErrorOccuredPlease.setForeground(new Color(255, 255, 255));
		lblErrorOccuredPlease.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorOccuredPlease.setBounds(10, 0, 611, 61);
		frame.getContentPane().add(lblErrorOccuredPlease);
		frame.setBounds(100, 100, 647, 129);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
