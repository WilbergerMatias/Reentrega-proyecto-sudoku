package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Victoria extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Victoria(boolean victory) {
		if (victory) {
			setTitle("VICTORIA!");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 272, 145);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
		
			JLabel lblNewLabel = new JLabel("FELICIDADES! ");
			lblNewLabel.setBounds(10, 11, 233, 27);
			contentPane.add(lblNewLabel);
		
			JLabel lblNewLabel_1 = new JLabel("Logro resolver el problema de forma exitosa!");
			lblNewLabel_1.setBounds(10, 49, 302, 46);
			contentPane.add(lblNewLabel_1);
		}
		else {
			setTitle("VICTORIA?");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 272, 145);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("...");
			lblNewLabel.setBounds(10, 11, 233, 27);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Siga intentando encontrar la solucion!");
			lblNewLabel_1.setBounds(10, 49, 302, 46);
			contentPane.add(lblNewLabel_1);	
		}
	}

}
