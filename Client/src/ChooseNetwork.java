import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ChooseNetwork {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private Main retTo;
	public ChooseNetwork cn;

	/**
	 * Launch the application. --- Thanks WindowBuilder, but I'll take it from here.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseNetwork window = new ChooseNetwork();
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
	public ChooseNetwork(Main rt) {
		initialize();
		retTo = rt;
		cn = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setLayout(null);
		
		JLabel lblIpToConnect = new JLabel("IP to connect to:");
		lblIpToConnect.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIpToConnect.setBounds(10, 22, 107, 14);
		frame.getContentPane().add(lblIpToConnect);
		
		textField = new JTextField();
		textField.setBounds(127, 19, 166, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPort.setBounds(31, 47, 86, 14);
		frame.getContentPane().add(lblPort);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 44, 166, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnConnect = new JButton("Connect!");
		btnConnect.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				retTo.connect(textField.getText(), textField_1.getText(), cn);
			}
		});
		btnConnect.setBounds(137, 75, 89, 23);
		frame.getContentPane().add(btnConnect);
		frame.setBounds(100, 100, 381, 201);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
