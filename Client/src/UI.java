import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.DropMode;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.sun.xml.internal.txw2.Document;


public class UI {
	JTextArea textArea;
	JFrame frame;
	boolean pause = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
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
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		//frame.setUndecorated(true);
		//frame.setAlwaysOnTop(true);
		//frame.setOpacity(0.5f);
		frame.setResizable(false);
		frame.setBounds(100, 100, 765, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setColumns(128);
		textArea.setRows(128);
		textArea.setBounds(10, 11, 739, 424);
		frame.getContentPane().add(textArea);
		javax.swing.text.Document d = textArea.getDocument();
		d.addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent arg0) {
				ud(arg0);
			}
			
			public void insertUpdate(DocumentEvent arg0) {
				ud(arg0);
			}
			
			public void changedUpdate(DocumentEvent arg0) {
			}
			
			public void ud(DocumentEvent e)
			{
				if (pause) return;
				DataModel temp = new DataModel();
				String[] s= textArea.getText().split(""+(char)10);
				for (int r = 0; r < s.length; r++)
				{
					char[] t = s[r].toCharArray();
					for (int c = 0; c < t.length; c++)
					{
						temp.update(t[c], r, c);
					}
				}
				DataModel diff = Main.m.dm.getDiff(temp);
				//System.out.println(diff);
				Main.ni.c.pushLine(diff.toString());
			}
		});
	}
}
