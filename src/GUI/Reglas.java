package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Reglas extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Reglas() {
		setTitle("REGLAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 100, 626, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1. Una misma fila no puede contener numeros repetidos");
		lblNewLabel.setBounds(22, 31, 412, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblUnaMisma = new JLabel("2. Una misma columna no puede contener numeros repetidos");
		lblUnaMisma.setBounds(22, 70, 412, 37);
		contentPane.add(lblUnaMisma);
		
		JLabel lblUnMismo = new JLabel("3. Un mismo panel no puede contener numeros repetidos");
		lblUnMismo.setBounds(22, 108, 412, 37);
		contentPane.add(lblUnMismo);
		
		JLabel lblNotaPuedeUsar = new JLabel("NOTA: puede usar el boton \"Verificar\" ");
		lblNotaPuedeUsar.setBounds(22, 156, 412, 37);
		contentPane.add(lblNotaPuedeUsar);
		
		JLabel lblParaVerificarQue = new JLabel("para verificar que la solucion es correcta");
		lblParaVerificarQue.setBounds(240, 156, 276, 37);
		contentPane.add(lblParaVerificarQue);
		
		JLabel lblExtra = new JLabel("El juego tiene un cronometro incluido que muestra el tiempo en el siguiente formato:");
		lblExtra.setBounds(22, 199, 536, 37);
		contentPane.add(lblExtra);
		
		JLabel timer = new JLabel("HH MM SS");
		timer.setBounds(510, 199, 90, 37);
		contentPane.add(timer);
		
		JLabel lblDondeHhRepresenta = new JLabel("Donde HH representa horas, MM minutos y SS segundos.");
		lblDondeHhRepresenta.setBounds(22, 232, 536, 37);
		contentPane.add(lblDondeHhRepresenta);
	}
}

